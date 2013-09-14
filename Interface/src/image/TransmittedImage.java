package image;

import java.awt.image.BufferedImage;

/**
 * @author Aaron Hayes
 */
public class TransmittedImage {

    private BufferedImage bufferedImage;
    private int feature1X;
    private int feature1Y;
    private int feature2X;
    private int feature2Y;

    public TransmittedImage(BufferedImage image) {
        bufferedImage = image;
    }

    public void setBufferedImage(BufferedImage image) {
        bufferedImage = image;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public int getFeature1X() {
        return feature1X;
    }

    public void setFeature1X(int x) {
        feature1X = x;
    }

    public int getFeature2X() {
        return feature2X;
    }

    public void setFeature2X(int x) {
        feature1X = x;
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
}
