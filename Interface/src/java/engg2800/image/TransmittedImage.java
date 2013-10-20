package engg2800.image;

import engg2800.image.processing.CopyBufferedImage;
import engg2800.image.processing.SIFT;

import java.awt.image.BufferedImage;


/**
 * Class to hold information about Images that have been Transmitted and loaded from file
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

    private int width;
    private int height;

    private int feature1X;
    private int feature1Y;
    private int feature2X;
    private int feature2Y;

    private Histogram[] histograms;

    /**
     * Basic Constructor
     * @param image BufferedImage - the engg2800.image
     */
    public TransmittedImage(BufferedImage image) {
        bufferedImage = CopyBufferedImage.copy(image);
        //bufferedImage = NoiseRemoval.cleanup(bufferedImage);

        System.out.println(HIST_ARRAY_SIZE);
        histograms = new Histogram[HIST_ARRAY_SIZE];
        this.width = TransmittedImage.IMG_WIDTH;
        this.height = TransmittedImage.IMG_HEIGHT;
    }

    /**
     * Constructor
     * @param image BufferedImage - the engg2800.image
     * @param width the width of the engg2800.image
     * @param height the height of the engg2800.image
     */
    public TransmittedImage(BufferedImage image, int width, int height) {
        this.width = width;
        this.height = height;
        bufferedImage = CopyBufferedImage.copy(image);
        //bufferedImage = NoiseRemoval.cleanup(bufferedImage);

        System.out.println(HIST_ARRAY_SIZE);
        histograms = new Histogram[HIST_ARRAY_SIZE];
    }

    /**
     * Set the edge detected engg2800.image
     * @param edge Buffered Image
     */
    public void setEdgeImage(BufferedImage edge) {
        edgeImage = edge;
    }

    /**
     * Get the edge detected engg2800.image
     * @return edgeImage
     */
    public BufferedImage getEdgeImage() {
        return edgeImage;
    }

    /**
     * Set the Buffered Image of the
     * @param image BufferedImage
     */
    public void setBufferedImage(BufferedImage image) {
        bufferedImage = image;
    }

    /**
     * Get Buffed Image of Transmitted Image
     * @return bufferedImage
     */
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

    /**
     * Get Width of Image
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get Height of Image
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set Angle Array
     * @param a double[] angle
     */
    public void setAngle(double[] a) {
        angle = a;
    }

    /**
     * Get Angle Array
     * @return angle
     */
    public double[] getAngleArray() {
        return angle;
    }

    /**
     * Get Magnitude Array
     * @return magnitude
     */
    public double[] getMagnitudeArray() {
        return magnitude;
    }

    /**
     * Set Magnitude Array
     * @param m double[] magnitude
     */
    public void setMagnitude(double[] m) {
        magnitude = m;
    }

    /**
     * Start creation of histograms
     */
    public void createHistogram() {
        SIFT.createHistograms(this);
    }

    /**
     * Add a histogram to the engg2800.image
     * @param histogram1 Histogram
     * @param x X location
     * @param y Y location
     */
    public void addHistogram(Histogram histogram1, int x, int y) {
        int xIn = x / Histogram.SIZE;
        int yIn = y / Histogram.SIZE;
        histograms[(yIn * (width / Histogram.SIZE)) + xIn] = histogram1;
    }

    /**
     * Get Array of Histograms
     * @return histograms[]
     */
    public Histogram[] getHistograms() {
        return histograms;
    }

    /**
     * Preform SIFT comparison to find the southern cross
     * @param star Image of the southern cross
     */
    public void findStars(TransmittedImage star) {
        this.stars = SIFT.compare(this, star);
    }

    /**
     * Preform SIFT comparison to find the earth
     * @param earth Image of the Earth
     */
    public void findEarth(TransmittedImage earth) {
        this.earth = SIFT.compare(this, earth);
    }

    /**
     * Get Points of the Southern cross
     * @return Point stars
     */
    public Point getStarsPoint() {
        return stars;
    }

    /**
     * Get Points of the Earth
     * @return Point earth
     */
    public Point getEarthPoint() {
        return earth;
    }
}
