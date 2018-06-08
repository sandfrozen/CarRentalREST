/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;

import java.util.Arrays;

/**
 *
 * @author tomek.buslowski
 */
public class FormatException extends Exception {
    
    private String info;

    public FormatException(String... values) {
        this.info = "Incorrect format. Required values: " + Arrays.toString(values);
    }

    @Override
    public String toString() {
        return this.info;
    }
}
