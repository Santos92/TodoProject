package org.slazhy.hopto.todo.model.parser;

import org.slazhy.hopto.todo.exceptions.WrongInputException;
import org.slazhy.hopto.todo.model.User;

public final class UserParser {

    private UserParser(){}

    public static User fromText(String user){
        String[] userVars = user.split(",");
        if(userVars.length != 2)
            throw new WrongInputException("Firstname and lastname required to create user!");
        return new User(userVars[0], userVars[1]);
    }

    public static String toText(User user){
        return user.toString();
    }

    public static String listToText(Iterable<User> users){
        StringBuilder userList = new StringBuilder();
        users.forEach(u -> userList.append(toText(u) + "\n"));
        return userList.toString();
    }

}
