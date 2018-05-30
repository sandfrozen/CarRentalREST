package entities;

import entities.Reservation;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-05-30T15:40:10")
@StaticMetamodel(Emergency.class)
public class Emergency_ { 

    public static volatile SingularAttribute<Emergency, Boolean> actual;
    public static volatile SingularAttribute<Emergency, Date> lastUpdate;
    public static volatile SingularAttribute<Emergency, Date> reported;
    public static volatile SingularAttribute<Emergency, Reservation> reservation;
    public static volatile SingularAttribute<Emergency, Double> lon;
    public static volatile SingularAttribute<Emergency, Integer> id;
    public static volatile SingularAttribute<Emergency, String> message;
    public static volatile SingularAttribute<Emergency, Double> lat;

}