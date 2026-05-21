package Controller;

import Interfaces.IController;
import Interfaces.IUserInterface;

public class Controller implements IController {

    private IUserInterface ui;

    public void exit() {
        System.exit(0);
    }

    @Override
    public void displayCreateReferent() {
        ui.displayCreateReferent();
    }

    @Override
    public void displayUpdateReferent() {
        ui.displayUpdateReferent();
    }

    @Override
    public void displayDeleteReferent() {
        ui.displayDeleteReferent();
    }

    @Override
    public void displayItemSearch() {
        ui.displayItemSearch();
    }

    @Override
    public void displayReferentSearch() {
        ui.displayReferentSearch();
    }
}
