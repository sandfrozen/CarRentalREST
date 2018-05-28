/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.response;

import entities.Car;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author tomek.buslowski
 */
public class ResponseCar extends Response {

    @XmlElement(name = "Car")
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        if ( car != null ) {
            this.car = car;
            this.totalResults = 1;
        } else {
            this.setStatus("error");
            this.totalResults = 0;
        } 
    }
}
