package Gui.listener;

import Entity.Category;
import Gui.panel.CategoryPanel;
import Gui.panel.DetailPanel;
import Service.CategoryService;
import Util.CenterPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * CategoryListener�����������ӣ��༭��ɾ����ť��ʹ�������������
 *
 * 1. �������ToolBarListener ���Ƶģ������¼��ķ���Դ���ж����ĸ���ť�����������������������Ӧ�Ĺ��ܡ�
 *
 * 2. ��������ӣ����������У���������ݲ�Ϊ�պ�ͨ��CategoryService.add()��ӵ����ݿ⡣
 *
 * 3. ����Ǳ༭�����������У���������ݲ�Ϊ�պ󣬸���CategoryPanel��getSelectedCategory()��ȡid, Ȼ����ͨ��CategoryService.update���µ����ݿ�
 *
 * 4. �����ɾ���������ж��Ƿ������Ѽ�¼����������Ѽ�¼������ɾ���� ���Ž���ɾ��ȷ����ʾ��ȷ�Ϻ�ͨ��CategoryService.delete()����ɾ����
 *
 * 5. ������p.updateData();��������
 */
public class CategoryListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        CategoryPanel panel = CategoryPanel.instance;
        DetailPanel detailPanel = DetailPanel.instance;
        JButton button = (JButton) e.getSource();
        if (button == panel.bAdd) {
            String name = JOptionPane.showInputDialog(null);
            if (name == null) {
                JOptionPane.showMessageDialog(panel,"�������Ʋ���Ϊ��");
                return;
            }
            new CategoryService().add(name);
        }
        if (button == panel.bEdit) {
            Category c = panel.getSelectedCategory();
            int id = c.getId();
            String name = JOptionPane.showInputDialog("�޸ķ�������",c.getName());
            if (name == null) {
                JOptionPane.showMessageDialog(panel,"�������Ʋ���Ϊ��");
                return;
            }
            new CategoryService().update(id,name);
        }
        if (button == panel.bDelete) {
            Category c = panel.getSelectedCategory();
            if (c.getRecordNumber() != 0) {
                JOptionPane.showMessageDialog(panel,"�������´������Ѽ�¼���޷�ɾ��");
                return;
            }
            if (JOptionPane.OK_OPTION != JOptionPane.showConfirmDialog(panel,"ȷ��ɾ��?")) return;
            int id = c.getId();
            new CategoryService().delete(id);
        }
        if (button == panel.bDetail) {
            //��Ҫ�������¼��󶨣�������Զ��ȡ���ǵ�һ������
            Category c = panel.getSelectedCategory();
            if (c.getRecordNumber() == 0) {
                JOptionPane.showMessageDialog(panel,"û�����Ѽ�¼���޷��鿴��ϸ");
                return;
            }
            detailPanel.updateData(c);
            Util.GUIUtil.showPanelNew(detailPanel,1);
        }
        panel.updateData();
    }
}
