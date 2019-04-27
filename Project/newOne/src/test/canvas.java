package test;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.*;
import java.awt.Canvas;
import java.io.Closeable;

public class canvas {
    Frame f1=new Frame("简单绘图");
    Panel p1=new Panel();
    Button bt1=new Button("绘制矩形");
    Button bt2=new Button("绘制圆形");
    Button bt3=new Button("clear");
    Mycanvas drawArea1=new Mycanvas();
    Mycanvas drawArea2=new Mycanvas();
    public void set()
    {
        p1.add(bt1);
        p1.add(bt2);
        p1.add(bt3);
        bt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                drawArea1.repaint();
                System.out.println("我靠！");
                System.exit(0);
                //    drawArea1.update(g);
            }
        });

        bt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                drawArea1.getGraphics().drawRect(80,90,150,10);
                drawArea1.repaint();
                System.out.println("我靠！");
                //    drawArea1.update(g);
            }
        });


        f1.add(p1,BorderLayout.SOUTH);
        drawArea1.setPreferredSize(new Dimension(250,180));
        drawArea2.setPreferredSize(new Dimension(300,180));
        f1.add(drawArea1,BorderLayout.EAST);
        f1.add(drawArea2,BorderLayout.WEST);
        f1.pack();
        f1.setVisible(true);
    }

    public static void main(String[] args){
        new canvas().set();
    }


    class Mycanvas extends Canvas
    {
        public void paint(Graphics g)
        {
            g.drawRect(80,60,150,10);
            g.drawOval(100,70,100,100);
        }
        public void update(Graphics g)
        {

        }
    }
}