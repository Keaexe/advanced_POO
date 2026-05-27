package UserInterface;

import Exceptions.DataAccessException;
import Interfaces.IController;
import javax.swing.JPanel;

public class DeleteRefPanel extends JPanel {

    public DeleteRefPanel(IController controller) throws DataAccessException {
        new ReadRefPanel(controller, "delete");
    }
}
