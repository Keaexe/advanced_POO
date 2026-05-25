package UserInterface;

import Interfaces.IController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeListener implements ActionListener {

    private IController controller;

    public HomeListener(IController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.displayHome();
    }
}
