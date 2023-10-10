package Service;

import DAO.RecordDAO;
import Entity.Record;
import Gui.page.SpendPage;
import Util.DateUtil;

import java.util.List;

public class SpendService {
    public SpendPage getSpendPage() {
        RecordDAO recordDAO = new RecordDAO();
        //本月数据
        List<Record> thisMonthRecords = recordDAO.listThisMonth();
        //今日数据
        List<Record> todayRecords = recordDAO.listToday();
        //本月总天数
        int thisMonthTotalDay = DateUtil.thisMonthTotalDay();

        int monthSpend = 0;
        int todaySpend = 0;
        int avgSpendPerDay = 0;
        int monthAvailable = 0;
        int dayAvgAvailable = 0;
        int monthLeftDay = 0;
        int usagePercentage = 0;

        //预算
        int monthBudget = new ConfigService().getIntBudget();

        //统计今日消费
        for (Record record : todayRecords) {
            todaySpend += record.getSpend();
        }
        //统计本月消费 漏写导致无法正常显示
        for (Record record : thisMonthRecords) {
            monthSpend += record.getSpend();
        }
        //计算日均消费
        avgSpendPerDay = monthSpend / thisMonthTotalDay;
        //计算本月剩余
        monthAvailable = monthBudget - monthSpend;

        //距离月末
        monthLeftDay = DateUtil.thisMonthTotalDay();

        //计算日均可用
        dayAvgAvailable = monthAvailable / monthLeftDay;

        //计算使用比例
        usagePercentage = monthSpend * 100 / monthBudget;

        //生成SpendPage对象
        return new SpendPage(monthSpend,todaySpend,avgSpendPerDay,monthAvailable,dayAvgAvailable,monthLeftDay,usagePercentage);
    }
}
