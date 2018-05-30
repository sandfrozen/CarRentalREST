/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tomek.buslowski
 */
@Entity
@Table(name = "EMERGENCY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Emergency.findAll", query = "SELECT e FROM Emergency e")
    , @NamedQuery(name = "Emergency.findById", query = "SELECT e FROM Emergency e WHERE e.id = :id")
    , @NamedQuery(name = "Emergency.findByLat", query = "SELECT e FROM Emergency e WHERE e.lat = :lat")
    , @NamedQuery(name = "Emergency.findByLon", query = "SELECT e FROM Emergency e WHERE e.lon = :lon")
    , @NamedQuery(name = "Emergency.findByMessage", query = "SELECT e FROM Emergency e WHERE e.message = :message")
    , @NamedQuery(name = "Emergency.findByActual", query = "SELECT e FROM Emergency e WHERE e.actual = :actual")
    , @NamedQuery(name = "Emergency.findByReported", query = "SELECT e FROM Emergency e WHERE e.reported = :reported")
    , @NamedQuery(name = "Emergency.findByLastUpdate", query = "SELECT e FROM Emergency e WHERE e.lastUpdate = :lastUpdate")})
public class Emergency implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "LAT")
    private double lat;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "LON")
    private double lon;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "MESSAGE")
    private String message;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTUAL")
    private Boolean actual;
    
    @Column(name = "REPORTED")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reported;
    
    @Column(name = "LAST_UPDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    
    @JoinColumn(name = "RESERVATION_ID", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    @XmlElement(name="reservation")
    private Reservation reservation;

    public Emergency() {
    }

    public Emergency(Integer id) {
        this.id = id;
    }

    public Emergency(Integer id, double lat, double lon, String message, Boolean actual) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.message = message;
        this.actual = actual;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getActual() {
        return actual;
    }

    public void setActual(Boolean actual) {
        this.actual = actual;
    }

    public Date getReported() {
        return reported;
    }

    public void setReported(Date reported) {
        this.reported = reported;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @XmlTransient
    public Reservation getReservation() {
        return reservation;
    }
    
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
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
        if (!(object instanceof Emergency)) {
            return false;
        }
        Emergency other = (Emergency) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Emergency[ id=" + id + " ]";
    }
    
}
