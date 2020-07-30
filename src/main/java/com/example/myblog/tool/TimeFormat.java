package com.example.myblog.tool;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeFormat {
    /**
     * 根据日期获取 星期 （2019-05-06 ——> 星期一）
     * @param datetime
     * @return
     */
    public static Integer dateToWeek(String datetime) {

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Integer[] weekDays = {7, 1, 2, 3, 4, 5, 6};
        Calendar cal = Calendar.getInstance();
        Date date;
        try {
            date = f.parse(datetime);
            cal.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //一周的第几天
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
}
