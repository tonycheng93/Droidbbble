package com.sky.droidbbble.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

import timber.log.Timber;

/**
 * Created by tonycheng on 2017/9/22.
 */

public class DateUtil {

    private DateUtil() {
        //utility class,no instance
    }

    /**
     * 时间显示格式要求：
     * 1、小于等于1分钟，显示 about one minute ago;
     * 2、大于1分钟，小于等于1小时，显示 about xxx minutes ago;
     * 3、大于1小时，小于等于24小时，显示 about xxx hours ago;
     * 4、大于24小时，小于等于48小时，显示 about one day ago;
     * 5、大于48小时，小于等于72小时，显示 about two days ago;
     * 6、大于72小时，小于等于96小时，显示 about three days ago;
     * 7、大于96小时，显示 yyyy-MM-dd,eg:2017-09-21
     */
    /**
     * 转换时间戳为指定格式
     *
     * @param timeStamp 时间戳
     * @return 指定时间格式
     */
    public static String parseTime(long timeStamp) {
        String result = "";
        long millis = timeStamp;
        long currentMillis = System.currentTimeMillis();
        long time = currentMillis - millis;
        Timber.d("millis = " + millis + ", currentMillis = " + currentMillis + ", time = " + time);
        if (time > 0 && time <= 60) {
            result = "about one minute ago";
        } else if (time > 60 && time <= 3600) {
            final int minute = (int) (time / 60);
            result = "about " + minute + " minutes ago";
        } else if (time > 3600 && time <= 24 * 60 * 60) {
            final int hours = (int) (time / 3600);
            result = "about " + hours + " hours ago";
        } else if ((time > (24 * 60 * 60)) && ((time <= (2 * 24 * 60 * 60)))) {
            result = "about one day ago";
        } else if ((time > (2 * 24 * 60 * 60))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            result = sdf.format(timeStamp);
        }
        return result;
    }
}
