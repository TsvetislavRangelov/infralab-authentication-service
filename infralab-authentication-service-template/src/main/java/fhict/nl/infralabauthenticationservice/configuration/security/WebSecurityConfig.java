package fhict.nl.infralabauthenticationservice.configuration.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class WebSecurityConfig {


    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //turning off login form and reverting back to http for redirect url
        httpSecurity
                .csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
