package UserInterface;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class FloatingThread extends Thread {

    JLabel monk;

    public FloatingThread(JLabel monk) {
        this.monk = monk;
    }

    public void run() {
        Integer y = 1;
        Integer i = 0;
        while (true) {
            monk.setLocation(monk.getX(), monk.getY() - y);
            if (i == 30) {
                i = 0;
                y *= -1;
            }
            try {
                sleep(60);
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(
                    null,
                    "Animation has been interrupted\n(" + e.getMessage() + ")",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
                break;
            }
            i++;
        }
    }
}
