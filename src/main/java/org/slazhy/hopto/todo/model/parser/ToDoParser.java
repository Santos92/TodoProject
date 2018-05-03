package org.slazhy.hopto.todo.model.parser;

import org.slazhy.hopto.todo.exceptions.WrongInputException;
import org.slazhy.hopto.todo.model.ToDo;

public final class ToDoParser {

    private ToDoParser(){}

    public static ToDo fromText(String toDo){
        String[] todoVars = toDo.split(",");
        if(todoVars.length != 2 || todoVars[1].matches("[^0-9]*"))
            throw new WrongInputException("You need to enter a description and a priority to create todo!");
        return new ToDo(todoVars[0], Integer.parseInt(todoVars[1]));
    }

    public static String toText(ToDo toDo){
        return toDo.toString();
    }

    public static String listToText(Iterable<ToDo> toDos){
        StringBuilder todoList = new StringBuilder();
        toDos.forEach(t -> todoList.append(toText(t) + "\n"));
        return todoList.toString();
    }

}
