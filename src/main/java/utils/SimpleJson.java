/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author tomek.buslowski
 */
public class SimpleJson {
    
    public static String CreateJson(String key, String value) {
        return "{ \"" + key + "\": \""+ value + "\" }"; 
    }
}
