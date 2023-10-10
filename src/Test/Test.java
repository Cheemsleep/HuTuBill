package Test;

import Util.CircleProgressBar;
import Util.ColorUtil;
import Util.GUIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Test {
    public static void main(String[] args) {


        GUIUtil.useLNF();//水晶皮肤
        JPanel jp = new JPanel();//设置面板
        CircleProgressBar cpb = new CircleProgressBar();//组件
        cpb.setBackgroundColor(ColorUtil.blueColor);
        cpb.setProgress(0);
        //按钮
        JButton button = new JButton("点击");
        jp.setLayout(new BorderLayout());//添加组件
        jp.add(cpb, BorderLayout.CENTER);
        jp.add(button, BorderLayout.SOUTH);

        GUIUtil.showPanel(jp);//显示面板

        //添加监听事件
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SwingWorker() {
                    @Override
                    protected Object doInBackground() throws Exception {
                        for (int i=0;i<100;i++) {
                            cpb.setProgress(i+1);
                            cpb.setForegroundColor(ColorUtil.getByPercentage(i+1));
                            try {
                                Thread.sleep(50);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        return null;
                    }
                }.execute();
            }
        });
    }
}
