package com.todo.todoapi.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.server.ResponseStatusException;

import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.todoapi.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private static final String APPLICATION_JSON_VALUE = "application/json";
  private static final String INVALID_CREDENTIALS_MSG = "Invalid username or password";
  private final AuthenticationManager authenticationManager;
  private final UserService userService;

  public CustomAuthenticationFilter(
    AuthenticationManager authenticationManager,
    UserService userService
  ) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
    return authenticationManager.authenticate(token);
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException, ServletException, ResponseStatusException {
    Map<String, String> error = new HashMap<>();
    error.put("message", INVALID_CREDENTIALS_MSG);
    response.setContentType(APPLICATION_JSON_VALUE);
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
    new ObjectMapper().writeValue(response.getOutputStream(), error);
    log.error(INVALID_CREDENTIALS_MSG);
    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, INVALID_CREDENTIALS_MSG);
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authentication) throws IOException, ServletException {
    User user = (User) authentication.getPrincipal();

    Algorithm algorithm = Algorithm.HMAC256("T0dOAP1v3rSion2".getBytes());
    
    String access_token = com.auth0.jwt.JWT.create()
      .withSubject(user.getUsername())
      .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24 hours
      .withIssuer(request.getRequestURL().toString())
      .withClaim("roles", user.getAuthorities().toString())
      .sign(algorithm);
    
    String refresh_token = com.auth0.jwt.JWT.create()
      .withSubject(user.getUsername())
      .withExpiresAt(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000)) // 1 week
      .withIssuer(request.getRequestURL().toString())
      .withClaim("roles", user.getAuthorities().toString())
      .sign(algorithm);
    
    // response.addHeader("access-token", access_token);
    // response.addHeader("refresh-token", refresh_token);

    Map<String, Object> data = new HashMap<>();
    data.put("user", userService.getUser(user.getUsername()));
    data.put("access_token", access_token);
    data.put("refresh_token", refresh_token);
    response.setContentType(APPLICATION_JSON_VALUE);
    new ObjectMapper().writeValue(response.getOutputStream(), data);

  }

}