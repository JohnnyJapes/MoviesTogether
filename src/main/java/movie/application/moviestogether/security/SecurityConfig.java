package movie.application.moviestogether.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import movie.application.moviestogether.service.UserService;

@Configuration
public class SecurityConfig {


    	//authenticationProvider bean definition


        @Bean
        public DaoAuthenticationProvider authenticationProvider(UserService userService) {
            DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
            auth.setUserDetailsService(userService); //set the custom user details service
            auth.setPasswordEncoder(passwordEncoder()); //set the password encoder - bcrypt
            return auth;
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }
        
        
        
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationSuccessHandler customAuthenticationSuccessHandler, AuthenticationFailureHandler CustomAuthenticationFailureHandler) throws Exception{
        //use HasAnyRole to not have to give higher level users every role
            http.authorizeHttpRequests(configurer -> 
                configurer
                .requestMatchers(HttpMethod.GET, "/images/**").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/register/**").permitAll()
                //.requestMatchers("/*").permitAll()
                .requestMatchers("/home").permitAll()
                //.requestMatchers(HttpMethod.GET,"/api/tmdb/search/**").permitAll()
                .requestMatchers("/api/user/**").permitAll()
                .requestMatchers("/api/event/**").permitAll()
                .requestMatchers("/**").authenticated())
                .formLogin(form ->form.loginPage("/login")
                        .loginProcessingUrl("/authenticateTheUser")
                        .successHandler(customAuthenticationSuccessHandler)
                        //.failureHandler(CustomAuthenticationFailureHandler)
                        .permitAll())
                .logout(logout ->logout.permitAll()
                        .logoutSuccessUrl("/home?logout"))
                .exceptionHandling(configurer ->
                configurer.accessDeniedPage("/access-denied"));
        
            
            //disable cross site request forgery (CSRF)
            //in general, not required for stateless REST APIs that use POST, PUT, DELETE
            http.csrf(csrf -> csrf.disable());
            
            return http.build();
    
        }
}
