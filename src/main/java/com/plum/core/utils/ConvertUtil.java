package com.plum.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 */
public class ConvertUtil {

    public static List<Long> stringConvert2LongList(String string, String ch){
        List<Long> longList = new ArrayList<Long>();
        if (null==string || string.isEmpty())
            return longList;

        String[] strArray = string.split(ch);
        for(String str: strArray){
            longList.add(Long.valueOf(str));
        }

        return longList;
    }

    public static String LongListConvert2String(List<Long> longList, String ch){
        StringBuffer stringBuffer = new StringBuffer();
        boolean ok=false;
        for(Long longNum: longList){
            stringBuffer.append(longNum);
            stringBuffer.append(ch);
            ok=true;
        }
        if (ok)
            stringBuffer.deleteCharAt(stringBuffer.length()-1);

        return stringBuffer.toString();
    }

    /** yyyy-MM-dd HH:mm:ss **/
    public static Date str2DateTime(String strDate){

        DateFormat df = DateFormat.getDateTimeInstance();
        Date date = null;
        try {
            date =  df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }

    public static String dateTime2Str(Date date){

        DateFormat df = DateFormat.getDateTimeInstance();
        return df.format(date);

    }
}
