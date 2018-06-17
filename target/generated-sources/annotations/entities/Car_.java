package entities;

import entities.Reservation;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-06-17T19:40:26")
@StaticMetamodel(Car.class)
public class Car_ { 

    public static volatile SingularAttribute<Car, String> fueltype;
    public static volatile SingularAttribute<Car, String> color;
    public static volatile SingularAttribute<Car, Double> daycost;
    public static volatile SingularAttribute<Car, Integer> range;
    public static volatile SingularAttribute<Car, Serializable> photo;
    public static volatile SingularAttribute<Car, Integer> doors;
    public static volatile SingularAttribute<Car, Integer> yearprod;
    public static volatile CollectionAttribute<Car, Reservation> reservations;
    public static volatile SingularAttribute<Car, String> imageurl;
    public static volatile SingularAttribute<Car, Date> lastUpdate;
    public static volatile SingularAttribute<Car, Integer> fuelcap;
    public static volatile SingularAttribute<Car, String> model;
    public static volatile SingularAttribute<Car, Integer> id;
    public static volatile SingularAttribute<Car, String> gearbox;
    public static volatile SingularAttribute<Car, Integer> boot;
    public static volatile SingularAttribute<Car, String> drive;
    public static volatile SingularAttribute<Car, String> brand;
    public static volatile SingularAttribute<Car, Integer> gears;

}