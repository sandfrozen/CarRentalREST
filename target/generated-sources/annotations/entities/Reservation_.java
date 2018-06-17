package entities;

import entities.Car;
import entities.Customer;
import entities.Emergency;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-17T20:59:42")
@StaticMetamodel(Reservation.class)
public class Reservation_ { 

    public static volatile SingularAttribute<Reservation, Date> fromDate;
    public static volatile CollectionAttribute<Reservation, Emergency> emergencies;
    public static volatile SingularAttribute<Reservation, Car> car;
    public static volatile SingularAttribute<Reservation, Date> toDate;
    public static volatile SingularAttribute<Reservation, Date> lastUpdate;
    public static volatile SingularAttribute<Reservation, Integer> id;
    public static volatile SingularAttribute<Reservation, Customer> customer;

}