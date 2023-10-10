package Gui.panel;

import javax.swing.*;

import Gui.listener.BackupListener;
import Util.ColorUtil;
import Util.GUIUtil;


public class BackupPanel extends WorkingPanel {
    static{
        GUIUtil.useLNF();
    }
    public static BackupPanel instance = new BackupPanel();

    JButton bBackup = new JButton("±¸·Ý");

    public BackupPanel() {
        GUIUtil.setColor(ColorUtil.blueColor,bBackup);
        this.add(bBackup);
        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(BackupPanel.instance);
    }

    @Override
    public void updateData() {
        //pass
    }

    @Override
    public void addListener() {
        BackupListener listener = new BackupListener();
        bBackup.addActionListener(listener);
    }
}
