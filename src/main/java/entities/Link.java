/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javax.ejb.Singleton;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tomek.buslowski
 */
//@XmlRootElement
public class Link {
    
    private String rel;
    private String link;

    public Link() {
    }

    public Link(String rel, String link) {
        this.rel = rel;
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }
    
    
    
}
