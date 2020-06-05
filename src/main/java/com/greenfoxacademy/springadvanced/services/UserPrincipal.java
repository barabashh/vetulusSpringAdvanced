package com.greenfoxacademy.springadvanced.services;

import com.greenfoxacademy.springadvanced.models.MyUserDetails;
import com.greenfoxacademy.springadvanced.models.User;
import com.greenfoxacademy.springadvanced.repositories.UserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipal implements UserDetailsService {

  private UserRepository userRepository;

  @Autowired
  public UserPrincipal(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
    Optional<User> user = userRepository.findByUserName(userName);
    user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
    /*if (user.isPresent()){
      return new MyUserDetails(user.get());
    }*/
    return user.map(MyUserDetails::new).get();
  }
}