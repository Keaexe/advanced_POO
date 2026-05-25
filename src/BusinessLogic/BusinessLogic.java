package BusinessLogic;

import Exceptions.DataAccessException;
import Interfaces.*;
import Model.SchoolOfThought;
import java.util.ArrayList;

public class BusinessLogic implements IBusinessLogic {

    IController controller;
    IDataAccess dataAccess;

    public BusinessLogic(IController controller) {
        this.controller = controller;
    }

    @Override
    public ArrayList<SchoolOfThought> getAllSchools()
        throws DataAccessException {
        return dataAccess.getAllSchools();
    }

    public void setDataAccess(IDataAccess dataAccess) {
        this.dataAccess = dataAccess;
    }
}
