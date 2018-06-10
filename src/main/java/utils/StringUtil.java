/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tomek.buslowski
 */
public class StringUtil {

    public static String Capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    private static final String MAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    public static boolean IsMailFormat(String mail) {
        Pattern pattern = Pattern.compile(MAIL_PATTERN);
        Matcher matcher;
        
        matcher = pattern.matcher(mail);
        return matcher.matches();

    }

}
