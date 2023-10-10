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


        GUIUtil.useLNF();//ˮ��Ƥ��
        JPanel jp = new JPanel();//�������
        CircleProgressBar cpb = new CircleProgressBar();//���
        cpb.setBackgroundColor(ColorUtil.blueColor);
        cpb.setProgress(0);
        //��ť
        JButton button = new JButton("���");
        jp.setLayout(new BorderLayout());//������
        jp.add(cpb, BorderLayout.CENTER);
        jp.add(button, BorderLayout.SOUTH);

        GUIUtil.showPanel(jp);//��ʾ���

        //��Ӽ����¼�
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
