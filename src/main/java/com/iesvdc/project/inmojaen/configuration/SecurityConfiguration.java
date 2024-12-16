package com.iesvdc.project.inmojaen.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configure(AuthenticationManagerBuilder amb) throws Exception {
        amb.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT username, password, enabled " +
                        "FROM usuario WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT u.username, r.rol as 'authority'" +
                        "FROM usuario u " +
                        "JOIN rol r ON u.rol_id = r.id " +
                        "WHERE u.username = ?");
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filter(HttpSecurity http) throws Exception {

        // Con Spring Security 6.2 y 7:
        // Puede usarse el método http.authorizeRequests()
        // para configurar las rutas y permisos de acceso.

        return http
                .authorizeHttpRequests((requests) -> requests
                    // Permitir acceso sin autorización a recursos estáticos y páginas públicas:
                    .requestMatchers(
                        "/webjars/**", "/img/**", "/js/**", "/css/**", "/static/**", "/resources/**",
                        "/", "/invitado", "/invitado/**", "/invitado/*/**", // Rutas públicas
                        "/uploads/**", // Uploads: acceso público
                        "/register", "/register/**", "/login",
                        "/public/**", "/public/*/**", "/public/*/*/**", // NEW: Acceso para usuarios sin registrar
                        "/help", "/about", "/error")
                    .permitAll()
                    // Configurar acceso según roles:
                    .requestMatchers(
                        "/admin/**", "/admin/*/**", "/admin/*/*/**", // Rutas para administradores
                        "/usuarios/**", "/usuarios/*/**", "/usuarios/*/*/**",
                        "/anuncios/**", "/anuncios/*/**", "/anuncios/*/*/**")
                        // .authenticated()
                    .hasAuthority("ADMIN")
                    .requestMatchers(
                        "/usuario/**", "/usuario/*/**", "/usuario/*/*/**", // Rutas para usuarios
                        "/activos/**", "/activos/*/**", "/activos/*/*/**",
                        "/favoritos/**", "/favoritos/*/**", "/favoritos/*/*/**")
                    // .authenticated()
                    .hasAuthority("USER")
                        // Escoger si cualquier otra solicitud debe estar autenticada o se permiten
                        // todas:
                        .anyRequest().authenticated() // Autenticación obligatoria
                        // .anyRequest().permitAll() // Autenticación opcional
                    // ).headers(headers -> headers
                        // .frameOptions(frameOptions -> frameOptions
                        // .sameOrigin())
                        // ).sessionManagement((session) -> session
                        // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).exceptionHandling((exception) -> exception
                    .accessDeniedPage("/denied"))
                .formLogin((formLogin) -> formLogin
                    .loginPage("/login") // Personalizar la página de login
                    .permitAll())
                .rememberMe(
                    Customizer.withDefaults())
                .logout((logout) -> logout
                    .invalidateHttpSession(true)
                    .logoutSuccessUrl("/")
                    // .deleteCookies("JSESSIONID") // Innecesario, JSESSIONID es por defecto
                    .permitAll())
                .csrf((protection) -> protection
                    .disable()
                // ).cors((protection) -> protection
                    // .disable()
                ).build();

    }

}
