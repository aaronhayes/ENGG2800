package image;

import java.awt.image.BufferedImage;

/**
 * @author Aaron Hayes
 */
public class TransmittedImage {

    public static final int IMG_WIDTH = 320;
    public static final int IMG_HEIGHT = 240;

    private BufferedImage bufferedImage;
    private BufferedImage edgeImage;
    private double[] magnitude;
    private double[] angle;
    private int feature1X;
    private int feature1Y;
    private int feature2X;
    private int feature2Y;

    public TransmittedImage(BufferedImage image) {
        bufferedImage = image;
    }

    public void setEdgeImage(BufferedImage edge) {
        edgeImage = edge;
    }

    public BufferedImage getEdgeImage() {
        return edgeImage;
    }

    public void setBufferedImage(BufferedImage image) {
        bufferedImage = image;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public int getFeature1X() {
        return 0;
    }

    public void setFeature1X(int x) {
        feature1X = x;
    }

    public int getFeature2X() {
        return 0;
    }

    public void setFeature2X(int x) {
        feature2X = x;
    }

    public int getXOffset(){
        return ((getFeature1X() + getFeature2X()) / 2);
    }

    public int getWidth() {
        return bufferedImage.getWidth();
    }

    public int getHeight() {
        return bufferedImage.getHeight();
    }

    public void setAngle(double[] a) {
        angle = a;
    }

    public void setMagnitude(double[] m) {
        magnitude = m;
    }
}
