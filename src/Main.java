import BusinessLogic.BusinessLogic;
import Controller.Controller;
import UserInterface.MainWindow;

public class Main {

    public static void main(String[] args) {
        var controller = new Controller();
        controller.setUI(new MainWindow(controller));
        controller.setBusinessLogic(new BusinessLogic(controller));
    }
}
