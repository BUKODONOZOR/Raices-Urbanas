package Contest.Project.config;

import Contest.Project.security.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/RaicesUrbanas/login", "/RaicesUrbanas/register").permitAll()
                .requestMatchers("/your/secured/url").hasRole("Vendedor")
                .requestMatchers("/your/secured/url").hasRole("Comprador")
                .anyRequest().authenticated()
        );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }*/

    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/register", "/login").permitAll() // Public access to login and registration
                        .anyRequest().authenticated() // Require authentication for other requests
                )
                .formLogin(form -> form
                        .loginPage("https://raices-urbanas-deploy-4yte.vercel.app/login") // Custom login page
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                );
    }

}
