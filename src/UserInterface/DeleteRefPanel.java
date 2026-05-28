package UserInterface;

import Exceptions.DataAccessException;
import Interfaces.IController;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class DeleteRefPanel extends JPanel {

    public DeleteRefPanel(IController controller) throws DataAccessException {
        this.setLayout(new BorderLayout());
        this.add(new ReadRefPanel(controller, "delete"), BorderLayout.CENTER);
    }
}
