package UserInterface;

import Controller.Controller;
import Interfaces.IController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateListener implements ActionListener {

    private IController controller;

    public CreateListener(IController controller) {
        controller = new Controller();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.displayCreateReferent();
    }
}
