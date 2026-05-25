package Interfaces;

import Exceptions.*;
import Model.*;
import java.util.ArrayList;

public interface IController {
    void exit();
    void displayCreateReferent();
    void displayUpdateReferent();
    void displayDeleteReferent();
    void displayItemSearch();
    void displayReferentSearch();
    void displayHome();
    void setUI(IUserInterface ui);
    void setBusinessLogic(IBusinessLogic bL);
    ArrayList<SchoolOfThought> getAllSchools() throws DataAccessException;
}
