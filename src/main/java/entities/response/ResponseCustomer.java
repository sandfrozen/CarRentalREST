/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.response;

import entities.Customer;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author tomek.buslowski
 */
public class ResponseCustomer extends MyResponse {

    @XmlElement(name = "customer")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.setTotalResults(customer != null ? 1 : 0);
    }

}
