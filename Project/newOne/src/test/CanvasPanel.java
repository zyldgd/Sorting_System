package test;

import sortingFactoryRelated.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CanvasPanel extends JPanel {
    private Timer timer;
    private Dimension blockDimension;
    private Dimension mapDimension;
    private boolean over = false;

    SortingView robotView;
    SortingView blockView;

    private Image showLayer;
    private Image bottomLayer;
    private Image middleLayer;
    private Image topLayer;
    private SortingZone sortingZone;

    private Graphics2D GS;
    private Graphics2D GB;
    private Graphics2D GM;
    private Graphics2D GT;

    public CanvasPanel(int width, int height, SortingZone sortingZone) throws IOException {

        this.setSize(width, height);
        this.setBackground(new Color(255, 255, 255));
        this.init();
        this.blockDimension = new Dimension(30, 30);
        this.sortingZone = sortingZone;
        this.mapDimension = new Dimension(30 * this.sortingZone.getMap().getHorizontalSCale(), 30 * this.sortingZone.getMap().getVerticalSCale());

        this.robotView = new SortingView(this.blockDimension);
        this.robotView.setView("src/img/robot.png");

        this.blockView = new SortingView(this.blockDimension);
        this.blockView.setView("src/img/block2.png");
    }

    public void init() {
        //this.createImage(this.getWidth(), this.getHeight());
        this.showLayer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.bottomLayer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.middleLayer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.topLayer = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);

         /*
          GS.setComposite(AlphaComposite.SrcOver.derive(1f));
         */

        this.GS = (Graphics2D) this.showLayer.getGraphics();
        this.GB = (Graphics2D) this.bottomLayer.getGraphics();
        this.GM = (Graphics2D) this.middleLayer.getGraphics();
        this.GT = (Graphics2D) this.topLayer.getGraphics();

        this.GS.setBackground(new Color(0, 0, 0, 0));
        this.GB.setBackground(new Color(0, 0, 0, 0));
        this.GM.setBackground(new Color(0, 0, 0, 0));
        this.GT.setBackground(new Color(0, 0, 0, 0));

        this.GS.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //边缘抗锯齿
        this.GS.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //文本抗锯齿


        this.timer = new Timer(15, e -> {
            this.repaint();
        });

        this.timer.start();

    }

    public void mergeLayers() {
        this.GS.clearRect(0, 0, this.getWidth(), this.getHeight());
        this.GS.drawImage(this.bottomLayer, 0, 0, this);
        this.GS.drawImage(this.middleLayer, 0, 0, this);
        //this.GS.drawImage(this.topLayer, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        this.drawBottomLayer();
        this.drawMiddleLayer();
        this.mergeLayers();
        g.drawImage(this.showLayer, 0, 0, this);
    }

    public void drawBottomLayer() {
        if (over) {
            return;
        }
        int H = this.sortingZone.getMap().getVerticalSCale();
        int W = this.sortingZone.getMap().getHorizontalSCale();

        for (int h = 0; h < H; h++) {
            for (int w = 0; w < W; w++) {
                Route r = this.sortingZone.getMap().getRoute(w, h);
                int x = this.blockDimension.width * w;
                int y = this.mapDimension.height - this.blockDimension.height - this.blockDimension.height * h;
                GB.setColor(new Color(212, 212, 212));
                GB.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                GB.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_ROUND, 0f, new float[]{2}, 0f));

                GB.drawImage(this.blockView.view, x, y, null);
                //GB.drawRect(x, y, this.blockDimension.width, this.blockDimension.height);
            }
        }

        for (SortingComponent sc : this.sortingZone.getMap().getComponents("putDownStation").values()) {
            int x = this.blockDimension.width * sc.getLocation().x;
            int y = this.mapDimension.height - this.blockDimension.height - this.blockDimension.height * sc.getLocation().y;
            GB.setColor(new Color(255, 30, 175));
            GB.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_ROUND, 0f, new float[]{2}, 0f));
            GB.drawRect(x, y, this.blockDimension.width, this.blockDimension.height);
        }

        for (SortingComponent sc : this.sortingZone.getMap().getComponents("pickUpStation").values()) {
            int x = this.blockDimension.width * sc.getLocation().x;
            int y = this.mapDimension.height - this.blockDimension.height - this.blockDimension.height * sc.getLocation().y;
            GB.setColor(new Color(14, 255, 7));
            GB.setStroke(new BasicStroke(2f, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_ROUND, 0f, new float[]{2}, 0f));
            GB.drawRect(x, y, this.blockDimension.width, this.blockDimension.height);
        }


        GB.dispose();
        this.over = true;

    }

    public void drawMiddleLayer() {
        Graphics2D G = (Graphics2D) this.middleLayer.getGraphics();
        G.setBackground(new Color(0, 0, 0, 0));
        G.clearRect(0, 0, this.getWidth(), this.getHeight());
        G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //边缘抗锯齿

        //G.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_DEFAULT);

        for (SortingRobot robot : this.sortingZone.getSortingRobots()) {
            int x = this.mapDimension.width * robot.getPosition().x / this.sortingZone.getWidth();
            int y = this.mapDimension.height - this.blockDimension.height - this.mapDimension.height * robot.getPosition().y / this.sortingZone.getHeight();
            double degree =  Math.toRadians(robot.getDegree());
            //G.setColor(new Color(39, 75, 255));
            G.rotate(degree, x + this.blockDimension.width / 2, y + this.blockDimension.height / 2);
            G.drawImage(this.robotView.view, x, y, null);
            //G.fillRoundRect(x, y, this.blockDimension.width, this.blockDimension.height, this.blockDimension.width / 2, this.blockDimension.height / 2);
            //System.out.println(String.format("[%d, %d]", x, y));
            G.rotate(-degree, x + this.blockDimension.width / 2, y + this.blockDimension.height / 2);
        }

//        GM.clearRect(0, 0, this.getWidth(), this.getHeight());
//        GM.setColor(Color.GREEN);
//        GM.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), this.getWidth(), this.getHeight());
//        GM.setColor(Color.BLACK);
//        GM.setFont(new Font("楷体", Font.ITALIC, 50));
//        GM.drawString("前景绿色", 50, 200);
//        GM.dispose();
//        GB.clearRect(0, 0, this.getWidth(), this.getHeight());
//        GB.setColor(Color.black); //设置颜色
//        GB.rotate(355 * Math.PI / 180, 15, 15);

//        GB.setColor(Color.BLACK);
//        GB.setFont(new Font("楷体", Font.ITALIC, 50));
//        GB.drawString("背景黄色", 50, 150); //画文本
//        GB.dispose();
    }

}
