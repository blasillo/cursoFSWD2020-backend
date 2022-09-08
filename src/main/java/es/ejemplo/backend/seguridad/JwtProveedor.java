package es.ejemplo.backend.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtProveedor {

    @Value("${app.jwt.secreto}")
    private String jwtSecreto;

    @Value("${app.jwt.token.prefijo}")
    private String jwtTokenPrefix;

    @Value("${app.jwt.header.cadena}")
    private String jwtHeaderString;

    @Value("${app.jwt.expiracion-en-ms}")
    private Long jwtExpiracionEnMs;

    public String generarToken(Authentication auth){
        String autoridades = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining());

        SecretKey key = Keys.hmacShaKeyFor( jwtSecreto.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .setSubject(auth.getName())
                .claim("roles", autoridades)
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiracionEnMs))
                .signWith(key)
                .compact();

    }


    public boolean validarToken(HttpServletRequest request){
        String token = resolverToken(request);
        if(token == null){
            return false;
        }
        Claims claims = Jwts.parserBuilder()
                .setSigningKey( Keys.hmacShaKeyFor( jwtSecreto.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token)
                .getBody();

        if(claims.getExpiration().before(new Date())){
            return false;
        }
        return true;
    }

    public Authentication obtenerAutenticacion (HttpServletRequest request) {
        String token = resolverToken(request);

        if(token == null){
            return null;
        }

        Claims claims =  Jwts.parserBuilder()
                .setSigningKey( Keys.hmacShaKeyFor( jwtSecreto.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseClaimsJws(token).
                getBody();

        String nombreUsuario = claims.getSubject();

        List<GrantedAuthority> autoridades = Arrays.stream(claims.get("roles").toString().split(","))
                .map(role -> role.startsWith("ROLE_")?role:"ROLE_"+role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        //log.info("Autoridades {}",autoridades.toString());

        return nombreUsuario!=null ?
                new UsernamePasswordAuthenticationToken(nombreUsuario, null, autoridades) :
                null;
    }

    private String resolverToken (HttpServletRequest request){
        String bearerToken = request.getHeader(jwtHeaderString);
        if(bearerToken != null && bearerToken.startsWith(jwtTokenPrefix)){
            return bearerToken.substring(7);
        }
        return null;
    }
}
