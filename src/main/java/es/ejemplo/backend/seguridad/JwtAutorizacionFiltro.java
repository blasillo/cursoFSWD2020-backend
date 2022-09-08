package es.ejemplo.backend.seguridad;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAutorizacionFiltro  extends BasicAuthenticationFilter {

    private JwtProveedor jwtProveedor;

    public JwtAutorizacionFiltro(AuthenticationManager authenticationManager,
                                 JwtProveedor jwtProveedor) {
        super(authenticationManager);

        this.jwtProveedor = jwtProveedor;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        Authentication auth = jwtProveedor.obtenerAutenticacion (request);

        if(auth != null && jwtProveedor.validarToken(request)) {
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        chain.doFilter(request, response);
    }



}
