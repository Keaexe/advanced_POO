package UserInterface;

import Interfaces.IController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateListener implements ActionListener {
    private IController controller;

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.displayCreate();
    }
}
