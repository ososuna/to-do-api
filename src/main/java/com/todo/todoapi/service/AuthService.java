package com.todo.todoapi.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.todo.todoapi.model.User;
import com.todo.todoapi.model.auth.RegisterRequest;
import com.todo.todoapi.model.enums.Role;
import com.todo.todoapi.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService implements UserDetailsService {
  
  private final String USER_NOT_FOUND_MSG = "Usuario no encontrado";
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUsername(username);
    if (user.isEmpty()) {
      log.error(USER_NOT_FOUND_MSG);
      throw new UsernameNotFoundException(USER_NOT_FOUND_MSG);
    }

    Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(user.get().getRole().toString()));

    return new org.springframework.security.core.userdetails.User(
      user.get().getUsername(),
      user.get().getPassword(),
      authorities
    );
  }

  public User register(RegisterRequest request) throws ResponseStatusException {

    if (userRepository.findByUsername(request.getUsername()).isPresent()) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "El usuario ya existe"
      );
    }

    if (request.getPassword().length() < 6) {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "La contraseña debe contener al menos 6 caracteres"
      );
    }

    request.setPassword(passwordEncoder.encode(request.getPassword()));

    User user = new User();
    BeanUtils.copyProperties(request, user);
    user.setRole(Role.ADMIN);
    user.setActive(true);
    return userRepository.save(user);

  }

}
