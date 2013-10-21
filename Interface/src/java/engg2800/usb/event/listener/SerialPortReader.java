package engg2800.usb.event.listener;

import engg2800.image.TransmittedImage;
import engg2800.image.processing.ByteArrayToBufferedImage;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import engg2800.ui.WindowFrame;

import java.awt.image.BufferedImage;

/**
 * Read Transmission from Serial COMS Port
 * @author Aaron Hayes
 */
public class SerialPortReader implements SerialPortEventListener {

    private SerialPort serialPort = null;
    private WindowFrame windowFrame;
    private static final int MASK = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;

    public static final int WIDTH = 200;
    public static final int HEIGHT = 160;

    private byte[] byteMap;
    private byte[] lastFour;
    private int currentX;
    private int currentY;
    private boolean readingImage = false;
    private int count;

    public SerialPortReader(WindowFrame wf) {
        windowFrame = wf;
        byteMap = new byte[WIDTH * HEIGHT];
        lastFour = new byte[4];
        reset();
    }

    /**
     * Read Data from Serial Port on event
     * @param event SerialPortEvent
     */
    public void serialEvent(SerialPortEvent event) {
        if (windowFrame.getSerialPortConnection().getStatus()) {
            if (event.isRXCHAR()) {
                try {
                    byte buffer[] = serialPort.readBytes(32);
                    if (buffer != null) {
                        for (byte aBuffer : buffer) {
                            if (readingImage) {
                                //int p = buffer[b] & 0xFF;
                                //System.out.println(p + " (" + currentX + ", " + currentY + ")");
                                /*byteMap[(currentY * WIDTH) + currentX] = aBuffer;
                                currentX++;
                                if (currentX >= WIDTH) {
                                    currentX = 0;
                                    currentY++;
                                    if (currentY >= HEIGHT) {
                                        uploadImage();
                                        break;
                                    }
                                }*/
                            	
                            	byteMap[count++] = aBuffer;
                            	
                            	if (count >= (WIDTH * HEIGHT)) {
                            		uploadImage();
                            	}
                                updateLastByte(aBuffer);
                            } else {
                                updateLastByte(aBuffer);
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
     * Add received byte to lst
     * @param b byte
     */
    private void updateLastByte(byte b) {
    	lastFour[3] = lastFour[2];
    	lastFour[2] = lastFour[1];
        lastFour[1] = lastFour[0];
        lastFour[0] = b;
        checkLastEight();
    }

    /**
     * Check the last eight bytes for start/stop signal
     */
    private void checkLastEight() {

        /* check for start signal */
        if(lastFour[3] == (byte) 0xFF &&
        		lastFour[2] == (byte) 0x00 &&
        		lastFour[1] == (byte) 0xFF &&
        		lastFour[0] == (byte) 0x00) {
            readingImage = true;
            System.out.println("Got Start Signal");
            return;
        }

        /* check for stop signal */
        if(lastFour[3] == (byte) 0x00 &&
           lastFour[2] == (byte) 0xFF &&
           lastFour[1] == (byte) 0x00 &&
           lastFour[0] == (byte) 0xFF) {
        	System.out.println("Got Stop Signal");
            if (readingImage) {
                fillImage();
                uploadImage();
            } else {
            	reset();
            }
        }
    }

    /**
     * Got stop signal from engg2800.image, fill in missing pixels.
     */
    private void fillImage() {
        //for (int i = 0; i < WIDTH * HEIGHT; i++) {
        //
        //}

    }

    /**
     * Convert imageMap into single engg2800.image and send to screen
     */
    private void uploadImage() {

        //BufferedImage bufferedImage = JoinBufferedImages.stitchArray(imageMap, TransmittedImage.IMG_WIDTH, TransmittedImage.IMG_HEIGHT);
        BufferedImage bufferedImage = ByteArrayToBufferedImage.Convert(byteMap, WIDTH, HEIGHT);
        windowFrame.addImage(new TransmittedImage(bufferedImage, WIDTH, HEIGHT));
        reset();

    }
    
    /**
     * Reset variables to beginning of an engg2800.image
     */
    private void reset() {
        currentY = 0;
        currentX = 0;
        count = 0;
        for (int i = 0; i < WIDTH * HEIGHT; i++) {
            byteMap[i] = 0;
        }

        readingImage = false;
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
