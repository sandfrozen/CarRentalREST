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
public class SessionException extends Exception {
    
    private String info;
    
    public enum ExceptionType {
        AUTH,
        SignIn,
        SignUp
    }
    
    public SessionException() {
        this.info = "incorrect password";
    }
    
    public SessionException(ExceptionType t) {
        this.info = "customer not " + t;
    }

    public SessionException(String info, ExceptionType t) {
        this.info = info + " not " + t;
    }

    @Override
    public String toString() {
        return this.info;
    }
}
