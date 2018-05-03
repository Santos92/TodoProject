package org.slazhy.hopto.todo.resource;

import org.slazhy.hopto.todo.model.LinkMessage;
import org.slazhy.hopto.todo.model.User;
import org.slazhy.hopto.todo.model.parser.UserParser;
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
@Path("/users")
@Produces({APPLICATION_JSON, TEXT_PLAIN})
public final class UserResource {

    private final UserService service;
    private final ToDoService toDoservice;

    @Context
    private UriInfo uriInfo;

    public UserResource(UserService service, ToDoService toDoservice) {
        this.service = service;
        this.toDoservice = toDoservice;
    }

    @POST
    @Consumes(APPLICATION_JSON)
    public Response createUserByJson(User user){
        User newUser = service.createUser(user);
        return Response
                .created(getCreatedUserUri(uriInfo, newUser))
                .entity(addLinkToSelf(uriInfo, newUser))
                .build();
    }
    @POST
    @Consumes(TEXT_PLAIN)
    public Response createUserByString(String user){
        User newUser = service.createUser(UserParser.fromText(user));
        return Response
                .created(getCreatedUserUri(uriInfo, newUser))
                .entity(addLinkToSelf(uriInfo, newUser))
                .build();
    }
    @DELETE
    @Path("/{userId}")
    public void removeUserById(@PathParam("userId") Long id){
        service.removeUserById(id);
    }
    @GET
    @Path("/{userId}")
    @Produces(APPLICATION_JSON)
    public Response getUserById(@PathParam("userId") Long id){
        return Response.ok(service.getUserById(id)).build();
    }
    @GET
    @Path("/{userId}")
    @Produces(TEXT_PLAIN)
    public Response getUserByIdInText(@PathParam("userId") Long id){
        return Response.ok(UserParser.toText(service.getUserById(id))).build();
    }
    @GET
    @Produces(APPLICATION_JSON)
    public Response getAllUsers(){
        return Response.ok(service.getAllUsers()).build();
    }
    @GET
    @Produces(TEXT_PLAIN)
    public Response getAllUsersToText(){
        return Response.ok(UserParser.listToText(service.getAllUsers())).build();
    }

    @Path("/{userId}/todos/")
    public ToDoResource callToDoResource(){
        return new ToDoResource(toDoservice, service);
    }

    private URI getCreatedUserUri(UriInfo uriInfo, User user){
        URI uri = uriInfo.getAbsolutePathBuilder().path(user.getId().toString()).build();
        return uri;
    }
    private LinkMessage addLinkToSelf(UriInfo uriInfo, User user){
        return new LinkMessage(getCreatedUserUri(uriInfo, user).toString(), "Self");
    }

}
