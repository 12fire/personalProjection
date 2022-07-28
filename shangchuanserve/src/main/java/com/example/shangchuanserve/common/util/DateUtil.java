package com.example.shangchuanserve.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    public static String datefromat(int daynumber){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + daynumber);
        Date today = calendar.getTime();
//        System.out.println(today);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String format1 = format.format(today);
//        System.out.println(format1);
        return format1;
    }

    /**
     * 获取当前时间的毫秒值
     *
     * @return
     */
    public static String getSId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String date = sdf.format(new Date());
        long num = (long) (Math.random() * (999L - 100L)) + 100L;
        return date + num;
    }
}
