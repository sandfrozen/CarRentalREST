/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

/**
 *
 * @author tomek.buslowski
 */
public class NotFoundException extends Exception {

    private String info;

    public NotFoundException(String info) {
        this.info = info + " not found";
    }

    public NotFoundException(String info, Integer id) {
        this.info = info + " id=" + id + " not found";
    }

    @Override
    public String toString() {
        return this.info;
    }
}
