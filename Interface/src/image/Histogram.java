package image;

/**
 * Abstraction for image histograms
 * @author Aaron Hayes
 */
public class Histogram {
    public static final int SIZE = 16;
    public static final int ANGLE_GROUPS = 8;
    private double[] values;
    private double[] keyValues;
    private double maxValue;

    private int x;
    private int y;
    private double totalMagnitude;

    private boolean keypoint = false;

    /**
     * Basic constructor
     * @param x x location
     * @param y y location
     */
    public Histogram(int x, int y) {
        this.x = x;
        this.y = y;
        totalMagnitude = 0;
        maxValue = 0;
        keyValues = new double[ANGLE_GROUPS];
    }

    public void setValues(double[] values) {

        //System.out.println("NEW HISTOGRAM");
        for (int x = 0; x < values.length; x++) {
            values[x] /= (SIZE ^ 2);
            //values[x] /= 32;
            totalMagnitude += values[x];

            if (values[x] > maxValue) maxValue = values[x];
            //System.out.println(values[x]);
        }
        //System.out.println("{" + x + "," + y + ") TOTAL MAG = " + totalMagnitude);
        if (totalMagnitude > 10.0) keypoint = true;
        this.values = values;

        for (int i = 0; i < values.length; i++) {
            if (values[i] >  maxValue * 0.8) {
                keyValues[i] = values[i];
            } else {
                keyValues[i] = 0;
            }
        }
    }

    public double[] getValues() {
        return values;
    }

    public double getTotalMagnitude() {
        return totalMagnitude;
    }

    public boolean isKeypoint() {
        return keypoint;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public double[] getKeyValues() {
        return keyValues;
    }
}
