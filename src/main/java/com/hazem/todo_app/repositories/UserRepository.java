package com.hazem.todo_app.repositories;

import com.hazem.todo_app.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
}
