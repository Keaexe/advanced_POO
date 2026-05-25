import BusinessLogic.BusinessLogic;
import Controller.Controller;
import DataAccess.DBAccess;
import Exceptions.DataAccessException;
import UserInterface.MainWindow;
import javax.swing.*;

void main() {
    var controller = new Controller();
    var mainWindow = new MainWindow(controller);
    controller.setUI(mainWindow);
    var business = new BusinessLogic(controller);
    try {
        business.setDataAccess(new DBAccess());
    } catch (DataAccessException e) {
        JOptionPane.showMessageDialog(
            mainWindow,
            "Cannot access data",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
    controller.setBusinessLogic(business);
}
