/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.response;

import entities.Car;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author tomek.buslowski
 */
public class ResponseCars extends MyResponse {

    @XmlElement(name = "cars")
    private List<Car> list;

    public List<Car> getList() {
        return list;
    }

    public void setList(List<Car> list) {
        this.list = list;
        this.setTotalResults(list != null ? list.size() : 0);
    }

}
