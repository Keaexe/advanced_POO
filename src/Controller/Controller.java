package Controller;

import Interfaces.IController;
import Interfaces.IUserInterface;

public class Controller implements IController {
    private IUserInterface ui;

    public void exit(){
        System.exit(0);
    }
    @Override
    public void displaySearch() {
        ui.displaySearch();
    }

    @Override
    public void displayCreate() {

    }

    @Override
    public void displayUpdate() {

    }

    @Override
    public void displayDelete() {

    }

    @Override
    public void displayItemSearch() {

    }

    @Override
    public void displayReferentSearch() {

    }
}
