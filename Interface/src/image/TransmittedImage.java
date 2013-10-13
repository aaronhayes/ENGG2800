package image;

import image.processing.CopyBufferedImage;
import image.processing.SIFT;

import java.awt.image.BufferedImage;


/**
 * @author Aaron Hayes
 */
public class TransmittedImage {

    public static final int IMG_WIDTH = 320;
    public static final int IMG_HEIGHT = 240;
    private static final int HIST_ARRAY_SIZE = ((IMG_HEIGHT * IMG_WIDTH) / (Histogram.SIZE * Histogram.SIZE));

    private BufferedImage bufferedImage;
    private BufferedImage edgeImage;
    private double[] magnitude;
    private double[] angle;

    private Point stars;
    private Point earth;

    private int feature1X;
    private int feature1Y;
    private int feature2X;
    private int feature2Y;

    private Histogram[] histograms;

    public TransmittedImage(BufferedImage image) {
        bufferedImage = CopyBufferedImage.copy(image);

        System.out.println(HIST_ARRAY_SIZE);
        histograms = new Histogram[HIST_ARRAY_SIZE];
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

    public double[] getAngleArray() {
        return angle;
    }

    public double[] getMagnitudeArray() {
        return magnitude;
    }

    public void setMagnitude(double[] m) {
        magnitude = m;
    }

    public void createHistogram() {
        SIFT.createHistograms(this);
    }

    public void addHistogram(Histogram histogram1, int x, int y) {
        int xIn = x / Histogram.SIZE;
        int yIn = y / Histogram.SIZE;
        histograms[(yIn * (IMG_WIDTH / Histogram.SIZE)) + xIn] = histogram1;
    }

    public Histogram[] getHistograms() {
        return histograms;
    }

    public void findStars(TransmittedImage star) {
        this.stars = SIFT.compare(this, star);
    }

    public void findEarth(TransmittedImage earth) {
        this.earth = SIFT.compare(this, earth);
    }

    public Point getStarsPoint() {
        return stars;
    }
    public Point getEarthPoint() {
        return earth;
    }
}
