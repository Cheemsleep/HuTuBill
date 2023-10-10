package Gui.listener;

import Entity.Category;
import Gui.panel.CategoryPanel;
import Gui.panel.MainPanel;
import Gui.panel.RecordPanel;
import Gui.panel.SpendPanel;
import Service.RecordService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * �������ϵ�"��һ�ʡ� ��ť��Ӽ���
 * 1. �����ж��Ƿ������ѷ�����Ϣ�����û����ʾ���������ѷ���
 * 2. ����Ľ���Ϊ0
 * 3. ����RecordService��add������Ѽ�¼
 * 4. ��ʾ��ӳɹ�
 * 5. ��ӳɹ����л�������һ��
 */
public class RecordListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RecordPanel recordPanel = RecordPanel.instance;
        if (recordPanel.cbModel.cs.size() == 0) {
            JOptionPane.showMessageDialog(recordPanel,"�������ѷ��࣬�޷���ӣ���������ѷ���");
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
            return;
        }
        if (!Util.GUIUtil.checkZero(recordPanel.tfSpend, "���ѽ��")) return;
        int spend = Integer.parseInt(recordPanel.tfSpend.getText());
        Category c = recordPanel.getSelectedCategory();
        String comment = recordPanel.tfComment.getText();
        Date date = recordPanel.datePicker.getDate();
        new RecordService().add(spend, c, comment, date);
        JOptionPane.showMessageDialog(recordPanel, "��ӳɹ�");

        MainPanel.instance.workingPanel.show(SpendPanel.instance);
    }
}
