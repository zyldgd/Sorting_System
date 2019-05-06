package test;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by hdwang on 2018/10/11.
 */
public class Main {

    public static void main(String[] args) {
        testComposite();
    }

    /**
     * 合成测试
     */
    public static void testComposite() {
        //创建背景图片（带透明分量的）
        BufferedImage bg = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D bgGraphics = (Graphics2D) bg.getGraphics();
        bgGraphics.setColor(Color.yellow); //设置颜色
        bgGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //边缘抗锯齿
        bgGraphics.fillRect(0, 0, bg.getWidth(), bg.getHeight()); //填充矩形
        bgGraphics.setColor(Color.BLACK);
        bgGraphics.setFont(new Font("楷体", Font.ITALIC, 50));
        bgGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //文本抗锯齿
        bgGraphics.drawString("背景黄色", 50, 150); //画文本
        bgGraphics.dispose();

        //创建第二张图片
        BufferedImage image = new BufferedImage(300, 300, BufferedImage.TYPE_INT_ARGB);
        Graphics2D imageGraphics = (Graphics2D) image.getGraphics();
        imageGraphics.setColor(Color.GREEN);
        imageGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        imageGraphics.fillRoundRect(0, 0, image.getWidth(), image.getHeight(), image.getWidth(), image.getHeight());
        imageGraphics.setColor(Color.BLACK);
        imageGraphics.setFont(new Font("楷体", Font.ITALIC, 50));
        imageGraphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        imageGraphics.drawString("前景绿色", 50, 200);
        imageGraphics.dispose();

        JFrame jf = new JFrame(); //窗体
        JPanel contentPanel = new JPanel(); //内容面板
        contentPanel.setBorder(BorderFactory.createLineBorder(Color.blue)); //设置边框
        //contentPanel.setLayout(new BorderLayout());

        //添加标签
        JLabel label = new JLabel();
        label.setText("组合模式：");
        contentPanel.add(label);

        //添加下拉框
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("默认");
        comboBox.addItem("CLEAR");
        comboBox.addItem("SRC");
        comboBox.addItem("DST");
        comboBox.addItem("SRC_OVER");
        comboBox.addItem("DST_OVER");
        comboBox.addItem("SRC_IN");
        comboBox.addItem("DST_IN");
        comboBox.addItem("SRC_OUT");
        comboBox.addItem("DST_OUT");
        comboBox.addItem("SRC_ATOP");
        comboBox.addItem("DST_ATOP");
        comboBox.addItem("XOR");
        contentPanel.add(comboBox);

        JLabel label2 = new JLabel();
        label2.setText("源图非透明度：");
        contentPanel.add(label2);

        //添加滑动条([0-10]=>[0,0.1,0.2,.... 1.0])
        JSlider jSlider = new JSlider(0,10,10);
        jSlider.setMajorTickSpacing(5); // 设置主刻度间隔
        jSlider.setMinorTickSpacing(1); // 设置次刻度间隔
        jSlider.setPaintTicks(true); // 绘制刻度
        jSlider.setPaintLabels(true);  // 绘制标签
        jSlider.setSnapToTicks(true); // 对其到刻度取值
        /*
         * 给指定的刻度值显示自定义标签
         */
        Hashtable<Integer, JComponent> hashtable = new Hashtable<Integer, JComponent>();
        hashtable.put(0, new JLabel("0"));      //  0  刻度位置，显示 "0"
        hashtable.put(5, new JLabel("0.5"));    //  5 刻度位置，显示 "0.5"
        hashtable.put(10, new JLabel("1"));       //  10 刻度位置，显示 "1"
        jSlider.setLabelTable(hashtable); //将刻度值和自定义标签的对应关系设置到滑块（设置后不再显示默认的刻度值）

        contentPanel.add(jSlider);


        jf.setContentPane(contentPanel); //窗体设置内容面板为jp
        jf.setBounds(200, 200, 500, 500);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true); //窗体可见


        DrawingPanel drawPanel = new DrawingPanel();
        drawPanel.setBounds(0,60,500,415);
        drawPanel.setPreferredSize(new Dimension(500,415));
        drawPanel.setBorder(BorderFactory.createLineBorder(Color.red)); //设置边框
        drawPanel.setBg(bg);
        drawPanel.setImage(image);
        // drawPanel.setAlphaComposite(AlphaComposite.SrcAtop);
        contentPanel.add(drawPanel);

        Map<String,AlphaComposite> compositeMap = new HashMap<>();
        compositeMap.put("CLEAR",AlphaComposite.Clear);
        compositeMap.put("SRC",AlphaComposite.Src);
        compositeMap.put("DST",AlphaComposite.Dst);
        compositeMap.put("SRC_OVER",AlphaComposite.SrcOver);
        compositeMap.put("DST_OVER",AlphaComposite.DstOver);
        compositeMap.put("SRC_IN",AlphaComposite.SrcIn);
        compositeMap.put("DST_IN",AlphaComposite.DstIn);
        compositeMap.put("SRC_OUT",AlphaComposite.SrcOut);
        compositeMap.put("DST_OUT",AlphaComposite.DstOut);
        compositeMap.put("SRC_ATOP",AlphaComposite.SrcAtop);
        compositeMap.put("DST_ATOP",AlphaComposite.DstAtop);
        compositeMap.put("XOR",AlphaComposite.Xor);
        //下拉框选中事件
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    String selected =  e.getItem().toString();
                    System.out.println(selected);
                    drawPanel.setAlphaComposite(compositeMap.get(selected));
                    drawPanel.repaint(); //重画
                }
            }
        });

        //滑动条改变事件
        jSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int val = jSlider.getValue();
                float alpha = (float) (val / 10.0);
                System.out.println("alpha:"+alpha);
                drawPanel.setAlpha(alpha);
                drawPanel.repaint(); //重画
            }
        });

        //窗体改变事件
        jf.addWindowStateListener(new WindowStateListener() {

            @Override
            public void windowStateChanged(WindowEvent e) {
                System.out.println("window state:"+e.paramString());
            }
        });
    }


    static class DrawingPanel extends JPanel{

        BufferedImage bg;
        BufferedImage image;
        AlphaComposite alphaComposite;
        float alpha = 1.0f; //源图非透明度（新加的图）

        public BufferedImage getBg() {
            return bg;
        }

        public void setBg(BufferedImage bg) {
            this.bg = bg;
        }

        public BufferedImage getImage() {
            return image;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }

        public AlphaComposite getAlphaComposite() {
            return alphaComposite;
        }

        public void setAlphaComposite(AlphaComposite alphaComposite) {
            this.alphaComposite = alphaComposite;
        }

        public float getAlpha() {
            return alpha;
        }

        public void setAlpha(float alpha) {
            this.alpha = alpha;
        }

        /**
         * 重写paint方法
         * @param g
         */
        @Override
        public  void paint(Graphics g){
            //调用的super.paint(g),让父类做一些事前的工作，如刷新屏幕
            super.paint(g);

            //在面板上画画
            Graphics2D g2d = (Graphics2D)g;
            g2d.setComposite(AlphaComposite.Src);
            g2d.drawImage(bg,100,100,null); //背景图

            if(alphaComposite!=null) {
                g2d.setComposite(alphaComposite.derive(alpha));
            }else{
                //默认SrcOver
                g2d.setComposite(AlphaComposite.SrcOver.derive(alpha));
            }
            g2d.drawImage(image,100,100,null); //叠加图

            //g2d.setColor(Color.GREEN);
            //g2d.fillRoundRect(100,100,image.getWidth(),image.getHeight(),image.getWidth(),image.getHeight()); //叠加图层
            g2d.dispose();
        }
    }
}