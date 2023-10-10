package Gui.listener;


import Entity.Category;
import Entity.Record;
import Gui.panel.CategoryPanel;
import Gui.panel.DetailPanel;
import Gui.panel.DetailSetPanel;
import Gui.panel.MainPanel;
import Service.CategoryService;
import Service.RecordService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * 1.���ж����Ƿ��м�¼��û���򷵻�
 * 2.�������Ϊ0
 * 3.����RecordService��update���¼�¼
 * ���ڼ���DetailPanel�еı༭��ɾ������
 * ����༭�������򣬽�����������ʾ�������
 * ���ɾ������ѡ�еļ�¼ɾ��
 */
public class DetailListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        DetailPanel detailPanel = DetailPanel.instance;
        DetailSetPanel detailSetPanel = DetailSetPanel.instance;
        JButton button = (JButton) e.getSource();//��ȡ����İ�ť
        if (detailPanel.dtm.rs.size() == 0) {
            JOptionPane.showMessageDialog(detailPanel,"�������Ѽ�¼���޷��޸�");
            MainPanel.instance.workingPanel.show(CategoryPanel.instance);
            return;
        }
        if (button == detailPanel.bEdit) {
            Util.GUIUtil.showPanelNew(detailSetPanel);
        }
        if (button == detailPanel.bDelete) {
            Record record = detailPanel.getSelectedRecord();
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(detailPanel, "ȷ��ɾ����")) return;
            int id = record.getId();
            System.out.println(id);
            new RecordService().delete(id);
        }
        detailPanel.updateData(CategoryPanel.instance.getSelectedCategory());
    }
}
