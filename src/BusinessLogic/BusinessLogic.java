package BusinessLogic;

import Interfaces.*;

public class BusinessLogic implements IBusinessLogic {

    IController controller;

    public BusinessLogic(IController controller) {
        this.controller = controller;
    }
}
