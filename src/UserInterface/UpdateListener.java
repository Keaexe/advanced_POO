package UserInterface;

import Controller.Controller;
import Interfaces.IController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateListener implements ActionListener {

    private IController controller;

    public UpdateListener() {
        controller = new Controller();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.displayUpdateReferent();
    }
}
