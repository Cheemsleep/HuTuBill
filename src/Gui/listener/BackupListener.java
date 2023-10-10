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

/**
 * BackupListener ���ݼ�����
 * 1. �����ж�MYSQL��װ·���Ƿ�����
 *
 * 2. ���ļ�ѡ������ָ��Ҫ������ļ�
 * �ļ���Ĭ������Ϊhutubill.sql
 * �Ժ�׺��.sql�����ļ�
 *
 * 3. ����MysqlUtil ���б���
 *
 * 4. ��ʾ���ݳɹ�
 */

public class BackupListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel p = BackupPanel.instance;
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        if (mysqlPath.length() == 0) {
            JOptionPane.showMessageDialog(p, "����ǰ������mysql��װ·��");
            MainPanel.instance.workingPanel.show(ConfigPanel.instance);
            ConfigPanel.instance.tfMysqlPath.grabFocus();
            return;
        }
        JFileChooser fc = new JFileChooser("D:/Files/mysql_backup");
        fc.setSelectedFile(new File("hutubill.sql"));
        fc.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return true;
                }
                return f.getName().toLowerCase().endsWith(".sql");
            }

            @Override
            public String getDescription() {
                return ".sql";
            }
        });
        int returnVal = fc.showSaveDialog(p);
        File file = fc.getSelectedFile();
        System.out.println(file);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            //���������ļ���û����.sql��β���Զ�����.sql
            System.out.println(file);
            if (!file.getName().toLowerCase().endsWith(".sql"))
                file = new File(file.getParent(),file.getName()+".sql");
            System.out.println(file);
        }
        try {
            MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
            JOptionPane.showMessageDialog(p, "���ݳɹ�,�����ļ�λ�ã�\r\n" + file.getAbsolutePath());
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(p, "����ʧ��\r\n, ����\r\n" + e1.getMessage());
        }
    }
}
