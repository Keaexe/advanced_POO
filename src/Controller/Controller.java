package Controller;

import Interfaces.IController;
import Interfaces.IUserInterface;

public class Controller implements IController {
    private IUserInterface ui;

    public void exit(){
        System.exit(0);
    }
    @Override
    public void displayModification() {
        ui.displayModification();
    }
    @Override
    public void displaySearch() {
        ui.displaySearch();
    }
}
