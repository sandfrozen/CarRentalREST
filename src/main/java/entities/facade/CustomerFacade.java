/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.facade;

import entities.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tomek.buslowski
 */
public class CustomerFacade extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "com.carrental_CarRentalREST_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public CustomerFacade() {
        super(Customer.class);
    }

    @Override
    public List<Customer> findAll() {
        return super.findAll();
    }

    public Customer find(Integer id) {
        return super.find(id);
    }

    @Override
    public void create(Customer entity) {
        super.create(entity);
    }

    @Override
    public void edit(Customer entity) {
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
