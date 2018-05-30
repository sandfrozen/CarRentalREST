/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.response;

import entities.Emergency;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author tomek.buslowski
 */
public class ResponseEmergencies extends MyResponse {
    
    @XmlElement(name = "emergencies")
    private List<Emergency> list;

    public List<Emergency> getList() {
        return list;
    }

    public void setList(List<Emergency> list) {
        this.list = list;
        this.setTotalResults(list != null ? list.size() : 0);
    }

}
