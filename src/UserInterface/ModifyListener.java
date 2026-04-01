package UserInterface;

import Interfaces.IController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyListener implements ActionListener {
    private IController controller;


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.displayModification();
    }
}
