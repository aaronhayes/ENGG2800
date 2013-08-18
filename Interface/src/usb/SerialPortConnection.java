package usb;

import jssc.SerialPortList;
import ui.WindowFrame;
import usb.event.listener.SerialPortReader;

/**
 * Listener for USB serial connection
 * @author Aaron Hayes
 */
public class SerialPortConnection {
    private String port = null;
    private boolean reading = false;
    private WindowFrame windowFrame;
    private SerialPortReader serialPortReader;

    /**
     * Basic Constructor
     */
    public SerialPortConnection(WindowFrame wf){
        windowFrame = wf;
        serialPortReader = new SerialPortReader(windowFrame);
    }

    /**
     * Update the COMS ports string
     * @param p The COMS Port
     */
    public void updatePort(String p) {
        port = p;
    }

    /**
     * Get available COM Ports
     */
    public String[] getAvailablePorts() {
        return SerialPortList.getPortNames();
    }

    /**
     * Toggle Reading Serial Data
     */
    public void toggle() {
        reading = !reading;

        if (reading) {
            if (port != null) {
                serialPortReader.openPort(port);
            }
        } else {
            serialPortReader.closePort();
        }
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
