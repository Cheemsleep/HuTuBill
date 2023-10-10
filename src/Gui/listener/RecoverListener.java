package Gui.listener;

import Gui.panel.BackupPanel;
import Gui.panel.ConfigPanel;
import Gui.panel.MainPanel;
import Service.ConfigService;
import Util.MysqlUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLOutput;

/**
 * RecoverListener �ָ�������
 * 1. �����ж�MYSQL��װ·���Ƿ�����
 *
 * 2. ���ļ�ѡ������ָ��Ҫ�򿪵��ļ�
 * �ļ���Ĭ������Ϊhutubill.sql
 * ���ݺ�׺��.sql�����ļ�
 *
 * 3. ����MysqlUtil ���лָ�
 *
 * 4. ��ʾ�ָ��ɹ�
 */

public class RecoverListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel p = BackupPanel.instance;
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        if (mysqlPath.length() == 0) {
            JOptionPane.showMessageDialog(p, "�ָ�ǰ��������mysql·��");

            MainPanel.instance.workingPanel.show(ConfigPanel.instance);
            ConfigPanel.instance.tfMysqlPath.grabFocus();
            return;
        }
        JFileChooser fc = new JFileChooser("D:/Files/mysql_backup");
        fc.setSelectedFile(new File("hutubill.sql"));
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                return f.getName().toLowerCase().endsWith(".sql");
            }

            @Override
            public String getDescription() {
                return ".sql";
            }
        });

        int returnVal = fc.showOpenDialog(p);
        File file = fc.getSelectedFile();
        System.out.println(file);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            try {
                MysqlUtil.recover(mysqlPath, file.getAbsolutePath());
                JOptionPane.showMessageDialog(p, "�ָ��ɹ�");
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(p, "�ָ�ʧ��\r\n ����:\r\n" + e1.getMessage());
            }
        }
    }

}
