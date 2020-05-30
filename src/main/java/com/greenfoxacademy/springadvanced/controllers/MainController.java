package com.greenfoxacademy.springadvanced.controllers;

import com.greenfoxacademy.springadvanced.models.AuthenticationRequest;
import com.greenfoxacademy.springadvanced.models.AuthenticationResponse;
import com.greenfoxacademy.springadvanced.services.MyUserDetailsService;
import com.greenfoxacademy.springadvanced.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

  private AuthenticationManager authenticationManager;
  private UserDetailsService userDetailsService;
  private JwtUtil jwtUtil;

  @Autowired
  public MainController(AuthenticationManager authenticationManager, JwtUtil jwtUtil,
                        @Qualifier("myUserDetailsService") UserDetailsService userDetailsService) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtUtil = jwtUtil;
  }

  @GetMapping("/")
  public String home() {
    return ("<h1>Welcome home</h1>");
  }

  @GetMapping("/user")
  public String user() {
    return ("<h1>Welcome user</h1>");
  }

  @GetMapping("/admin")
  public String admin() {
    return ("<h1>Welcome admin</h1>");
  }

  @PostMapping("/authenticate")
  public ResponseEntity<?> createAuthenticationToken
      (@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),
              authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect username or password", e);
    }

    final UserDetails userDetails = userDetailsService
        .loadUserByUsername(authenticationRequest.getUserName());
    final String jwt = jwtUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }

}