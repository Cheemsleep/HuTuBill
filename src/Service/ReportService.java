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
 * 报表业务类ReportService
 *
 * listThisMonthRecords()
 * 返回一个消费记录集合，假设本月有30天，那么这个集合就有30条Record。每个Record对应一天的消费总金额，如果那天没有消费，则消费金额为0.
 *
 * getDaySpend()
 * 获取某一天的消费总金额，这天的消费可能有多笔，把这几笔消费加起来算在一起。
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
