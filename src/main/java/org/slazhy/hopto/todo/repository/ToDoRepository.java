package org.slazhy.hopto.todo.repository;

import org.slazhy.hopto.todo.model.ToDo;
import org.slazhy.hopto.todo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ToDoRepository extends CrudRepository<ToDo, Long> {

    Iterable<ToDo> findByOwnerAndPriority(User user, int year);

    Iterable<ToDo> findByOwner(User user);
}
