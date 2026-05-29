package UserInterface.listeners;

import Interfaces.IController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateListener implements ActionListener {

    private IController controller;

    public UpdateListener(IController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.displayUpdateReferent();
    }
}
