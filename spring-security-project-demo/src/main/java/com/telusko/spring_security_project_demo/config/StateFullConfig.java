//package com.telusko.spring_security_project_demo.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity
//public class StateFullConfig {
//
//    // UserDetailsService
//    @Autowired
//    public UserDetailsService userDetailsService;
//
//
//    // Authentication Provider
//    @Bean
//    public AuthenticationProvider authProvider() {
//
//        DaoAuthenticationProvider provider =
//                new DaoAuthenticationProvider(userDetailsService);
//
//        // BCrypt is used to verify the password
//        provider.setPasswordEncoder(
//                new BCryptPasswordEncoder(12)
//        );
//
//        return provider;
//    }
//
//
//    // Security Filter Chain
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http)
//            throws Exception {
//
//        // CSRF protection is enabled for stateful/session-based authentication
//        http.csrf(Customizer.withDefaults());
//
//
//        // Every request must be authenticated
//        http.authorizeHttpRequests(request ->
//                request.anyRequest().authenticated()
//        );
//
//
//        // Form-based login
//        http.formLogin(Customizer.withDefaults());
//
//
//        // STATEFUL
//        http.sessionManagement(session ->
//                session.sessionCreationPolicy(
//                        SessionCreationPolicy.IF_REQUIRED
//                )
//        );
//
//
//        return http.build();
//    }
//}
//







//for statefull remember two things


//1.
//    // STATEFUL
//        http.sessionManagement(session ->
//                session.sessionCreationPolicy(
//                        SessionCreationPolicy.IF_REQUIRED
//                )
//        );



//2.
// Form-based login
      //  http.formLogin(Customizer.withDefaults());