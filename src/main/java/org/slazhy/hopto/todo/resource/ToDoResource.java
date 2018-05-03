package org.slazhy.hopto.todo.resource;

import org.slazhy.hopto.todo.model.LinkMessage;
import org.slazhy.hopto.todo.model.ToDo;
import org.slazhy.hopto.todo.model.User;
import org.slazhy.hopto.todo.model.parser.ToDoParser;
import org.slazhy.hopto.todo.service.ToDoService;
import org.slazhy.hopto.todo.service.UserService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import java.net.URI;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

@Component
public final class ToDoResource {

    private final ToDoService service;
    private final UserService userService;

    public ToDoResource(ToDoService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @GET
    @Produces({TEXT_PLAIN})
    public Response getToDosToText(@PathParam("userId") Long userId,
                             @QueryParam("get_all") @DefaultValue(false + "") boolean getAll,
                             @QueryParam("priority") @DefaultValue(0 + "") int priority) {
        User user = userService.getUserById(userId);
        if (getAll)
            return Response.ok(ToDoParser.listToText(service.getAllToDos())).build();
        else if (priority != 0)
            return Response.ok(ToDoParser.listToText(service.getAllToDosByUserAndPriority(user, priority))).build();
        else
            return Response.ok(ToDoParser.listToText(service.getAllToDosByUser(user))).build();
    }
    @GET
    @Produces({APPLICATION_JSON})
    public Response getToDos(@PathParam("userId") Long userId,
                             @QueryParam("get_all") @DefaultValue(false + "") boolean getAll,
                             @QueryParam("priority") @DefaultValue(0 + "") int priority) {
        User user = userService.getUserById(userId);
        if (getAll)
            return Response.ok(service.getAllToDos()).build();
        else if (priority != 0)
            return Response.ok(service.getAllToDosByUserAndPriority(user, priority)).build();
        else
            return Response.ok(service.getAllToDosByUser(user)).build();
    }
    @GET
    @Path("/{todoId}")
    @Produces(TEXT_PLAIN)
    public Response getTodoByIdText(@PathParam("todoId") Long id){
        return Response.ok(ToDoParser.toText(service.getTodoById(id))).build();
    }
    @GET
    @Path("/{todoId}")
    @Produces(APPLICATION_JSON)
    public Response getTodoById(@PathParam("todoId") Long id){
        return Response.ok(service.getTodoById(id)).build();
    }
    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response createTODO(@PathParam("userId") Long userId,
                               @Context UriInfo uriInfo,
                               ToDo toDo) {
        User user = userService.getUserById(userId);
        ToDo newTodo = service.createToDoForUser(user, toDo);
        return Response.created(getCreatedToDoUri(uriInfo, newTodo)).entity(addLinkToSelf(uriInfo, newTodo)).build();
    }
    @POST
    @Consumes(TEXT_PLAIN)
    @Produces(TEXT_PLAIN)
    public Response createTODOByString(@PathParam("userId") Long userId,
                               @Context UriInfo uriInfo,
                               String toDo) {
        User user = userService.getUserById(userId);
        ToDo newTodo = service.createToDoForUser(user, ToDoParser.fromText(toDo));
        return Response.created(getCreatedToDoUri(uriInfo, newTodo)).entity(addLinkToSelf(uriInfo, newTodo)).build();
    }
    @DELETE
    @Path("/{todoId}")
    public void deleteById(@PathParam("userId") Long userId,
                           @PathParam("todoId") Long todoId) {
        service.removeToDoById(todoId);
    }

    private URI getCreatedToDoUri(UriInfo uriInfo, ToDo todo) {
        URI uri = uriInfo.getAbsolutePathBuilder().path(todo.getId().toString()).build();
        return uri;
    }

    private LinkMessage addLinkToSelf(UriInfo uriInfo, ToDo todo) {
        return new LinkMessage(getCreatedToDoUri(uriInfo, todo).toString(), "Self");
    }

}
