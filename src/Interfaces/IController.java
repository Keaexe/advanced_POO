package Interfaces;

import Exceptions.*;
import Model.*;
import java.util.ArrayList;

public interface IController {
    public void exit();
    public void displayCreateReferent();
    public void displayUpdateReferent();
    public void displayDeleteReferent();
    public void displayItemSearch();
    public void displayReferentSearch();
    public void displayHome();
    public void setUI(IUserInterface ui);
    public void setBusinessLogic(IBusinessLogic bL);
    public ArrayList<SchoolOfThought> getAllSchools()
        throws DataAccessException;
    public ArrayList<Referent> getReferentsByDesignation(String search)
        throws DataAccessException;
    public Referent getReferentById(int id) throws DataAccessException;
    public ArrayList<Referent> getAllReferent() throws DataAccessException;
    public void deleteReferent(int id) throws DataAccessException;
    public void addReferent(Referent referent) throws DataAccessException;
}
