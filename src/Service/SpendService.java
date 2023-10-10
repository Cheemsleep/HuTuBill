package Service;

import DAO.RecordDAO;
import Entity.Record;
import Gui.page.SpendPage;
import Util.DateUtil;

import java.util.List;

public class SpendService {
    public SpendPage getSpendPage() {
        RecordDAO recordDAO = new RecordDAO();
        //��������
        List<Record> thisMonthRecords = recordDAO.listThisMonth();
        //��������
        List<Record> todayRecords = recordDAO.listToday();
        //����������
        int thisMonthTotalDay = DateUtil.thisMonthTotalDay();

        int monthSpend = 0;
        int todaySpend = 0;
        int avgSpendPerDay = 0;
        int monthAvailable = 0;
        int dayAvgAvailable = 0;
        int monthLeftDay = 0;
        int usagePercentage = 0;

        //Ԥ��
        int monthBudget = new ConfigService().getIntBudget();

        //ͳ�ƽ�������
        for (Record record : todayRecords) {
            todaySpend += record.getSpend();
        }
        //ͳ�Ʊ������� ©д�����޷�������ʾ
        for (Record record : thisMonthRecords) {
            monthSpend += record.getSpend();
        }
        //�����վ�����
        avgSpendPerDay = monthSpend / thisMonthTotalDay;
        //���㱾��ʣ��
        monthAvailable = monthBudget - monthSpend;

        //������ĩ
        monthLeftDay = DateUtil.thisMonthTotalDay();

        //�����վ�����
        dayAvgAvailable = monthAvailable / monthLeftDay;

        //����ʹ�ñ���
        usagePercentage = monthSpend * 100 / monthBudget;

        //����SpendPage����
        return new SpendPage(monthSpend,todaySpend,avgSpendPerDay,monthAvailable,dayAvgAvailable,monthLeftDay,usagePercentage);
    }
}
