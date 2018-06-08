/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.rest;

import entities.facade.*;
import entities.Customer;
import exceptions.SessionException;
import exceptions.FormatException;
import exceptions.NotFoundException;
import exceptions.SessionException.ExceptionType;
import java.util.List;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.KeyGenerator;
import utils.SimpleJson;

/**
 *
 * @author tomek.buslowski
 */
@Stateless
@Path("session")
public class SessionREST extends CustomerFacade {

    // <editor-fold desc="POST /session/auth" defaultstate="collapsed">
    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response customerAuth(Customer customer) {
        Response response;
        try {
            if (customer.getId() != null
                    && customer.getMail() != null
                    && customer.getPassword() != null
                    && customer.getLoginKey() != null) {

                Customer c = this.find(customer.getId());
                if (c == null) {
                    throw new NotFoundException("customer", customer.getId());
                } else if (c.getLoginKey() != null
                        && c.getLoginKey().equals(customer.getLoginKey())
                        && c.getMail().equals(customer.getMail())
                        && c.getPassword().equals(customer.getPassword())) {

                    response = Response.accepted().build();

                } else {
                    throw new SessionException(ExceptionType.AUTH);
                }
            } else {
                throw new FormatException("id", "mail", "password", "loginKey");
            }

        } catch (Exception e) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
            response.getHeaders().add("error", e.toString());
        }

        return response;
    }
    // </editor-fold>
    // <editor-fold desc="POST /session/signin" defaultstate="collapsed">
    @POST
    @Path("/signin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response customerSignIn(Customer customer) {
        Response response;
        try {
            if (customer.getMail() != null && customer.getPassword() != null) {

                List<Customer> customers = this.findAll();
                Customer founded = null;

                for (Customer c : customers) {
                    if (c.getMail().equals(customer.getMail())) {
                        founded = c;
                        break;
                    }
                }

                if (founded == null) {
                    throw new NotFoundException("customer");

                } else if (founded.getPassword().equals(customer.getPassword())) {

                    String loginKey = KeyGenerator.GenerateKey();
                    founded.setLoginKey(loginKey);
                    this.edit(founded);

                    response = Response.accepted(SimpleJson.CreateJson("loginKey", loginKey)).build();

                } else {
                    throw new SessionException();
                }
            } else {
                throw new FormatException("mail", "password");
            }

        } catch (Exception e) {
            response = Response.status(Response.Status.BAD_REQUEST).build();
            response.getHeaders().add("error", e.toString());
        }

        return response;
    }
    // </editor-fold>

}
