package com.cgl.java.tool;

import java.text.SimpleDateFormat;

/**
 * Created by chenguangliang on 9/15/15.
 */
public class Tool {
    public static void main(String[] args) throws Exception{

        long time = 1445861957389l;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date date = sdf.parse(Long.toString(time));

        System.out.println("time is :" + date);
    }
}
