package com.hazem.todo_app.repositories;

import com.hazem.todo_app.entities.Todo;
import com.hazem.todo_app.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface TodoRepository extends JpaRepository<Todo, Long> {
  Page<Todo> findByUser(User user, Pageable pageable);
}
