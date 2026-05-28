package UserInterface;

import Interfaces.IController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderLinesSearchListener implements ActionListener {

    private IController controller;

    public OrderLinesSearchListener(IController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        controller.displayOrderLinesSearch();
    }
}