package com.sky.droidbbble.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by tonycheng on 2017/9/26.
 */
public class DateUtilTest {
    @Test
    public void parseTime() throws Exception {
        long timeStamp = 1506390795000L;
        final String str = DateUtil.parseTime(timeStamp);
        System.out.print(str);
    }

}