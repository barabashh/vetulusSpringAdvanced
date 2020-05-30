package com.greenfoxacademy.springadvanced.repositories;

import com.greenfoxacademy.springadvanced.models.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findByUserName(String userName);
}
