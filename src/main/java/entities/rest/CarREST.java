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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
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
import javax.ws.rs.QueryParam;
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
    // brand=Volvo&model=V40&color=white&gearbox=A&fuelType=D&drive=2x4&yearMin=2010&yearMax=2018&fuelCapMin=20&fuelCapMax=20&bootMin=60&bootMax=60&rangeMin=350&rangeMax=350&doorsMin=4&doorsMax=4&gearsMin=5&gearsMax=5&dayCostMin=29.99&dayCostMax=44.90
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllRest(@QueryParam("brand") String brand,
            @QueryParam("model") String model,
            @QueryParam("color") String color,
            @QueryParam("gearbox") String gearbox,
            @QueryParam("fuelType") String fuelType,
            @QueryParam("drive") String drive,
            @QueryParam("yearMin") int yearMin,
            @QueryParam("yearMax") int yearMax,
            @QueryParam("fuelCapMin") int fuelCapMin,
            @QueryParam("fuelCapMax") int fuelCapMax,
            @QueryParam("bootMin") int bootMin,
            @QueryParam("bootMax") int bootMax,
            @QueryParam("rangeMin") int rangeMin,
            @QueryParam("rangeMax") int rangeMax,
            @QueryParam("doorsMin") int doorsMin,
            @QueryParam("doorsMax") int doorsMax,
            @QueryParam("gearsMin") int gearsMin,
            @QueryParam("gearsMax") int gearsMax,
            @QueryParam("dayCostMin") double dayCostMin,
            @QueryParam("dayCostMax") double dayCostMax,
            @QueryParam("fromDate") String fromDate,
            @QueryParam("toDate") String toDate) {
        ResponseCars responseCars = new ResponseCars();
        try {
            List<Car> allCars = this.findAll();
            if (allCars == null) {
                throw new NotFoundException("cars");
            }
            List<Car> fileredList = new LinkedList<>();
            if (brand != null) {
                for (Car c : allCars) {
                    if (c.getBrand().equals(brand)) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (model != null) {
                for (Car c : allCars) {
                    if (c.getModel().equals(model)) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (color != null) {
                for (Car c : allCars) {
                    if (c.getColor().equals(color)) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (gearbox != null) {
                for (Car c : allCars) {
                    if (c.getGearbox().equals(gearbox)) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (fuelType != null) {
                for (Car c : allCars) {
                    if (c.getFueltype().equals(fuelType)) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (drive != null) {
                for (Car c : allCars) {
                    if (c.getDrive().equals(drive)) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (yearMin > 0) {
                for (Car c : allCars) {
                    if (c.getYearprod() >= yearMin) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (yearMax > 0) {
                for (Car c : allCars) {
                    if (c.getYearprod() <= yearMax) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (fuelCapMin > 0) {
                for (Car c : allCars) {
                    if (c.getFuelcap() >= fuelCapMin) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (fuelCapMax > 0) {
                for (Car c : allCars) {
                    if (c.getFuelcap() <= fuelCapMax) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (bootMin > 0) {
                for (Car c : allCars) {
                    if (c.getBoot() >= bootMin) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (bootMax > 0) {
                for (Car c : allCars) {
                    if (c.getBoot() <= bootMax) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (rangeMin > 0) {
                for (Car c : allCars) {
                    if (c.getRange() >= rangeMin) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (rangeMax > 0) {
                for (Car c : allCars) {
                    if (c.getRange() <= rangeMax) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (doorsMin > 0) {
                for (Car c : allCars) {
                    if (c.getRange() >= doorsMin) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (doorsMax > 0) {
                for (Car c : allCars) {
                    if (c.getDoors() <= doorsMax) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (gearsMin > 0) {
                for (Car c : allCars) {
                    if (c.getGears() >= gearsMin) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (gearsMax > 0) {
                for (Car c : allCars) {
                    if (c.getGears() <= gearsMax) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (dayCostMin > 0) {
                for (Car c : allCars) {
                    if (c.getDaycost() >= dayCostMin) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (dayCostMax > 0) {
                for (Car c : allCars) {
                    if (c.getDaycost() <= dayCostMax) {
                        fileredList.add(c);
                    }
                }
                allCars = fileredList;
                fileredList = new LinkedList<>();
            }
            if (fromDate != null && toDate != null &&!allCars.isEmpty()) {
                String fromString = fromDate + "T06:00:00.000+02:00";
                String toString = toDate + "T22:00:00.000+02:00";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
                Date searchedFromDate = sdf.parse(fromString);
                Date searchedToDate = sdf.parse(toString);

                ReservationFacade reservationFacade = ReservationFacade.getInstance();
                List<Reservation> allReservations = reservationFacade.findAll();

                for (Reservation r : allReservations) {
                    if( r.getToDate().before(searchedFromDate) || searchedToDate.before(r.getFromDate()) ) {
                        if( allCars.contains(r.getCar()) ) {
                            // it's ok
                        }
                    } else {
                        if( allCars.contains(r.getCar()) ) {
                            allCars.remove(r.getCar());
                        }
                    }
                }
            }

            responseCars.setList(allCars);
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
