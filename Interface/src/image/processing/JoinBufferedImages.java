package image.processing;

import image.TransmittedImage;
import image.Point;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Join two BufferedImage
 * @author Aaron Hayes
 */
public class JoinBufferedImages {

    /**
     * Join three BufferedImages to make one.
     * @param a BufferedImage 1
     * @param b BufferedImage 2
     * @param c BufferedImage 3
     * @param offsetA offset from image 1 to 2
     * @param offsetB offset from image 2 to 3
     * @return BufferedImage
     */
    public static BufferedImage join(BufferedImage a, BufferedImage b, BufferedImage c, int offsetA, int offsetB) {
        int width = a.getWidth() + b.getWidth() + c.getWidth() - offsetA - offsetB;
        BufferedImage result = new BufferedImage(width, a.getHeight(), BufferedImage.TYPE_INT_RGB);

        Graphics g = result.getGraphics();
        g.drawImage(a, 0, 0, null);
        g.drawImage(b, a.getWidth() - offsetA, 0, null);
        g.drawImage(c, width - c.getWidth(), 0, null);
        g.dispose();

        return result;
    }

    /**
     * Join a number of images from a list
     * @param images List of TransmittedImages to be joint
     * @param num number of images to be joint
     * @return BufferedImage result of joining
     */
    public static BufferedImage join(ArrayList<TransmittedImage> images, int num) {
        if (images == null) return null;
        if (images.size() < num) return null;
        TransmittedImage[] transmittedImages = new TransmittedImage[num];

        /* Get required dimensions for final image */
        int width = 0;
        int height = 0;
        for (int x = 0; x < num; x++) {
            transmittedImages[x] = images.get(images.size() - num + x);

            if (x > 0) {
                width += transmittedImages[x].getWidth();
                width -= transmittedImages[x].getXOffset();
            } else {
                width += transmittedImages[x].getWidth();
                height += transmittedImages[x].getHeight();
            }
        }

        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        Graphics g = result.getGraphics();
        g.drawImage(transmittedImages[0].getBufferedImage(), 0, 0, null);
        int currentWidth = transmittedImages[0].getWidth();
        for (int x = 1; x < num; x++) {
            g.drawImage(transmittedImages[x].getBufferedImage(), currentWidth - transmittedImages[x].getXOffset(), 0, null);
            currentWidth += (transmittedImages[x].getWidth() - transmittedImages[x].getXOffset());
        }
        g.dispose();

        return result;
    }

    /**
     * Join an Matrix of Buffered Into one buffered Image
     * @param images BufferedImage Array of Arrays
     * @param width Width of Buffered Image
     * @param height Height of Buffered Image
     * @return A joint buffered image with dimensions (width, height)
     */
    public static BufferedImage stitchArray(BufferedImage[][] images, int width, int height) {
        BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics graphics = result.getGraphics();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                graphics.drawImage(images[y][x], x, y, null);
            }
        }
        graphics.dispose();

        return result;

    }

    public static BufferedImage stitchTwoTransmittedImages(TransmittedImage image1, TransmittedImage image2) {
        if (image1 == null || image2 == null) {
            return null;
        }

        /* Get required dimensions for final image */
        int width = image1.getWidth() + image2.getWidth();
        int height = image1.getHeight();

        Point[] compare1 = SIFT.compareMerge(image1, image2);

        if (compare1[0] == null || compare1[1] == null) {
            System.err.println("Unable to Join Images");
            return null;
        }

        width -= (image1.getWidth() - compare1[0].getX());
        //width -= compare1[0].getX();

        //height += Math.abs(compare1[0].getY() - compare1[1].getY());

        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        Graphics g = bufferedImage.getGraphics();


        int x2 = compare1[0].getX();
        int y2 = Math.abs(compare1[0].getY() - compare1[1].getY());;
        int x = 0;
        int y = 0;

        g.drawImage(CropBufferedImage.crop(image1.getBufferedImage(), x, y), x, y, null);
        g.drawImage(CropBufferedImage.crop(image2.getBufferedImage(), x, 0), x2, 0, null);

        g.dispose();

        return bufferedImage;
    }

    public static BufferedImage stitchThreeTransmittedImages(TransmittedImage image1, TransmittedImage image2, TransmittedImage image3) {
        if (image1 == null || image2 == null || image3 == null) {
            return null;
        }

        /* Get required dimensions for final image */
        int width = image1.getWidth() + image2.getWidth() + image3.getWidth();
        int height = image1.getHeight();

        Point[] compare1 = SIFT.compareMerge(image1, image2);

        if (compare1[0] == null || compare1[1] == null) {
            System.err.println("Unable to Join Images");
            return null;
        }
        Point[] compare2 = SIFT.compareMerge(image2, image3);
        if (compare2[0] == null || compare2[1] == null) {
            System.err.println("Unable to Join Images");
            return null;
        }

        width -= (image1.getWidth() - compare1[0].getX());
        //width -= compare1[0].getX();
        width -= (image2.getWidth() - compare2[0].getX());
        //width -= compare2[0].getX();

        height += Math.abs(compare1[0].getY() - compare1[1].getY());
        height += Math.abs(compare2[0].getY() - compare2[1].getY());




        System.out.println(width);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

        Graphics g = bufferedImage.getGraphics();


        int x3 = width - image3.getWidth();
                //image1.getWidth() - (image1.getWidth() - compare1[0].getX() + compare1[1].getX()) + image2.getWidth() - (image2.getWidth() - compare2[0].getX() + compare2[1].getX());
        System.out.println(x3);
        int y3 = Math.abs(compare2[0].getY() - compare2[1].getY());;
        int x2 = compare1[0].getX();
        System.out.println(x2);
        int y2 = Math.abs(compare1[0].getY() - compare1[1].getY());;
        int x = 0;
        System.out.println(x);
        int y = Math.abs(compare1[0].getY() - compare1[1].getY());

        g.drawImage(image1.getBufferedImage(), x, y, null);
        g.drawImage(image2.getBufferedImage(), x2, y2, null);
        g.drawImage(image3.getBufferedImage(), x3, y3, null);

        g.dispose();

        return bufferedImage;
    }
}
