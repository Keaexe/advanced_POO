package UserInterface;

import Controller.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener {
    private Controller controller;

    public ExitListener(){
        controller = new Controller();
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.exit();
    }
}
