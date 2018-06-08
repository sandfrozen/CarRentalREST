/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.rest;

import entities.facade.*;
import entities.Emergency;
import entities.Reservation;
import entities.response.ResponseEmergencies;
import entities.response.ResponseEmergency;
import exceptions.NotFoundException;
import java.net.URI;
import java.sql.Timestamp;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author tomek.buslowski
 */
@Stateless
@Path("emergencies")
public class EmergencyREST extends EmergencyFacade {

    // <editor-fold desc="GET /reservations" defaultstate="collapsed">
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllRest() {
        ResponseEmergencies response = new ResponseEmergencies();
        try {
            response.setList(this.findAll());
            if (response.getList() == null) {
                throw new NotFoundException("emergencies");
            }
        } catch (Exception e) {
            response.setList(null);
            response.setStatus("error");
            response.setErrorMessage(e.toString());
        }

        Response.Status status = response.getStatus() == "ok" ? Response.Status.OK : Response.Status.NOT_FOUND;
        return Response.status(status).entity(response).build();
    }
    // </editor-fold>
    // <editor-fold desc="GET /reservations/1" defaultstate="collapsed">
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRest(@PathParam("id") Integer id) {
        ResponseEmergency responseEmergency = new ResponseEmergency();

        try {
            responseEmergency.setEmergency(this.find(id));
            if (responseEmergency.getEmergency() == null) {
                throw new NotFoundException("emergency", id);
            }
        } catch (Exception e) {
            responseEmergency.setEmergency(null);
            responseEmergency.setStatus("error");
            responseEmergency.setErrorMessage(e.toString());
        }
        Response.Status status = responseEmergency.getStatus() == "ok" ? Response.Status.OK : Response.Status.BAD_REQUEST;
        Response response = Response.status(status).entity(responseEmergency).build();

        return response;
    }
    // </editor-fold>
    // <editor-fold desc="POST /reservations" defaultstate="collapsed">
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRest(Emergency emergency, @Context UriInfo uriInfo) {
        Response response;
        try {
            Integer resId = emergency.getReservation().getId();
            System.out.println("------- resId: " + resId);
            Reservation res = this.getEntityManager().find(Reservation.class, resId);
            if (res == null) {
                throw new NotFoundException("reservation", resId);
            }
            
            emergency.setActual(Boolean.TRUE);
            emergency.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            this.create(emergency);

            List<Emergency> reservations = this.findAll();
            URI uri = uriInfo.getAbsolutePathBuilder().path(reservations.get(reservations.size() - 1).getId().toString()).build();
            response = Response.created(uri).build();

        } catch (Exception e) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
            response.getHeaders().add("error", e.toString());
        }

        return response;
    }
    // </editor-fold>
    // <editor-fold desc="PUT /reservations/1" defaultstate="collapsed">
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editRest(@PathParam("id") Integer id, Emergency emergency, @Context UriInfo uriInfo) {
        Response response;
        try {
            emergency.setId(id);
            emergency.setLastUpdate(new Timestamp(System.currentTimeMillis()));

            this.edit(emergency);
            URI uri = uriInfo.getAbsolutePathBuilder().build();

            response = Response.created(uri).build();
        } catch (Exception e) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
            response.getHeaders().add("error", e.toString());
        }

        return response;
    }
    // </editor-fold>
    // <editor-fold desc="DELETE /reservations/1" defaultstate="collapsed">
    @DELETE
    @Path("{id}")
    public Response removeRest(@PathParam("id") Integer id) {
        Response response;
        try {
            this.remove(id);
            response = Response.noContent().build();

        } catch (Exception e) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
            response.getHeaders().add("error", e.toString());
        }

        return response;
    }
    // </editor-fold>

}
