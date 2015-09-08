package com.plum.cas.utils;

import com.plum.core.utils.ConvertUtil;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Andy on 2015/7/29.
 */
public class ConvertTest {

    @Test
    public void listArrayConvertTest(){
        Long[] longs = {12L, 34L , 48L, 67L};
        List<Long> longList = Arrays.asList(longs);

        String longStr = ConvertUtil.LongListConvert2String(longList, ",");
        System.out.println(longStr);

        List<Long> longListNew = ConvertUtil.stringConvert2LongList(longStr, ",");
        System.out.println(longListNew.toString());
    }

    @Test
    public void dateStringConvertTest(){

        Date date = new Date();

        String dateStr = ConvertUtil.dateTime2Str(date);

        date = ConvertUtil.str2DateTime("2012-09-23 12:23:40");

        System.out.println(dateStr);

    }



}
