package test;


import sortingFactoryRelated.SortingRobot;
import sortingFactoryRelated.SortingZone;

import javax.swing.*;
import java.awt.*;

public class testFactory extends JFrame {

    private final int windowWidth = 1240;
    private final int windowHeight = 870;
    private final int mainCanvasWidth = 1000;
    private final int mainCanvasHeight = 800;

    public testFactory(SortingZone sortingZone) {
        super();
        this.setTitle("canvas");
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocation(450, 100);
        this.setSize(windowWidth, windowHeight);
        this.setPreferredSize(new Dimension(windowWidth, windowHeight));
        this.setMinimumSize(new Dimension(windowWidth, windowHeight));

        JPanel panel_left = new JPanel();
        panel_left.setBackground(new Color(226, 226, 226));
        panel_left.setLayout(null);
        panel_left.setSize(200, windowHeight);
        panel_left.setLocation(0, 0);

        JPanel panel_right = new JPanel();
        panel_right.setBackground(new Color(169, 169, 169));
        panel_right.setLayout(null);
        panel_right.setSize(1020, windowHeight);
        panel_right.setLocation(200, 0);

        JButton btn_start = new JButton("START");
        btn_start.setSize(100, 40);
        btn_start.setBackground(new Color(225, 225, 225));
        btn_start.setLocation(10, 30);

        JTextField text_robotNum = new JTextField("123");
        text_robotNum.setText("1");
        text_robotNum.setSize(100, 30);
        text_robotNum.setLocation(10, 100);

        CanvasPanel canvas_main = new CanvasPanel(mainCanvasWidth, mainCanvasHeight, sortingZone);
        canvas_main.setLocation(10, 10);


        panel_left.add(btn_start);
        panel_left.add(text_robotNum);
        panel_right.add(canvas_main);

        this.add(panel_left);
        this.add(panel_right);

        this.setVisible(true);

        for (SortingRobot robot:sortingZone.getSortingRobots()){
            new Thread(robot).start();
        }


    }

    public static void main(String[] args) {
        SortingZone zone = new SortingZone(26, 26);
        testFactory testFactory0 =  new testFactory(zone);
    }
}
