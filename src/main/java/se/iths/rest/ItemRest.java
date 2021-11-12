package se.iths.rest;

import se.iths.service.ItemService;
import se.iths.entity.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("items")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ItemRest {

    @Inject
    ItemService itemService;

    @Path("")
    @POST
    public Response createItem(Item item){
        itemService.createItem(item);
        return Response.ok(item).build();
    }

    @Path("")
    @PUT
    public Response updateItem(Item item) {
        itemService.updateItem(item);
        return Response.ok(item).build();
    }

    @Path("{id}")
    @GET
    public Response getItem(@PathParam("id") Long id) {
        Item foundItem = itemService.findItemById(id);
        if(foundItem == null) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("ID NOT FOUND").type(MediaType.TEXT_PLAIN_TYPE).build());
        }
        return Response.ok(foundItem).build();
    }

    @Path("")
    @GET
    public Response getAllItems(){
        List<Item> foundItems = itemService.getAllItems();

        return Response.ok(foundItems).build();
    }

    @Path("{id}")
    @DELETE
    public Response deleteItem(@PathParam("id") Long id) {
        itemService.deleteItem(id);
        return Response.ok().build();
    }

    @Path("getallbycategory")
    @GET
    public Response getAllItemsByCategory(@QueryParam("category") String category) {
        String responseString = "Here is a list of all the items in Category" + category;
        return Response.ok(responseString).build();
    }

    @Path("updatename/{id}")
    @PATCH
    public Response updateName(@PathParam("id") Long id, @QueryParam("name") String name) {
        Item updatedItem = itemService.updateName(id, name);
        return Response.ok(updatedItem).build();
    }
}
