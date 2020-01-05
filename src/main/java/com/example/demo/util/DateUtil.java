package com.example.demo.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String getDay(){
        Date date=new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-ddhhmmss");
        return format.format(date);
    }

}
