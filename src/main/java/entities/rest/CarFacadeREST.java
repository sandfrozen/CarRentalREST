/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.rest;

import entities.Car;
import entities.response.ResponseCar;
import entities.response.ResponseCars;
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
import javax.ws.rs.core.MediaType;

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
    
    // <editor-fold desc="GET /cars">
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseCars findAllCars() {
        ResponseCars responseCars = new ResponseCars();
        responseCars.setList(this.findAll());
        return responseCars;
    }
    
    @Override
    public List<Car> findAll() {
        return super.findAll();
    }
    // </editor-fold>

    // <editor-fold desc="GET /cars/1">
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseCar findCar(@PathParam("id") Integer id) {
        ResponseCar responseCar = new ResponseCar();
        responseCar.setCar(this.find(id));
        return responseCar;
    }
    public Car find(Integer id) {
        return super.find(id);
    }
    // </editor-fold>
    
    @POST
    @Override
    @Consumes(MediaType.APPLICATION_JSON)
    public void create(Car entity) {
        super.create(entity);
    }

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
