package UserInterface;

import Exceptions.*;
import java.awt.*;
import javax.swing.*;

public class HomePanel extends JPanel {

    private JLabel monk;
    private JLabel welcomeMessage;

    public HomePanel() throws UIException {
        this.setLayout(new BorderLayout());
        this.monk = new JLabel();
        this.welcomeMessage = new JLabel();
        final int MONK_WIDTH = 100;
        try {
            monk.setIcon(
                new ImageIcon(
                    new ImageIcon("resources/monk.png")
                        .getImage()
                        .getScaledInstance(
                            MONK_WIDTH,
                            MONK_WIDTH + 10,
                            Image.SCALE_SMOOTH
                        )
                )
            );
            welcomeMessage.setText(
                "Welcome to the world where the magic happens"
            );
            monk.setHorizontalAlignment(SwingConstants.RIGHT);
            welcomeMessage.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(monk, BorderLayout.SOUTH);
            this.add(welcomeMessage, BorderLayout.CENTER);
        } catch (NullPointerException exception) {
            throw new UIException(
                "Image or text failed to render",
                exception.getMessage()
            );
        }
    }
}
