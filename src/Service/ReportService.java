package Service;

import DAO.RecordDAO;
import Entity.Record;
import Util.DateUtil;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * ����ҵ����ReportService
 *
 * listThisMonthRecords()
 * ����һ�����Ѽ�¼���ϣ����豾����30�죬��ô������Ͼ���30��Record��ÿ��Record��Ӧһ��������ܽ��������û�����ѣ������ѽ��Ϊ0.
 *
 * getDaySpend()
 * ��ȡĳһ��������ܽ���������ѿ����ж�ʣ����⼸�����Ѽ���������һ��
 */

public class ReportService {
   public int getDaySpend(Date d, List<Record> monthRawData) {
       int daySpend = 0;
       for (Record record : monthRawData) {
           if (record.getDate().equals(d)) {
               daySpend += record.getSpend();
           }
       }
       return daySpend;
   }

   public List<Record> listThisMonthRecords() {
       RecordDAO dao = new RecordDAO();
       List<Record> monthRawData = dao.listThisMonth();
       List<Record> result = new ArrayList<>();
       Date monthBegin = DateUtil.monthBegin();
       int monthTotalDay = DateUtil.thisMonthTotalDay();
       Calendar calendar = Calendar.getInstance();
       for (int i = 0;i < monthTotalDay; i++) {
           Record record = new Record();
           calendar.setTime(monthBegin);
           calendar.add(Calendar.DATE, i);
           Date eachDayOfThisMonth = calendar.getTime();
           int daySpend = getDaySpend(eachDayOfThisMonth, monthRawData);
           record.setSpend(daySpend);
           result.add(record);
       }
       return result;
   }
}
