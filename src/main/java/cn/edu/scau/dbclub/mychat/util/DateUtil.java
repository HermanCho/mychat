package cn.edu.scau.dbclub.mychat.util;

import org.mockito.internal.verification.Times;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * @Description: DB里面的是TimeStamp字段，类中是Date字段，转换类
 * @Author: hermanCho
 * @Date: 2020-05-10
 * @Param null:
 * @return: null
 **/

public class DateUtil {

    // 另一种实现，先留下
//    private static SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    String time = TIME_FORMAT.format(date);
//    Timestamp timestamp = Timestamp.valueOf(time);

    public static Timestamp getNowTimeStamp() {
        Date date = new Date();
        return date2TimeStamp(date);
    }

    public static Timestamp date2TimeStamp(Date date) {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }


    /***
      * @Description:  给redis用的，生成score
      * @Author: hermanCho
      * @Date: 2020-05-10
      * @Param date:
      * @return: java.lang.Double
      **/

    public static Double date2Double(Date date) {
        return Double.valueOf(date.getTime());
    }


}
