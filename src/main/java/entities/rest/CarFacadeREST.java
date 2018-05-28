/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.rest;

import entities.Car;
import entities.response.ResponseCar;
import entities.response.ResponseCars;
import exceptions.NotFoundException;
import java.net.URI;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
import org.omg.CosNaming.NamingContextPackage.NotFound;

/**
 *
 * @author tomek.buslowski
 */
@Stateless
@Path("cars")
public class CarFacadeREST extends AbstractFacade<Car> {

    @PersistenceContext(unitName = "com.carrental_CarRentalREST_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CarFacadeREST() {
        super(Car.class);
    }

    // <editor-fold desc="GET /cars" defaultstate="collapsed">
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllCars() {
        ResponseCars responseCars = new ResponseCars();
        try {
            responseCars.setList(this.findAll());
            if (responseCars.getList() == null) {
                throw new NotFoundException("cars");
            }
        } catch (Exception e) {
            responseCars.setList(null);
            responseCars.setStatus("error");
            responseCars.setErrorMessage(e.toString());
        }

        Response.Status status = responseCars.getStatus() == "ok" ? Response.Status.OK : Response.Status.NOT_FOUND;
        Response response = Response.status(status).entity(responseCars).build();

        return response;
    }

    @Override
    public List<Car> findAll() {
        return super.findAll();
    }

    // </editor-fold>
    // <editor-fold desc="GET /cars/1" defaultstate="collapsed">
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCar(@PathParam("id") Integer id) {
        ResponseCar responseCar = new ResponseCar();

        try {
            responseCar.setCar(this.find(id));
            if (responseCar.getCar() == null) {
                throw new NotFoundException("car");
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

    public Car find(Integer id) {
        return super.find(id);
    }

    // </editor-fold>
    // <editor-fold desc="POST /cars">
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createCar(Car car, @Context UriInfo uriInfo) {
        ResponseCar responseCar = new ResponseCar();
        Response response;
        try {
            this.create(car);
            List<Car> cars = this.findAll();
            Car newCar = cars.get(cars.size()-1);
            Integer newCarId = newCar.getId();

            URI uri = uriInfo.getAbsolutePathBuilder().path(newCarId.toString()).build();
            response = Response.created(uri).build();
            
        } catch (Exception e) {
            responseCar.setCar(null);
            responseCar.setStatus("error");
            responseCar.setErrorMessage(e.toString());
            response = Response.status(Response.Status.BAD_REQUEST).entity(responseCar).build();
        }

        return response;
    }

    @Override
    public void create(Car entity) {
        super.create(entity);
    }

    // </editor-fold>
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void edit(@PathParam("id") Integer id, Car entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{from}/{to}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Car> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
