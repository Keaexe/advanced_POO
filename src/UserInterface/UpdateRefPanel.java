package UserInterface;

import Exceptions.DataAccessException;
import Interfaces.IController;
import Model.Referent;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class UpdateRefPanel extends JPanel {

    public UpdateRefPanel(IController controller) throws DataAccessException {
        this.setLayout(new BorderLayout());
        this.add(new ReadRefPanel(controller, "update"), BorderLayout.CENTER);
    }

    public UpdateRefPanel(IController controller, Referent referent)
        throws DataAccessException {
        this.setLayout(new BorderLayout());
        this.add(
            new CreateRefPanel(controller, "update", referent),
            BorderLayout.CENTER
        );
    }
}
