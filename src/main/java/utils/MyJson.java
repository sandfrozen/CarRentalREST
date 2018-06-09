/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author tomek.buslowski
 */
public class MyJson {
    
    private HashMap<String, Object> map;
    
    public MyJson() {
        map = new HashMap<>();
    }
    
    public void add(String key, Object value) {
        map.put(key, value);
    }

    @Override
    public String toString() {
        String json = "{ ";
        
        for (String key : map.keySet()) {
            Object value = map.get(key);
            json += "\"" + key + "\": ";
            json += "\"" + value + "\", ";
        }
        json = json.substring(0, json.length()-2);
        
        return json + " }";
    }
}
