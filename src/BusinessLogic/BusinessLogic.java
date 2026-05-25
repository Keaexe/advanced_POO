package BusinessLogic;

import Exceptions.DataAccessException;
import Interfaces.*;
import Model.SchoolOfThought;
import java.util.ArrayList;

public class BusinessLogic implements IBusinessLogic {

    IController controller;

    public BusinessLogic(IController controller) {
        this.controller = controller;
    }

    @Override
    public ArrayList<SchoolOfThought> getAllSchools()
        throws DataAccessException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
            "Unimplemented method 'getAllSchools'"
        );
    }
}
