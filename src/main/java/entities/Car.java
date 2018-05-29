/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tomek.buslowski
 */
@Entity
@Table(name = "CAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c")
    , @NamedQuery(name = "Car.findById", query = "SELECT c FROM Car c WHERE c.id = :id")
    , @NamedQuery(name = "Car.findByBrand", query = "SELECT c FROM Car c WHERE c.brand = :brand")
    , @NamedQuery(name = "Car.findByModel", query = "SELECT c FROM Car c WHERE c.model = :model")
    , @NamedQuery(name = "Car.findByDoors", query = "SELECT c FROM Car c WHERE c.doors = :doors")
    , @NamedQuery(name = "Car.findByFuelcap", query = "SELECT c FROM Car c WHERE c.fuelcap = :fuelcap")
    , @NamedQuery(name = "Car.findByFueltype", query = "SELECT c FROM Car c WHERE c.fueltype = :fueltype")
    , @NamedQuery(name = "Car.findByRange", query = "SELECT c FROM Car c WHERE c.range = :range")
    , @NamedQuery(name = "Car.findByGearbox", query = "SELECT c FROM Car c WHERE c.gearbox = :gearbox")
    , @NamedQuery(name = "Car.findByGears", query = "SELECT c FROM Car c WHERE c.gears = :gears")
    , @NamedQuery(name = "Car.findByDaycost", query = "SELECT c FROM Car c WHERE c.daycost = :daycost")
    , @NamedQuery(name = "Car.findByImageurl", query = "SELECT c FROM Car c WHERE c.imageurl = :imageurl")
    , @NamedQuery(name = "Car.findByLastUpdate", query = "SELECT c FROM Car c WHERE c.lastUpdate = :lastUpdate")})
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "BRAND")
    private String brand;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "MODEL")
    private String model;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DOORS")
    private int doors;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FUELCAP")
    private int fuelcap;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "FUELTYPE")
    private String fueltype;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RANGE")
    private int range;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "GEARBOX")
    private String gearbox;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GEARS")
    private int gears;
    @Basic(optional = false)
    @NotNull
    @Column(name = "DAYCOST")
    private double daycost;
    @Size(max = 255)
    @Column(name = "IMAGEURL")
    private String imageurl;
    @Lob
    @Column(name = "PHOTO")
    private Serializable photo;
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "carId")
    private Collection<Reservation> reservationCollection;

    public Car() {
    }

    public Car(Integer id) {
        this.id = id;
    }

    public Car(Integer id, String brand, String model, int doors, int fuelcap, String fueltype, int range, String gearbox, int gears, double daycost) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.doors = doors;
        this.fuelcap = fuelcap;
        this.fueltype = fueltype;
        this.range = range;
        this.gearbox = gearbox;
        this.gears = gears;
        this.daycost = daycost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getDoors() {
        return doors;
    }

    public void setDoors(int doors) {
        this.doors = doors;
    }

    public int getFuelcap() {
        return fuelcap;
    }

    public void setFuelcap(int fuelcap) {
        this.fuelcap = fuelcap;
    }

    public String getFueltype() {
        return fueltype;
    }

    public void setFueltype(String fueltype) {
        this.fueltype = fueltype;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public String getGearbox() {
        return gearbox;
    }

    public void setGearbox(String gearbox) {
        this.gearbox = gearbox;
    }

    public int getGears() {
        return gears;
    }

    public void setGears(int gears) {
        this.gears = gears;
    }

    public double getDaycost() {
        return daycost;
    }

    public void setDaycost(double daycost) {
        this.daycost = daycost;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public Serializable getPhoto() {
        return photo;
    }

    public void setPhoto(Serializable photo) {
        this.photo = photo;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @XmlTransient
    public Collection<Reservation> getReservationCollection() {
        return reservationCollection;
    }

    public void setReservationCollection(Collection<Reservation> reservationCollection) {
        this.reservationCollection = reservationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Car[ id=" + id + " ]";
    }
    
//    public void merge(Car newc) {
//        // doesn't modifies id and login_key
//        this.brand = newc.brand != null ? newc.brand : this.brand;
//        this.model = newc.model != null ? newc.model : this.model;
//        this.doors = newc.doors > 0 ? newc.doors : this.doors;
//        this.fuelcap = newc.fuelcap > 0 ? newc.fuelcap : this.fuelcap;
//        this.fueltype = newc.fueltype != null ? newc.fueltype : this.fueltype;
//        this.range = newc.range > 0 ? newc.range : this.range;
//        this.gearbox = newc.fueltype != null ? newc.fueltype : this.fueltype;
//        this.lastUpdate = new Timestamp(System.currentTimeMillis());
//    }
}
