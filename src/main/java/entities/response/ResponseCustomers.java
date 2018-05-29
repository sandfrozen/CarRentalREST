/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.response;

import entities.Customer;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author tomek.buslowski
 */
public class ResponseCustomers extends MyResponse {
    
    @XmlElement(name = "customers")
    private List<Customer> list;

    public List<Customer> getList() {
        return list;
    }

    public void setList(List<Customer> list) {
        this.list = list;
        this.setTotalResults(list != null ? list.size() : 0);
    }

}
