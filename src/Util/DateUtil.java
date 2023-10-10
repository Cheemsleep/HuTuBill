package Util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    static long millisecondOfOneDay = 1000*60*60*24;//һ��ĺ�����


    //��java.util.Date��תΪjava.sql.Date�ࣨJDBC�в����ʱ��Ϊjava.sql.Date�࣬���ڿؼ���ȡ��ʱ��Ϊjava.util.Date��
    public static java.sql.Date util2sql(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * ��ȡ���첢�Ұ�ʱ���֣���ͺ��붼��0. ��Ϊͨ�����ڿؼ���ȡ�������ڣ�����û��ʱ����ͺ����
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
     * ��ȡ�³�,ʹ��Calendar���ȡÿ�µ�һ�죬��ʹ������һ������ʱ����Ҫ�����ݿ��ȡ���³�����ĩ�����Ѽ�¼������ͳ��
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
     * ��ȡ��ĩ����
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
     * ��ȡ��������
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
