package de.rieckpil.courses.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .authorizeRequests(authorize -> authorize
        .mvcMatchers(HttpMethod.GET, "/api/books").permitAll()
        .mvcMatchers(HttpMethod.GET, "/api/books/reviews").permitAll()
        .mvcMatchers("/api/**").authenticated()
      )
      .sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .csrf().disable()
      .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
  }
}
