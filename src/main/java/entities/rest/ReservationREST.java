/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.rest;

import entities.facade.*;
import entities.Car;
import entities.Customer;
import entities.Emergency;
import entities.Reservation;
import entities.response.ResponseEmergencies;
import entities.response.ResponseReservation;
import entities.response.ResponseReservations;
import exceptions.NotFoundException;
import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
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
@Path("reservations")
public class ReservationREST extends ReservationFacade {

    // <editor-fold desc="GET /reservations" defaultstate="collapsed">
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllRest() {
        ResponseReservations response = new ResponseReservations();
        try {
            response.setList(this.findAll());
            if (response.getList() == null) {
                throw new NotFoundException("reservations");
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
        ResponseReservation responseReservation = new ResponseReservation();

        try {
            responseReservation.setReservation(this.find(id));
            if (responseReservation.getReservation() == null) {
                throw new NotFoundException("reservation", id);
            }
        } catch (Exception e) {
            responseReservation.setReservation(null);
            responseReservation.setStatus("error");
            responseReservation.setErrorMessage(e.toString());
        }
        Response.Status status = responseReservation.getStatus() == "ok" ? Response.Status.OK : Response.Status.BAD_REQUEST;
        Response response = Response.status(status).entity(responseReservation).build();

        return response;
    }
    // </editor-fold>
    // <editor-fold desc="GET /reservations/1/emergencies" defaultstate="collapsed">
    @GET
    @Path("{id}/emergencies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findEmergenciesRest(@PathParam("id") Integer id) {
        ResponseEmergencies response = new ResponseEmergencies();
        try {
            Collection<Emergency> emergencies = this.find(id).getEmergencies();
            response.setList(new ArrayList<Emergency>(emergencies));
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
    // <editor-fold desc="POST /reservations" defaultstate="collapsed">
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRest(Reservation reservation, @Context UriInfo uriInfo) {
        Response response;
        try {
            Integer carId = reservation.getCar().getId();
            Integer cusId = reservation.getCustomer().getId();
            if(  this.getEntityManager().find(Car.class, carId) == null) {
                throw new NotFoundException("car", carId);
            }
            if( this.getEntityManager().find(Customer.class, cusId) == null) {
                throw new NotFoundException("customer", cusId);
            }
            
            reservation.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            this.create(reservation);
            
            List<Reservation> reservations = this.findAll();
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
    public Response editRest(@PathParam("id") Integer id, Reservation reservation, @Context UriInfo uriInfo) {
        Response response;
        try {
            reservation.setId(id);
            reservation.setLastUpdate(new Timestamp(System.currentTimeMillis()));

            this.edit(reservation);
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
