package entities;

import entities.Reservation;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-28T13:10:26")
@StaticMetamodel(Customer.class)
public class Customer_ { 

    public static volatile SingularAttribute<Customer, String> password;
    public static volatile SingularAttribute<Customer, String> mail;
    public static volatile SingularAttribute<Customer, String> surname;
    public static volatile SingularAttribute<Customer, Date> lastUpdate;
    public static volatile SingularAttribute<Customer, String> name;
    public static volatile CollectionAttribute<Customer, Reservation> reservationCollection;
    public static volatile SingularAttribute<Customer, Integer> id;

}