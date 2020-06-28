package org.d11.rest.security;

import java.util.Arrays;

import org.d11.rest.api.Endpoint;
import org.d11.rest.security.authentication.JwtAuthenticationProvider;
import org.d11.rest.security.authentication.UserAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserAuthenticationProvider userAuthenticationProvider;
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    public SecurityConfiguration(UserAuthenticationProvider userAuthenticationProvider, JwtAuthenticationProvider jwtAuthenticationProvider) {
        this.userAuthenticationProvider = userAuthenticationProvider;
        this.jwtAuthenticationProvider = jwtAuthenticationProvider;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.authenticationProvider(this.userAuthenticationProvider).authenticationProvider(this.jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        // Turn off CSRF since we're using another token (JWT).
        // TODO: Sort out authorizations
        httpSecurity.csrf().disable().cors().and().authorizeRequests().antMatchers(Endpoint.AUTHENTICATE).permitAll().anyRequest().permitAll()
                // .antMatchers(Endpoint.SEASONS, Endpoint.SEASONS + "/**").permitAll()
                // .anyRequest().hasRole(Role.ADMINISTRATOR.name())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.addFilterBefore(new JwtRequestFilter(authenticationManagerBean()), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // Have to override this method in order to expose the AuthenticationManager as
        // a Bean.
        return super.authenticationManagerBean();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        // TODO: Maybe tighten this up.
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
