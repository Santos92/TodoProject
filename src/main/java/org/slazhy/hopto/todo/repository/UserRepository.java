package org.slazhy.hopto.todo.repository;

import org.slazhy.hopto.todo.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
