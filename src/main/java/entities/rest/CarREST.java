/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.rest;

import entities.facade.*;
import entities.Car;
import entities.Reservation;
import entities.response.ResponseCar;
import entities.response.ResponseCars;
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
@Path("cars")
public class CarREST extends CarFacade {

    // <editor-fold desc="GET /cars" defaultstate="collapsed">
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllRest() {
        ResponseCars responseCars = new ResponseCars();
        try {
            responseCars.setList(this.findAll());
            if (responseCars.getList() == null) {
                throw new NotFoundException("cars");
            }
            //throw new NotFoundException("cars");
        } catch (Exception e) {
            responseCars.setList(null);
            responseCars.setStatus("error");
            responseCars.setErrorMessage(e.toString());
        }

        Response.Status status = responseCars.getStatus() == "ok" ? Response.Status.OK : Response.Status.NOT_FOUND;
        Response response = Response.status(status).entity(responseCars).build();

        return response;
    }

    // </editor-fold>
    // <editor-fold desc="GET /cars/1" defaultstate="collapsed">
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRest(@PathParam("id") Integer id) {
        ResponseCar responseCar = new ResponseCar();

        try {
            responseCar.setCar(this.find(id));
            if (responseCar.getCar() == null) {
                throw new NotFoundException("car", id);
            }
        } catch (Exception e) {
            responseCar.setCar(null);
            responseCar.setStatus("error");
            responseCar.setErrorMessage(e.toString());
        }
        Response.Status status = responseCar.getStatus() == "ok" ? Response.Status.OK : Response.Status.BAD_REQUEST;
        Response response = Response.status(status).entity(responseCar).build();

        return response;
    }

    // </editor-fold>
    // <editor-fold desc="GET /cars/1/reservations" defaultstate="collapsed">
    @GET
    @Path("{id}/reservations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCarReservationsRest(@PathParam("id") Integer id) {
        ResponseReservations response = new ResponseReservations();
        try {
            ReservationFacade reservationFacade = ReservationFacade.getInstance();
            List<Reservation> allReservations = reservationFacade.findAll();
            List<Reservation> carReservations = new ArrayList<>();

            for (Reservation r : allReservations) {
                if (r.getCar().getId().equals(id)) {
                    carReservations.add(r);
                }
            }

            response.setList(carReservations);
            if (response.getList() == null) {
                throw new NotFoundException("car", id);
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
    // <editor-fold desc="POST /cars" defaultstate="collapsed">
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRest(Car car, @Context UriInfo uriInfo) {
        Response response;
        try {
            this.create(car);
            List<Car> cars = this.findAll();
            URI uri = uriInfo.getAbsolutePathBuilder().path(cars.get(cars.size() - 1).getId().toString()).build();
            response = Response.created(uri).build();

        } catch (Exception e) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
            response.getHeaders().add("error", e.toString());
        }

        return response;
    }

    // </editor-fold>
    // <editor-fold desc="PUT /cars/1" defaultstate="collapsed">
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editRest(@PathParam("id") Integer id, Car car, @Context UriInfo uriInfo) {
        Response response;
        try {
            car.setId(id);
            car.setLastUpdate(new Timestamp(System.currentTimeMillis()));

            this.edit(car);
            URI uri = uriInfo.getAbsolutePathBuilder().build();

            response = Response.created(uri).build();
        } catch (Exception e) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
            response.getHeaders().add("error", e.toString());
        }

        return response;
    }

    // </editor-fold>
    // <editor-fold desc="DELETE /cars/1" defaultstate="collapsed">
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
