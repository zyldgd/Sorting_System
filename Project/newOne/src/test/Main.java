package test;

import java.awt.*;
import javax.swing.*;
import java.util.*;



public class Main {

    public void init(){
        JFrame frame = new JFrame();
        frame.setTitle("canvas");
        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(450, 150);
        frame.setSize(1000, 800);
        CanvasPanel mainCanvas = new CanvasPanel(600,600);

        frame.add(mainCanvas);

        frame.setVisible(true);


    }

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Main zMain = new Main();
        zMain.init();


        /*
        JFrame f=new JFrame();
        f.setSize(1400,900);
        Mypanl p=new Mypanl();
        p.setBackground(Color.BLACK);
        f.add(p);
        f.setVisible(true);
        */
    }
    /*
    static class Mypanl extends JPanel{
        @Override
        public void paint(Graphics g) {
            super.paint(g);
            //设置画笔颜色
            g.setColor(Color.WHITE);
            //设置画笔大小
            g.setFont(new Font(null, 0,40));
            //循环花100个星星
            for (int i = 0; i <100; i++) {
                g.drawString("LLZ",(int)(Math.random()*1400), (int)(Math.random()*900));
            }
            //线程等待100毫秒后重画
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            repaint();
        }
    }
    */
}
