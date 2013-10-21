package engg2800.image.processing;

import engg2800.image.Histogram;
import engg2800.image.Point;
import engg2800.image.TransmittedImage;

/**
 * SIFT engg2800.image processing
 * @author Aaron Hayes
 */
public class SIFT {


    /**
     * Create Histograms for a transmitted image
     * @param image Transmitted Image
     */
    public static void createHistograms(TransmittedImage image) {
        double[] angle = image.getAngleArray();
        double[] magnitude = image.getMagnitudeArray();


        // Split Image into 16x16 region
        //
        for (int y = 0; y <= image.getHeight() - Histogram.SIZE; y += Histogram.SIZE) {
            for (int x = 0; x <= image.getWidth() - Histogram.SIZE; x += Histogram.SIZE) {

                Histogram histogram = new Histogram(x, y);
                double[] values = new double[Histogram.ANGLE_GROUPS];

                for (int yoff = y; yoff <  (y + Histogram.SIZE); yoff++) {
                    for (int xoff = x; xoff < (x + Histogram.SIZE); xoff++) {
                        int group = (int) (angle[(yoff * image.getWidth()) + xoff] / (360 / Histogram.ANGLE_GROUPS));
                        //values[group]++;
                        values[group] += magnitude[(yoff * image.getWidth()) + xoff];
                    }
                }

                histogram.setValues(values);
                image.addHistogram(histogram, x, y);
            }
        }


    }

    /**
     * Compare two image histograms
     * @param histogram1 Histogram Image1
     * @param histogram2 Histogram Image2
     * @return The calculated match
     */
    private static double compareHistogram(Histogram histogram1, Histogram histogram2) {
        double match = 100;
        double[] values1 = histogram1.getValues();
        double[] values2 = histogram2.getValues();
        double[] diff = new double[Histogram.ANGLE_GROUPS];
        for (int i = 0; i < values1.length; i++) {
            diff[i] = Math.abs(values1[i] - values2[i]);
            match -= (diff[i] * diff[i] * diff[i]);
            //System.out.println("ANGLE GROUP " + i + ":" + diff[i]);
        }
        return match;
    }


    /**
     * Compare two Transmitted Images
     * @param image1 TransmittedImage 1
     * @param image2 TransmittedImage 2
     * @return Point of closets match
     */
    public static Point compare(TransmittedImage image1, TransmittedImage image2) {

        double best = 0;
        double second = 0;
        Histogram secondFit = null;
        Histogram bestFit = null;
        Histogram[] histogramsMainImage = image1.getHistograms();
        Histogram[] histogramsSecondImage = image2.getHistograms();

        for (int y = 0; y < (image1.getHeight()/ Histogram.SIZE); y++) {
            for (int x = 0; x < (image1.getWidth() / Histogram.SIZE); x++) {
                Histogram histogram1 = histogramsMainImage[(y * (image1.getWidth() / Histogram.SIZE)) + x];

                if (histogram1 != null && histogram1.isKeypoint()) {
                      for (int y2 = 0; y2 < (image2.getHeight()/ Histogram.SIZE); y2++) {
                             for (int x2 = 0; x2 < (image2.getWidth() / Histogram.SIZE); x2++) {
                            Histogram histogram2 = histogramsSecondImage[(y2 * (image2.getWidth() / Histogram.SIZE)) + x2];

                            if (histogram2.isKeypoint()) {

                                double match;
                                match = compareHistogram(histogram1, histogram2);


                                if (match > best) {
                                    second = best;
                                    best = match;
                                    secondFit = bestFit;
                                    bestFit = histogram1;
                                } else if (match > second) {
                                    second = match;
                                    secondFit = histogram1;
                                }

                            }
                        }
                    }
                }


            }
        }

        if (bestFit != null)
        System.out.println("BEST FIT == " + best + " AT (" + bestFit.getX() + ", " + bestFit.getY() + ")");
        if (secondFit != null)
        System.out.println("Second FIT == " + second + " AT (" + secondFit.getX() + ", " + secondFit.getY() + ")");

        Point result;
        if (best > 80.00 && second > 80.00) {
            int newX = (bestFit.getX() + secondFit.getX()) / 2;
            int newY = (bestFit.getY() + secondFit.getY()) / 2;
            System.out.println("AVERAGE FIT == " + newX + ", " + newY);
            result = new Point(newX, newY);
        } else if (best > 80.00) {
            result = new Point(bestFit.getX(), bestFit.getY());
        } else {
            result = null;
        }

        //System.out.println("SECOND FIT AT (" + secondFit.getX() + ", " + secondFit.getY() + ")");
        //System.out.println("THIRD FIT AT (" + thirdFit.getX() + ", " + thirdFit.getY() + ")");


        return result;
    }


    /**
     * Compare images for panorama mode
     * @param image1 TransmittedImage 1
     * @param image2 TransmittedImage 2
     * @return Point of closets match
     */
    public static Point[] compareMerge(TransmittedImage image1, TransmittedImage image2) {

        double best = 0;
        Histogram secondFit = null;
        Histogram bestFit = null;
        Histogram[] histogramsMainImage = image1.getHistograms();
        Histogram[] histogramsSecondImage = image2.getHistograms();

        Point[] result = new Point[2];

        for (int y = 0; y < (TransmittedImage.IMG_HEIGHT/ Histogram.SIZE); y++) {
            for (int x = 0; x < (TransmittedImage.IMG_WIDTH / Histogram.SIZE); x++) {
                Histogram histogram1 = histogramsMainImage[(y * (TransmittedImage.IMG_WIDTH / Histogram.SIZE)) + x];

                if (histogram1.isKeypoint()) {
                    for (int y2 = 0; y2 < (TransmittedImage.IMG_HEIGHT/ Histogram.SIZE); y2++) {
                        for (int x2 = 0; x2 < (TransmittedImage.IMG_WIDTH / Histogram.SIZE); x2++) {
                            Histogram histogram2 = histogramsSecondImage[(y2 * (TransmittedImage.IMG_WIDTH / Histogram.SIZE)) + x2];

                            if (histogram2.isKeypoint()) {

                                double match = compareHistogram(histogram1, histogram2);


                                if (match > best) {
                                    best = match;
                                    secondFit = histogram2;
                                    bestFit = histogram1;
                                }



                            }
                        }
                    }
                }


            }
        }

        if (best > 65.00) {
            result[0] = new Point(bestFit.getX(), bestFit.getY());
            result[1] = new Point(secondFit.getX(), secondFit.getY());
        }

        return result;
    }
}
