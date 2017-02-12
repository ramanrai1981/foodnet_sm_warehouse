/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.food.farmer.domain.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sumit.garg
 */
public class DateUtility {

    public static Date getCutrrentDateTime() {
        Date date = new Date();
        try {
            Calendar currentdate = Calendar.getInstance();
            String strdate = null;
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            strdate = formatter.format(currentdate.getTime());
            TimeZone obj = TimeZone.getTimeZone("UTC+5:30");
            formatter.setTimeZone(obj);
            date = formatter.parse(strdate);
        } catch (ParseException ex) {
            Logger.getLogger(DateUtility.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }
}
