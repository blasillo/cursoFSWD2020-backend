package es.ejemplo.backend.configuracion;


import es.ejemplo.backend.persistencia.entidades.Rol;
import es.ejemplo.backend.seguridad.JwtAutorizacionFiltro;
import es.ejemplo.backend.seguridad.JwtProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SeguridadConfig {



    @Autowired
    private UserDetailsService userDetailsService;


    @Autowired
    private JwtProveedor jwtProveedor;

    @Bean
    public static PasswordEncoder cifradorClave() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //final AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);


        http.csrf().disable()
                .headers().frameOptions().sameOrigin()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/api/v1/usuarios/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/estudiantes/**").hasRole("ESTUDIANTE")
                .antMatchers("/api/v1/profesores/**").hasRole("PROFESOR")
                .antMatchers("/api/v1/administracion/**").hasRole("ADMINISTRADOR")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/api/v1/usuarios/login")
                .and()
                .httpBasic(); //permitir autenticación básica. Cabecera http "Basic Base64(user:pass)"

        //jwt filtro
        http.addFilter(new JwtAutorizacionFiltro(authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)),
                jwtProveedor));

        return http.build();
    }

}
