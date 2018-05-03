package org.slazhy.hopto.todo.service;

import org.slazhy.hopto.todo.exceptions.DataNotFoundException;
import org.slazhy.hopto.todo.exceptions.WrongInputException;
import org.slazhy.hopto.todo.model.User;
import org.slazhy.hopto.todo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public final class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user){
        validate(user);
        return repository.save(user);
    }

    public void removeUserById(Long id){
        if(repository.existsById(id))
            repository.deleteById(id);
        else
            throw new DataNotFoundException("User with id: " + id + " doesn't exist!");
    }

    public User getUserById(Long id){
        if(repository.existsById(id))
            return repository.findById(id).get();
        else
            throw new DataNotFoundException("User with id: " + id + " doesn't exist!");
    }

    public Iterable<User> getAllUsers(){
        return repository.findAll();
    }

    private void validate(User user) {
        if(user.getFirstname() == null || user.getLastname() == null)
            throw new WrongInputException("Firstname and lastname required to create user!");
    }

}
