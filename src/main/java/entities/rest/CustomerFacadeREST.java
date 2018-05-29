/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.rest;

import entities.Customer;
import entities.response.ResponseCustomer;
import entities.response.ResponseCustomers;
import exceptions.NotFoundException;
import java.net.URI;
import java.sql.Timestamp;
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

/**
 *
 * @author tomek.buslowski
 */
@Stateless
@Path("customers")
public class CustomerFacadeREST extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "com.carrental_CarRentalREST_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CustomerFacadeREST() {
        super(Customer.class);
    }

    // <editor-fold desc="GET /customers" defaultstate="collapsed">
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllRest() {
        ResponseCustomers responseCustomers = new ResponseCustomers();
        try {
            responseCustomers.setList(this.findAll());
            if (responseCustomers.getList() == null) {
                throw new NotFoundException("customers");
            }
        } catch (Exception e) {
            responseCustomers.setList(null);
            responseCustomers.setStatus("error");
            responseCustomers.setErrorMessage(e.toString());
        }

        Response.Status status = responseCustomers.getStatus() == "ok" ? Response.Status.OK : Response.Status.NOT_FOUND;
        Response response = Response.status(status).entity(responseCustomers).build();

        return response;
    }

    @Override
    public List<Customer> findAll() {
        return super.findAll();
    }
    // </editor-fold>
    // <editor-fold desc="GET /customers/1" defaultstate="collapsed">
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRest(@PathParam("id") Integer id) {
        ResponseCustomer responseCustomer = new ResponseCustomer();

        try {
            responseCustomer.setCustomer(this.find(id));
            if (responseCustomer.getCustomer() == null) {
                throw new NotFoundException("customer");
            }
        } catch (Exception e) {
            responseCustomer.setCustomer(null);
            responseCustomer.setStatus("error");
            responseCustomer.setErrorMessage(e.toString());
        }
        Response.Status status = responseCustomer.getStatus() == "ok" ? Response.Status.OK : Response.Status.BAD_REQUEST;
        Response response = Response.status(status).entity(responseCustomer).build();

        return response;
    }

    public Customer find(Integer id) {
        return super.find(id);
    }

    // </editor-fold>
    // <editor-fold desc="POST /customers">
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRest(Customer customer, @Context UriInfo uriInfo) {
        Response response;
        try {
            this.create(customer);
            List<Customer> customers = this.findAll();
            URI uri = uriInfo.getAbsolutePathBuilder().path(customers.get(customers.size() - 1).getId().toString()).build();
            response = Response.created(uri).build();

        } catch (Exception e) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
            response.getHeaders().add("error", e.toString());
        }

        return response;
    }

    @Override
    public void create(Customer entity) {
        super.create(entity);
    }

    // </editor-fold>
    // <editor-fold desc="PUT /customers/1">
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editRest(@PathParam("id") Integer id, Customer customer, @Context UriInfo uriInfo) {
        Response response;
        try {
            customer.setId(id);
            if(customer.getPassword() == null) {
                customer.setPassword(this.find(id).getPassword());
            }
            customer.setLastUpdate(new Timestamp(System.currentTimeMillis()));
            
            this.edit(customer);
            URI uri = uriInfo.getAbsolutePathBuilder().build();
            
            response = Response.created(uri).build();
        } catch (Exception e) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
            response.getHeaders().add("error", e.toString());
        }

        return response;
    }

    public void edit(Customer entity) {
        super.edit(entity);
    }

    // </editor-fold>
    // <editor-fold desc="DELETE /customers/1">
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
    
    public void remove(Integer id) {
        super.remove(super.find(id));
    }
    // </editor-fold>

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
