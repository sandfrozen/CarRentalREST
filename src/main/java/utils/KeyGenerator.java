/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Random;

/**
 *
 * @author tomek.buslowski
 */
public class KeyGenerator {
    
    private static String POOL = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
    private static int SIZE = POOL.length();
    
    public static String GenerateKey() {
        int length = 10;
        String key = "";
        while(key.length() < 10 ) {
            Random r = new Random();
            key += POOL.charAt(r.nextInt(SIZE));
        }
        return key;
    }
}
