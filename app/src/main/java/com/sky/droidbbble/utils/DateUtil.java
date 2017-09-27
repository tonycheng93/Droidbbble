package com.sky.droidbbble.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

import timber.log.Timber;

/**
 * Created by tonycheng on 2017/9/22.
 */

public class DateUtil {

    private static final int TIME_MINUTE = 60;
    private static final int TIME_HOUR = 60 * TIME_MINUTE;
    private static final int TIME_DAY = 24 * TIME_HOUR;

    private DateUtil() {
        //utility class,no instance
    }

    /**
     * 时间显示格式要求：
     * 1、小于等于1分钟，显示 about one minute ago;
     * 2、大于1分钟，小于等于1小时，显示 about xxx minutes ago;
     * 3、大于1小时，小于等于24小时，显示 about xxx hours ago;
     * 4、大于24小时，小于等于48小时，显示 about one day ago;
     * 5、大于48小时，显示 yyyy-MM-dd,eg:2017-09-21
     */
    /**
     * 转换时间戳为指定格式
     *
     * @param timeStamp 时间戳
     * @return 指定时间格式
     */
    public static String parseTime(long timeStamp) {
        String result = "";
        int millis = (int) (timeStamp / 1000);
        int currentMillis = (int) (System.currentTimeMillis() / 1000);
        int time = currentMillis - millis;
        Timber.d("millis = " + millis + ", currentMillis = " + currentMillis + ", time = " + time);
        if (time > 0 && time <= TIME_MINUTE) {
            result = "about one minute ago";
        } else if (time > TIME_MINUTE && time <= TIME_HOUR) {
            final int minute = time / TIME_MINUTE;
            result = "about " + minute + " minutes ago";
        } else if (time > TIME_HOUR && time <= TIME_DAY) {
            final int hours = time / TIME_HOUR;
            result = "about " + hours + " hours ago";
        } else if (time > TIME_DAY && time <= (2 * TIME_DAY)) {
            result = "a day ago";
        } else if (time > (2 * TIME_DAY)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            result = sdf.format(timeStamp);
        }
        return result;
    }
}
