package image.processing;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;

/**
 * Convert a Byte[] to a BufferedImage
 * @author Aaron Hayes
 */
public class ByteArrayToBufferedImage {

    /**
     * Convert byte array into 8 bit gray scale image
     * @param bytes Byte[] to be converted
     * @param width width of the image in pixels
     * @param height height of the immage in pixels
     */
    public static BufferedImage Convert(byte[] bytes, int width, int height) {
        ColorSpace colorSpace = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        int[] bits =  { 8 };
        ColorModel colorModel = new ComponentColorModel(colorSpace, bits, false, true, Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
        SampleModel sampleModel = colorModel.createCompatibleSampleModel(width, height);
        DataBufferByte dataBufferByte = new DataBufferByte(bytes, width * height);
        WritableRaster writableRaster = Raster.createWritableRaster(sampleModel, dataBufferByte, null);
        return new BufferedImage(colorModel, writableRaster, false, null);
    }

}
