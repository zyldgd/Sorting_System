package test;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

public class CanvasPanel extends Canvas {
    final int imageLayersCount = 3;
    Image showLayer;
    ArrayList<Image> imageLayers;

    public CanvasPanel(int width, int height){
        this.setSize(width,height);
        this.setBackground(new Color(225, 225, 225));

        this.imageLayers = new ArrayList<Image>(this.imageLayersCount);
        this.showLayer = this.createImage(this.getWidth(),this.getHeight());
        for (int i = 0; i < this.imageLayersCount; i++) {
            this.imageLayers.add(this.createImage(this.getWidth(),this.getHeight()));
        }

    }


    public void mergeLayer(){
        this.showLayer.getGraphics().clearRect(0,0, this.getWidth(), this.getHeight());
        for (int i = 0; i < this.imageLayersCount; i++) {
            this.showLayer.getGraphics().drawImage(this.imageLayers.get(i), 0,0,this);
        }
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        //g.clearRect(0,0, this.getWidth(), this.getHeight());
/*
        for (int i = 0; i < this.imageLayersCount; i++) {
            g.drawImage(this.imageLayers.get(i), 0,0,this);
        }
*/
        g.drawImage(this.imageLayers.get(0), 0,0,this);

//        try {
//            Thread.sleep(100);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    public void drawBottomLayer()
    {
        Graphics g = this.imageLayers.get(0).getGraphics();
        g.clearRect(0,0, this.getWidth(), this.getHeight());

        g.setColor(new Color(255, 43, 18));
        g.setFont(new Font(null, Font.PLAIN,40));
        g.fillRect(0,0,200,300);
    }

}
