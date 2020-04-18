package com.spring.sio.config;

import com.spring.sio.constant.SecurityConstants;
import com.spring.sio.service.IUserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final IUserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    //Step 1
    public SecurityConfiguration(IUserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    //Step 2
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
                .permitAll()
                .anyRequest()
                .authenticated().and().addFilter(getAuthenticationFilter()) //Step 3 -> Create Authentication filter

                .addFilter(getAuthorizationFilter()) //Step 4 -> Create Authorization filter
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    //Step 2-1
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

    ///Step 3 additional
    public AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager());
        authenticationFilter.setFilterProcessesUrl("/users/login");
        return authenticationFilter;
    }

    ///Step 4 additional
    public AuthorizationFilter getAuthorizationFilter() throws Exception {
        final AuthorizationFilter authoriztionFilter = new AuthorizationFilter(authenticationManager());
        return authoriztionFilter;
    }

}
