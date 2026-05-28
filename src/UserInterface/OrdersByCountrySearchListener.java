package UserInterface;

import Interfaces.IController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdersByCountrySearchListener implements ActionListener {

    private IController controller;

    public OrdersByCountrySearchListener(IController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        controller.displayOrdersByCountrySearch();
    }
}