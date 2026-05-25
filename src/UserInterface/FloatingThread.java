package UserInterface;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FloatingThread extends Thread {

    JLabel monk;

    public FloatingThread(JLabel monk) {
        this.monk = monk;
    }

    public void run() {
        Integer y = 1;
        Integer i = 0;
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                break;
            }
            monk.setLocation(monk.getX(), monk.getY() - y);
            if (i == 30) {
                i = 0;
                y *= -1;
            }
            try {
                sleep(100);
            } catch (InterruptedException e) {
                break;
            }
            i++;
        }
    }
}
