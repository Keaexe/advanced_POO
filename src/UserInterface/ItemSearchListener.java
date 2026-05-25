package UserInterface;

import Interfaces.IController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ItemSearchListener implements ActionListener {

    private IController controller;

    public ItemSearchListener(IController controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        controller.displayItemSearch();
    }
}
