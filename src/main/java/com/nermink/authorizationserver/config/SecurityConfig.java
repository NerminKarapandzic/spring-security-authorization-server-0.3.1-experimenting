package com.nermink.authorizationserver.config;

import com.nermink.authorizationserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

  private final UserService userService;

  @Bean
  public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
      throws Exception {

    http
        .authorizeRequests()
        .antMatchers("/users/**").permitAll()
        .antMatchers("/clients/**").permitAll()
        .anyRequest().authenticated()
        .and()
        .csrf().ignoringAntMatchers("/users/**", "/clients/**")
        .and()
        .formLogin(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    var provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userService);
    provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance()); //temporary
    return new ProviderManager(provider);
  }

  @Bean
  public UserDetailsService userDetailsService() {
    return this.userService;
  }

}
