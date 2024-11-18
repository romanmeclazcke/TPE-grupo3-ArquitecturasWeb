package micro.example.gateway.config;

import micro.example.gateway.security.AuthotityConstant;
import micro.example.gateway.security.jwt.JwtFilter;
import micro.example.gateway.security.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    public SecurityConfig( TokenProvider tokenProvider ) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain( final HttpSecurity http ) throws Exception {
        http
            .csrf( AbstractHttpConfigurer::disable );
        http
            .sessionManagement( s -> s.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) );
        http
            .securityMatcher("/**")
            .authorizeHttpRequests( authz -> authz
                    .requestMatchers(HttpMethod.POST, "/api/authenticate").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
                    .requestMatchers("/reporte/**").hasAuthority(AuthotityConstant._MANTENIMIENTO)//anda
                    .requestMatchers(HttpMethod.PUT, "/cuenta/{id}/anular").hasAuthority(AuthotityConstant._ADMINISTRADOR)//no anda
                    .requestMatchers(HttpMethod.GET, "/viaje/monopatines/viajes/{cant}/anio/{anio}").hasAuthority(AuthotityConstant._ADMINISTRADOR)//anda
                    .requestMatchers(HttpMethod.GET, "/pago/anio/{anio}/entre/{mesAnterior}/{mesPosterior}").hasAuthority(AuthotityConstant._ADMINISTRADOR)//ANDA
                    .requestMatchers(HttpMethod.GET, "/monopatines/order/disponibilidad").hasAuthority(AuthotityConstant._ADMINISTRADOR)//anda
                    .requestMatchers(HttpMethod.POST, "/tarifa").hasAuthority(AuthotityConstant._ADMINISTRADOR)//no anda
                    .anyRequest().hasAuthority(AuthotityConstant._USUARIO)
            )
            .httpBasic( Customizer.withDefaults() )
            .addFilterBefore( new JwtFilter( this.tokenProvider ), UsernamePasswordAuthenticationFilter.class );
        return http.build();
    }

}
