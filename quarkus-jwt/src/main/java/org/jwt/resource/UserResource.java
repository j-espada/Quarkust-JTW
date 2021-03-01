package org.jwt.resource;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jwt.api.UserDto;
import org.jwt.service.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

@Path("/user")
public class UserResource {

    @Inject
    UserService userService;

    @Path("/register")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(final @Valid @RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        URI uri = UriBuilder.fromPath("/api/user/register/" + userDto.getUsername()).build();
        return Response.created(uri).build();
    }

    @Path("/jwt")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(final @HeaderParam("Authorization") String auth) {
        boolean isLogged = userService.login(auth);
        return isLogged ? Response.ok().build() : Response.status(Response.Status.UNAUTHORIZED).build();
    }
}