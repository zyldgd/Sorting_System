package sortingFactoryRelated;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class SortingView {
    public Image view;
    public Dimension dimension;

    public SortingView(Dimension dimension, String path) throws IOException {
        this(dimension.width, dimension.height);
        this.setView(path);
    }

    public SortingView(Dimension dimension) {
        this(dimension.width, dimension.height);
    }

    public SortingView(int width, int height) {
        this.view = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        this.dimension = new Dimension(width, height);
    }

    public Image getView() {
        return this.view.getScaledInstance(this.dimension.width, this.dimension.height, Image.SCALE_SMOOTH);
    }

    public void setView(String path) throws IOException {
        Graphics2D G = ((BufferedImage)this.view).createGraphics();
        BufferedImage image = ImageIO.read(new File(path)); // "src/img/robot.png"
        G.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //边缘抗锯齿
        G.drawImage(image.getScaledInstance(this.dimension.width, this.dimension.height, Image.SCALE_SMOOTH), 0, 0, this.dimension.width, this.dimension.height, null);
        G.dispose();
    }
}
