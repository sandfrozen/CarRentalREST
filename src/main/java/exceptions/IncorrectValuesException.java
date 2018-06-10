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
public class IncorrectValuesException extends Exception {

    private String info;

    public IncorrectValuesException(String value) {
        this.info = info + " is incorrect";
    }

    public IncorrectValuesException(String valueName, String value) {
        this.info = valueName + ": " + value + " is incorrect";
    }
    
    public IncorrectValuesException(String valueName, String value, String reason) {
        this.info = valueName + ": " + value + " is " + reason;
    }

    @Override
    public String toString() {
        return this.info;
    }
}
