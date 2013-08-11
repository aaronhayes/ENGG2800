package usb.event.listener;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import ui.WindowFrame;

/**
 * @author Aaron Hayes
 */
public class SerialPortReader implements SerialPortEventListener {

    private SerialPort serialPort;
    private WindowFrame windowFrame;
    private static int MASK = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;

    public SerialPortReader(SerialPort s, WindowFrame wf) {
        serialPort = s;
        windowFrame = wf;
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            serialPort.setEventsMask(MASK);
            serialPort.addEventListener(this);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }

    }

    /**
     * Read Data from Serial Port on event
     */
    public void serialEvent(SerialPortEvent event) {
        if (windowFrame.getSerialPortConnection().getStatus()) {
            if (event.isRXCHAR()) {
                try {
                    byte buffer[] = serialPort.readBytes(10);
                    if (buffer != null) {
                        String read = new String(buffer);
                        System.out.println(read);
                    }

                } catch (SerialPortException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
