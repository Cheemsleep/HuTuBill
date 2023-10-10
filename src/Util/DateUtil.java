package Util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    static long millisecondOfOneDay = 1000*60*60*24;//一天的毫秒数


    //把java.util.Date类转为java.sql.Date类（JDBC中插入的时间为java.sql.Date类，日期控件获取的时间为java.util.Date类
    public static java.sql.Date util2sql(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * 获取今天并且把时，分，秒和毫秒都置0. 因为通过日期控件获取到的日期，就是没有时分秒和毫秒的
     * @return
     */
    public static Date today() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        return calendar.getTime();
    }

    /**
     * 获取月初,使用Calendar类获取每月第一天，在使用消费一览功能时，需要从数据库获取本月初到月末的消费记录，进行统计
     * @return
     */
    public static Date monthBegin() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE,1);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);

        return calendar.getTime();
    }

    /**
     * 获取月末方法
     */
    public static Date monthEnd() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);

        calendar.set(Calendar.DATE,1);
        calendar.add(Calendar.MONTH,1);
        calendar.add(Calendar.DATE,-2);

        return calendar.getTime();
    }

    /**
     * 获取本月天数
     * @return
     */
    public static int thisMonthTotalDay() {
        long lastDayMilliSeconds = monthEnd().getTime();
        long firstDayMilliSeconds = monthBegin().getTime();

        return (int) ((lastDayMilliSeconds-firstDayMilliSeconds)/millisecondOfOneDay)+1;
    }

    public static int thisMonthLeftDay(){
        long lastDayMilliSeconds = monthEnd().getTime();
        long toDayMilliSeconds = today().getTime();
        return (int) ((lastDayMilliSeconds-toDayMilliSeconds)/millisecondOfOneDay) +1;
    }

    /**
     * TEST
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(DateUtil.today());
        System.out.println(DateUtil.monthBegin());
        System.out.println(DateUtil.monthEnd());
        System.out.println(thisMonthLeftDay());
        System.out.println(thisMonthTotalDay());
    }

}
