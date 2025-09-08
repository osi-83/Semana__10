package exercicio.com.br.semana_10.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/auth/**", "/login/**").permitAll()

                        // Usuários: apenas ADMIN
                        .requestMatchers("/users/**").hasRole("ADMIN")

                        // Organizações: consultas USER e ADMIN, alterações só ADMIN
                        .requestMatchers(HttpMethod.GET, "/organizations/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/organizations/**").hasRole("ADMIN")

                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}