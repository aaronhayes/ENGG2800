package usb.event.listener;

import image.TransmittedImage;
import image.processing.ByteArrayToBufferedImage;
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

    public static final int WIDTH = 200;
    public static final int HEIGHT = 160;

    private byte[] byteMap;
    private int currentX;
    private int currentY;

    public SerialPortReader(WindowFrame wf) {
        windowFrame = wf;
        byteMap = new byte[WIDTH * HEIGHT];
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
                    byte buffer[] = serialPort.readBytes(32);
                    if (buffer != null) {
                        for (int b = 0; b < buffer.length; b++) {
                            //int p = buffer[b] & 0xFF;
                            //System.out.println(p + " (" + currentX + ", " + currentY + ")");
                            byteMap[(currentY * WIDTH) + currentX] = buffer[b];
                            currentX++;
                            if (currentX >= WIDTH) {
                                currentX = 0;
                                currentY++;
                                if (currentY >= HEIGHT) {
                                    uploadImage();
                                    break;
                                }
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
        //BufferedImage bufferedImage = JoinBufferedImages.stitchArray(imageMap, TransmittedImage.IMG_WIDTH, TransmittedImage.IMG_HEIGHT);
        BufferedImage bufferedImage = ByteArrayToBufferedImage.Convert(byteMap, TransmittedImage.IMG_WIDTH, TransmittedImage.IMG_HEIGHT);
        windowFrame.addImage(new TransmittedImage(bufferedImage, WIDTH, HEIGHT));

        for (int i = 0; i < WIDTH * HEIGHT; i++) {
            byteMap[i] = 0;
        }
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
            serialPort.setParams(SerialPort.BAUDRATE_19200, SerialPort.DATABITS_8, SerialPort.STOPBITS_2, SerialPort.PARITY_NONE);
            serialPort.setEventsMask(MASK);
            serialPort.addEventListener(this);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }
}
