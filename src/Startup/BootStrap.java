package Startup;

import javax.swing.SwingUtilities;

import Gui.frame.MainFrame;
import Gui.panel.MainPanel;
import Gui.panel.SpendPanel;
import Util.GUIUtil;

public class BootStrap {
    public static void main(String[] args) throws Exception {
        GUIUtil.useLNF();

        SwingUtilities.invokeAndWait(new Runnable() {
            @Override
            public void run() {
                MainFrame.instance.setVisible(true);
                MainPanel.instance.workingPanel.show(SpendPanel.instance);
            }
        });
    }
}
