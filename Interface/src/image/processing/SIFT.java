package image.processing;

import image.Histogram;
import image.Point;
import image.TransmittedImage;

/**
 * SIFT image processing
 * @author Aaron Hayes
 */
public class SIFT {


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


    public static Point compare(TransmittedImage image1, TransmittedImage image2) {

        double best = 0;
        double second = 0;
        Histogram secondFit = null;
        Histogram bestFit = null;
        Histogram[] histogramsMainImage = image1.getHistograms();
        Histogram[] histogramsSecondImage = image2.getHistograms();

        for (int y = 0; y < (image1.IMG_HEIGHT/ Histogram.SIZE); y++) {
            for (int x = 0; x < (image1.IMG_WIDTH / Histogram.SIZE); x++) {
                Histogram histogram1 = histogramsMainImage[(y * (image1.IMG_WIDTH / Histogram.SIZE)) + x];

                if (histogram1 != null && histogram1.isKeypoint()) {
                      for (int y2 = 0; y2 < (image2.IMG_HEIGHT/ Histogram.SIZE); y2++) {
                             for (int x2 = 0; x2 < (image2.IMG_WIDTH / Histogram.SIZE); x2++) {
                            Histogram histogram2 = histogramsSecondImage[(y2 * (image2.IMG_WIDTH / Histogram.SIZE)) + x2];

                            if (histogram2.isKeypoint()) {

                                double match;

                                /*if ((x + 1) < (TransmittedImage.IMG_WIDTH / Histogram.SIZE)
                                        && (y + 1) < (TransmittedImage.IMG_HEIGHT / Histogram.SIZE)
                                        && (y - 1) >= 0
                                        && (x - 1) >= 0) {


                                    Histogram left  = histogramsMainImage[(y * (TransmittedImage.IMG_WIDTH / Histogram.SIZE)) + (x - 1)];
                                    Histogram left2  = histogramsSecondImage[(y * (TransmittedImage.IMG_WIDTH / Histogram.SIZE)) + (x - 1)];

                                    double matchLeft = compareHistogram(left, left2);


                                    Histogram right  = histogramsMainImage[(y * (TransmittedImage.IMG_WIDTH / Histogram.SIZE)) + (x + 1)];
                                    Histogram right2  = histogramsSecondImage[(y * (TransmittedImage.IMG_WIDTH / Histogram.SIZE)) + (x + 1)];

                                    double matchRight = compareHistogram(right, right2);

                                    Histogram up  = histogramsMainImage[((y - 1) * (TransmittedImage.IMG_WIDTH / Histogram.SIZE)) + (x)];
                                    Histogram up2  = histogramsSecondImage[((y - 1) * (TransmittedImage.IMG_WIDTH / Histogram.SIZE)) + (x)];

                                    double matchUp = compareHistogram(up, up2);

                                    Histogram down  = histogramsMainImage[((y + 1) * (TransmittedImage.IMG_WIDTH / Histogram.SIZE)) + (x)];
                                    Histogram down2  = histogramsSecondImage[((y + 1) * (TransmittedImage.IMG_WIDTH / Histogram.SIZE)) + (x)];

                                    double matchDown = compareHistogram(down, down2);

                                    Histogram diB  = histogramsMainImage[((y - 1) * (TransmittedImage.IMG_WIDTH / Histogram.SIZE)) + (x - 1)];
                                    Histogram diB2  = histogramsSecondImage[((y - 1) * (TransmittedImage.IMG_WIDTH / Histogram.SIZE)) + (x - 1)];

                                    double matchDiB = compareHistogram(diB, diB2);

                                    Histogram di  = histogramsMainImage[((y + 1) * (TransmittedImage.IMG_WIDTH / Histogram.SIZE)) + (x + 1)];
                                    Histogram di2  = histogramsSecondImage[((y + 1) * (TransmittedImage.IMG_WIDTH / Histogram.SIZE)) + (x + 1)];

                                    double matchDi = compareHistogram(di, di2);

                                    double norm = compareHistogram(histogram1, histogram2);

                                    match = ((matchRight + matchDown + matchDi + matchDiB + matchUp + matchLeft + norm) / 7);

                                } else { */
                                    match = compareHistogram(histogram1, histogram2);
                                //}


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

        /*for (Histogram histogram : image1.getHistograms()) {
            if (histogram.isKeypoint()) {
                for (Histogram histogram1 : image2.getHistograms()) {
                    if (histogram1.isKeypoint()) {
                        double match = 100;
                        double[] values1 = histogram.getValues();
                        double[] values2 = histogram1.getValues();
                        double[] diff = new double[Histogram.ANGLE_GROUPS];
                        for (int i = 0; i < values1.length; i++) {
                            diff[i] = Math.abs(values1[i] - values2[i]);
                            match -= (diff[i] * diff[i]);
                            //System.out.println("ANGLE GROUP " + i + ":" + diff[i]);
                        }

                        if (match > best) {
                            best = match;
                            bestFit = histogram;
                        } else if (match > second) {
                            second = match;
                            secondFit = histogram;
                        }
                        if (match > 80)   ;
                            //System.out.println(match + " at : (" + histogram1.getX() + "," + histogram1.getY() + ") (" + histogram.getX() + "," + histogram.getY() + ") ");
                    }
                }
            }
        } */



        if (bestFit != null)
        System.out.println("BEST FIT == " + best + " AT (" + bestFit.getX() + ", " + bestFit.getY() + ")");
        if (secondFit != null)
        System.out.println("Second FIT == " + second + " AT (" + secondFit.getX() + ", " + secondFit.getY() + ")");

        Point result = null;
        if (best > 85.00 && second > 85.00) {
            int newX = (bestFit.getX() + secondFit.getX()) / 2;
            int newY = (bestFit.getY() + secondFit.getY()) / 2;
            System.out.println("AVERAGE FIT == " + newX + ", " + newY);
            result = new Point(newX, newY);
        } else if (best > 85.00) {
            result = new Point(bestFit.getX(), bestFit.getY());
        } else {
            result = null;
        }

        //System.out.println("SECOND FIT AT (" + secondFit.getX() + ", " + secondFit.getY() + ")");
        //System.out.println("THIRD FIT AT (" + thirdFit.getX() + ", " + thirdFit.getY() + ")");


        return result;
    }


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
