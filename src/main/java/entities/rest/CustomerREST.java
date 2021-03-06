/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.rest;

import entities.facade.*;
import entities.Customer;
import entities.Reservation;
import entities.response.ResponseCustomer;
import entities.response.ResponseCustomers;
import entities.response.ResponseReservations;
import exceptions.IncorrectValuesException;
import exceptions.NotFoundException;
import java.net.URI;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
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
import utils.StringUtil;

/**
 *
 * @author tomek.buslowski
 */
@Stateless
@Path("customers")
public class CustomerREST extends CustomerFacade {

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

    // </editor-fold>
    // <editor-fold desc="GET /customers/1" defaultstate="collapsed">
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findRest(@PathParam("id") Integer id, @Context UriInfo uriInfo) {
        ResponseCustomer responseCustomer = new ResponseCustomer();

        try {
            Customer c = this.find(id);
            if (c == null) {
                throw new NotFoundException("customer", id);
            }

            String self = uriInfo.getBaseUriBuilder()
                    .path(CustomerREST.class)
                    .path(String.valueOf(c.getId()))
                    .build().toString();
            c.addLink("self", self);
            
            String ress = uriInfo.getBaseUriBuilder()
                    .path(CustomerREST.class)
                    .path(String.valueOf(c.getId()))
                    .path(ReservationREST.class)
                    .build().toString();
            c.addLink("reservations", ress);

//            String comments = uriInfo.getBaseUriBuilder()
//                    .path(MessageResource.class)
//                    .path(MessageResource.class, "getComments")
//                    .resolveTemplate("messageId", message.getId())
//                    .build().toString();
//            c.addLink("comments", comments);
            
            responseCustomer.setCustomer(c);
            if (responseCustomer.getCustomer() == null) {
                throw new NotFoundException("customer", id);
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

    // </editor-fold>  
    // <editor-fold desc="GET /customers/1/reservations" defaultstate="collapsed">
    @GET
    @Path("{id}/reservations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCustomerReservationsRest(@PathParam("id") Integer id) {
        ResponseReservations response = new ResponseReservations();
        try {
            ReservationFacade reservationFacade = ReservationFacade.getInstance();
            List<Reservation> allReservations = reservationFacade.findAll();
            List<Reservation> customerReservations = new ArrayList<>();

            for (Reservation r : allReservations) {
                if (r.getCustomer().getId().equals(id)) {
                    customerReservations.add(r);
                }
            }

            response.setList(customerReservations);
            if (response.getList() == null) {
                throw new NotFoundException("customer", id);
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
    // <editor-fold desc="GET /customers/1/reservations/past" defaultstate="collapsed">
    @GET
    @Path("{id}/reservations/past")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCustomerReservationsOldRest(@PathParam("id") Integer id) {
        ResponseReservations response = new ResponseReservations();
        try {
            ReservationFacade reservationFacade = ReservationFacade.getInstance();
            List<Reservation> allReservations = reservationFacade.findAll();
            List<Reservation> customerReservations = new ArrayList<>();
            Date now = new Date();
            for (Reservation r : allReservations) {
                if (r.getCustomer().getId().equals(id) && r.getToDate().before(now)) {
                    customerReservations.add(r);
                }
            }

            response.setList(customerReservations);
            if (response.getList() == null) {
                throw new NotFoundException("customer", id);
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
    // <editor-fold desc="GET /customers/1/reservations/actual" defaultstate="collapsed">
    @GET
    @Path("{id}/reservations/actual")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCustomerReservationsActualRest(@PathParam("id") Integer id) {
        ResponseReservations response = new ResponseReservations();
        try {
            ReservationFacade reservationFacade = ReservationFacade.getInstance();
            List<Reservation> allReservations = reservationFacade.findAll();
            List<Reservation> customerReservations = new ArrayList<>();

            Date now = new Date();
            System.out.println("NOW ------- " + now);
            for (Reservation r : allReservations) {
                Date from = r.getFromDate();
                Date to = r.getToDate();

                if (r.getCustomer().getId().equals(id) && !from.after(now) && !to.before(now)) {
                    customerReservations.add(r);
                }
            }

            response.setList(customerReservations);
            if (response.getList() == null) {
                throw new NotFoundException("customer", id);
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
    // <editor-fold desc="GET /customers/1/reservations/future" defaultstate="collapsed">
    @GET
    @Path("{id}/reservations/future")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCustomerReservationsFutureRest(@PathParam("id") Integer id) {
        ResponseReservations response = new ResponseReservations();
        try {
            ReservationFacade reservationFacade = ReservationFacade.getInstance();
            List<Reservation> allReservations = reservationFacade.findAll();
            List<Reservation> customerReservations = new ArrayList<>();

            Date now = new Date();
            for (Reservation r : allReservations) {
                if (r.getCustomer().getId().equals(id) && r.getFromDate().after(now)) {
                    customerReservations.add(r);
                }
            }

            response.setList(customerReservations);
            if (response.getList() == null) {
                throw new NotFoundException("customer", id);
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
    // <editor-fold desc="POST /customers" defaultstate="collapsed">
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createRest(Customer customer, @Context UriInfo uriInfo) {
        Response response;
        try {
            String mail = customer.getMail().toLowerCase();;
            if (!StringUtil.IsMailFormat(mail)) {
                throw new IncorrectValuesException("mail", customer.getMail());
            }
            List<Customer> customers = this.findAll();
            Customer founded = null;
            for (Customer c : customers) {
                if (c.getMail().equals(mail)) {
                    founded = c;
                    break;
                }
            }

            if (founded != null) {
                throw new IncorrectValuesException("mail", mail, "arleady used");
            }
            customer.setMail(mail);
            customer.setName(StringUtil.Capitalize(customer.getName()));
            customer.setSurname(StringUtil.Capitalize(customer.getSurname()));
            this.create(customer);
            customers = this.findAll();
            URI uri = uriInfo.getAbsolutePathBuilder().path(customers.get(customers.size() - 1).getId().toString()).build();
            response = Response.created(uri).build();

        } catch (Exception e) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
            response.getHeaders().add("error", e.toString());
        }

        return response;
    }

    // </editor-fold>
    // <editor-fold desc="PUT /customers/1" defaultstate="collapsed">
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editRest(@PathParam("id") Integer id, Customer customer, @Context UriInfo uriInfo) {
        Response response;
        try {
            Customer editableCustomer = this.find(id);
            if (editableCustomer == null) {
                throw new NotFoundException("customer", id);
            }
            editableCustomer.merge(customer);
            editableCustomer.setLastUpdate(new Timestamp(System.currentTimeMillis()));

            this.edit(editableCustomer);
            URI uri = uriInfo.getAbsolutePathBuilder().build();

            response = Response.created(uri).build();
        } catch (Exception e) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
            response.getHeaders().add("error", e.toString());
        }

        return response;
    }

    // </editor-fold>
    // <editor-fold desc="DELETE /customers/1" defaultstate="collapsed">
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
