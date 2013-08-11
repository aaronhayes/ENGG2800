package usb;

import jssc.SerialPort;
import jssc.SerialPortList;
import ui.WindowFrame;
import usb.event.listener.SerialPortReader;

/**
 * Listener for USB serial connection
 * @author Aaron Hayes
 */
public class SerialPortConnection {
    private static String PORT = "COM3";
    private boolean reading = false;
    private WindowFrame windowFrame;
    /**
     * Basic Constructor
     */
    public SerialPortConnection(WindowFrame wf){
        windowFrame = wf;

        String[] ports = SerialPortList.getPortNames();
        for (String com : ports) {
            System.out.println(com);
        }
        SerialPort serialPort = new SerialPort(PORT);
        new SerialPortReader(serialPort, windowFrame);

    }

    /**
     * Toggle Reading Serial Data
     */
    public void toggle() {
        reading = !reading;
    }

    /**
     * Stop Reading Serial Data
     */
    public void stop() {
        reading = false;
    }

    /**
     * Get reading status
     */
    public boolean getStatus() {
        return reading;
    }
}
