/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.facade;

import entities.Emergency;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tomek.buslowski
 */
public class EmergencyFacade extends AbstractFacade<Emergency> {

    @PersistenceContext(unitName = "com.carrental_CarRentalREST_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public EmergencyFacade() {
        super(Emergency.class);
    }

    @Override
    public List<Emergency> findAll() {
        return super.findAll();
    }

    public Emergency find(Integer id) {
        return super.find(id);
    }

    @Override
    public void create(Emergency entity) {
        super.create(entity);
    }

    @Override
    public void edit(Emergency entity) {
        super.edit(entity);
    }

    public void remove(Integer id) {
        super.remove(super.find(id));
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}
