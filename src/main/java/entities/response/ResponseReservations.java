/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.response;

import entities.Reservation;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author tomek.buslowski
 */
public class ResponseReservations extends MyResponse {
    
    @XmlElement(name = "reservations")
    private List<Reservation> list;

    public List<Reservation> getList() {
        return list;
    }

    public void setList(List<Reservation> list) {
        this.list = list;
        this.setTotalResults(list != null ? list.size() : 0);
    }

}
