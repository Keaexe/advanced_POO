import DataAccess.DBAccess;
import Model.Referent;
import UserInterface.MainWindow;
import Interfaces.*;

void main() {
    var mainWindow = new MainWindow();
    try{
        IDataAccess dbAccess = new DBAccess();
        System.out.println(dbAccess.toString());
        dbAccess.addReferent(new Referent(null,
                "test", "test", "test", LocalDate.now(), true,
                1, null, null));
    }catch (Exception e){
        System.out.println("SWALALA");
        System.out.println(e.getMessage());
    }
}
