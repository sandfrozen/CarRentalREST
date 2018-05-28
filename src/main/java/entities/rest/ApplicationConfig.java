/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.rest;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author tomek.buslowski
 */
@javax.ws.rs.ApplicationPath("v1")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(entities.rest.CarFacadeREST.class);
        resources.add(entities.rest.CustomerFacadeREST.class);
        resources.add(entities.rest.EmergencyFacadeREST.class);
        resources.add(entities.rest.ReservationFacadeREST.class);
    }
    
}