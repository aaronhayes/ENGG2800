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
    private String port = "COM3";
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
        SerialPort serialPort = new SerialPort(port);
        new SerialPortReader(serialPort, windowFrame);

    }

    /**
     *
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
