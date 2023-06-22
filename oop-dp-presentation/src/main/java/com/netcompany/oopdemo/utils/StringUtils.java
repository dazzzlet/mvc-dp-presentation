package com.netcompany.oopdemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
    public static String formatDate(Date date, String format) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        return formater.format(date);
    }
}
