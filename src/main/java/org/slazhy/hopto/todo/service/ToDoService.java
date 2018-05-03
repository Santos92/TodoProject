package org.slazhy.hopto.todo.service;

import org.slazhy.hopto.todo.exceptions.DataNotFoundException;
import org.slazhy.hopto.todo.model.ToDo;
import org.slazhy.hopto.todo.model.User;
import org.slazhy.hopto.todo.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public final class ToDoService {

    private final ToDoRepository repository;

    public ToDoService(ToDoRepository repository) {
        this.repository = repository;
    }

    public ToDo createToDoForUser(User user, ToDo todo){
        ToDo newToDo = new ToDo(todo.getDescription(), todo.getPriority(), user);
        return repository.save(newToDo);
    }
    public void removeToDoById(Long id){
        if(repository.existsById(id))
            repository.deleteById(id);
        else
            throw new DataNotFoundException("Todo with id: " + id + " doesn't exist!");
    }
    public Iterable<ToDo> getAllToDosByUser(User user){
        return repository.findByOwner(user);
    }
    public Iterable<ToDo> getAllToDosByUserAndPriority(User user, int priority){
        return repository.findByOwnerAndPriority(user, priority);
    }
    public Iterable<ToDo> getAllToDos() {
        return repository.findAll();
    }

    public ToDo getTodoById(Long id) {
        Optional<ToDo> toDo = repository.findById(id);
        if (toDo.isPresent())
            return toDo.get();
        else
            throw new DataNotFoundException("Todo with id " + id + " doesn't exist");
    }
}
