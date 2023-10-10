package Gui.page;

/**
 * ����һ��ר������SpendPanel ����һ����� ��ҳ���࣬���仰˵������һ���������Ҫʲô��Ϣ�������ͷ�װʲô��Ϣ��
 */

public class SpendPage {
    //��������
    public String monthSpend;
    //��������
    public String todaySpend;
    //�վ�����
    public String avgSpendPerDay;
    //����ʣ��
    public String monthAvailable;
    //�վ�����
    public String dayAvgAvailable;
    //������ĩ
    public String monthLeftDay;
    //ʹ�ñ���
    public int usagePercentage;
    //�Ƿ�֧
    public boolean isOverSpend = false;

    public SpendPage(int monthSpend, int todaySpend, int avgSpendPerDay, int monthAvailable, int dayAvgAvailable,
                     int monthLeftDay, int usagePercentage) {
        this.monthSpend = "RMB:" + monthSpend;
        this.todaySpend = "RMB:" + todaySpend;
        this.avgSpendPerDay = "RMB:" + avgSpendPerDay;
        if (monthAvailable < 0) isOverSpend = true;
        if (!isOverSpend) {
            this.monthAvailable = "RMB:" + monthAvailable;
            this.dayAvgAvailable = "RMB:" + dayAvgAvailable;
        } else {
            this.monthAvailable = "�ѳ�֧" + (0 - monthAvailable);
            this.dayAvgAvailable = "RMB:0";
        }
        this.monthLeftDay = monthLeftDay + "��";
        this.usagePercentage = usagePercentage;
    }
}
