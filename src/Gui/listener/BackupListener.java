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
 * BackupListener 备份监听器
 * 1. 首先判断MYSQL安装路径是否配置
 *
 * 2. 打开文件选择器，指定要保存的文件
 * 文件名默认设置为hutubill.sql
 * 以后缀名.sql过滤文件
 *
 * 3. 调用MysqlUtil 进行备份
 *
 * 4. 提示备份成功
 */

public class BackupListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel p = BackupPanel.instance;
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        if (mysqlPath.length() == 0) {
            JOptionPane.showMessageDialog(p, "备份前请配置mysql安装路径");
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
            //如果保存的文件名没有以.sql结尾，自动加上.sql
            System.out.println(file);
            if (!file.getName().toLowerCase().endsWith(".sql"))
                file = new File(file.getParent(),file.getName()+".sql");
            System.out.println(file);
        }
        try {
            MysqlUtil.backup(mysqlPath, file.getAbsolutePath());
            JOptionPane.showMessageDialog(p, "备份成功,备份文件位置：\r\n" + file.getAbsolutePath());
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(p, "备份失败\r\n, 错误：\r\n" + e1.getMessage());
        }
    }
}
