package Gui.panel;

import javax.swing.*;

import Gui.listener.RecoverListener;
import Util.ColorUtil;
import Util.GUIUtil;

public class RecoverPanel extends WorkingPanel {
    static{
        GUIUtil.useLNF();
    }
    public static RecoverPanel instance = new RecoverPanel();

    JButton bRecover = new JButton("»Ö¸´");

    public RecoverPanel() {
        GUIUtil.setColor(ColorUtil.blueColor,bRecover);
        this.add(bRecover);
        addListener();
    }

    public static void main(String[] args) {
        GUIUtil.showPanel(RecoverPanel.instance);
    }

    @Override
    public void updateData() {
        //pass
    }

    @Override
    public void addListener() {
        RecoverListener listener = new RecoverListener();
        bRecover.addActionListener(listener);
    }
}
