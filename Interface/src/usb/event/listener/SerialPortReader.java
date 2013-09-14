package usb.event.listener;

import image.TransmittedImage;
import image.processing.ByteArrayToBufferedImage;
import image.processing.JoinBufferedImages;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import ui.WindowFrame;

import java.awt.image.BufferedImage;

/**
 * @author Aaron Hayes
 */
public class SerialPortReader implements SerialPortEventListener {

    private SerialPort serialPort = null;
    private WindowFrame windowFrame;
    private static final int MASK = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;

    private BufferedImage[][] imageMap;
    private int currentX;
    private int currentY;

    public SerialPortReader(WindowFrame wf) {
        windowFrame = wf;
        imageMap = new BufferedImage[TransmittedImage.IMG_HEIGHT][TransmittedImage.IMG_WIDTH];
        currentX = 0;
        currentY = 0;
    }

    /**
     * Read Data from Serial Port on event
     */
    public void serialEvent(SerialPortEvent event) {
        if (windowFrame.getSerialPortConnection().getStatus()) {
            if (event.isRXCHAR()) {
                try {
                    byte buffer[] = serialPort.readBytes(1);
                    if (buffer != null) {
                        String read = new String(buffer);
                        System.out.println(read);

                        imageMap[currentY][currentX++] = ByteArrayToBufferedImage.Convert(buffer);

                        if (currentX == TransmittedImage.IMG_WIDTH) {
                            currentX = 0;
                            currentY++;
                            if (currentY == TransmittedImage.IMG_HEIGHT) {
                                uploadImage();
                            }
                        }
                    }

                } catch (SerialPortException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Convert imageMap into single image and send to screen
     */
    private void uploadImage() {
        currentY = 0;
        currentX = 0;

        BufferedImage bufferedImage = JoinBufferedImages.stitchArray(imageMap, TransmittedImage.IMG_WIDTH, TransmittedImage.IMG_HEIGHT);
        windowFrame.addImage(new TransmittedImage(bufferedImage));
    }

    /**
     * Close the serial Port
     */
    public void closePort() {
        if (serialPort != null) {
            try {
                if (serialPort.isOpened()) {
                    serialPort.closePort();
                }
            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Open a new serial port
     * @param port Serial Port Connection
     */
    public void openPort(String port) {
        serialPort = new SerialPort(port);
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            serialPort.setEventsMask(MASK);
            serialPort.addEventListener(this);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }
}
