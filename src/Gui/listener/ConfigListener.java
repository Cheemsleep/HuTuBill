package Gui.listener;

import Gui.panel.ConfigPanel;
import Service.ConfigService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ConfigListener implements ActionListener {
    /**
     * ������ConfigListener����������������ڸ��°�ť�ϵ�
     *
     * 1. �����ж������Ԥ��ֵ�Ƿ�������
     *
     * 2. �����ж������MYSQL��װ·���Ƿ���ȷ�� �жϰ취�ǿ�·����Ӧ��bin��Ŀ¼���Ƿ���mysql.exe�ļ�����
     *
     * 3. ������������ж϶�ͨ���ˣ���ô�͵���ҵ����ConfigService�������ݸ���
     *
     * ConfigService cs= new ConfigService();
     * cs.update(ConfigService.budget,p.tfBudget.getText());
     * cs.update(ConfigService.mysqlPath,mysqlPath);
     *
     *
     *
     * 4. �����ʾ�����޸ĳɹ�
     */

    @Override
    public void actionPerformed(ActionEvent e) {
        ConfigPanel panel = ConfigPanel.instance;//�������ģʽ
        if (!Util.GUIUtil.checkNumber(panel.tfBudget,"����Ԥ��")) return;
        String mysqlPath = panel.tfMysqlPath.getText();
        if (0!=mysqlPath.length()) {
            File commandFile = new File(mysqlPath,"bin/mysql.exe");
            if (!commandFile.exists()) {
                JOptionPane.showMessageDialog(panel,"mysql·����ȷ");
                panel.tfMysqlPath.grabFocus();
                return;
            }
        }

        ConfigService cs = new ConfigService();
        cs.update(ConfigService.budget,panel.tfBudget.getText());
        cs.update(ConfigService.mysqlPath,mysqlPath);

        JOptionPane.showMessageDialog(panel,"�����޸ĳɹ�");
    }


}
