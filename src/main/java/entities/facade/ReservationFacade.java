/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities.facade;

import entities.Reservation;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tomek.buslowski
 */
public class ReservationFacade extends AbstractFacade<Reservation> {

    @PersistenceContext(unitName = "com.carrental_CarRentalREST_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    private static ReservationFacade instance;

    public static ReservationFacade getInstance() {
        if ( instance == null ) {
            instance = new ReservationFacade();
        }
        return instance;
    }

    public ReservationFacade() {
        super(Reservation.class);
        instance = this;
    }

    @Override
    public List<Reservation> findAll() {
        return super.findAll();
    }

    public Reservation find(Integer id) {
        return super.find(id);
    }

    @Override
    public void create(Reservation entity) {
        super.create(entity);
    }

    @Override
    public void edit(Reservation entity) {
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
