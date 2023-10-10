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
 * RecoverListener 恢复监听器
 * 1. 首先判断MYSQL安装路径是否配置
 *
 * 2. 打开文件选择器，指定要打开的文件
 * 文件名默认设置为hutubill.sql
 * 根据后缀名.sql过滤文件
 *
 * 3. 调用MysqlUtil 进行恢复
 *
 * 4. 提示恢复成功
 */

public class RecoverListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        BackupPanel p = BackupPanel.instance;
        String mysqlPath = new ConfigService().get(ConfigService.mysqlPath);
        if (mysqlPath.length() == 0) {
            JOptionPane.showMessageDialog(p, "恢复前请先配置mysql路径");

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
                JOptionPane.showMessageDialog(p, "恢复成功");
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(p, "恢复失败\r\n 错误:\r\n" + e1.getMessage());
            }
        }
    }

}
