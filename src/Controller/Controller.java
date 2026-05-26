package Controller;

import Exceptions.DataAccessException;
import Interfaces.*;
import Model.Referent;
import Model.SchoolOfThought;
import java.util.ArrayList;

public class Controller implements IController {

    private IUserInterface ui;
    private IBusinessLogic bL;

    public Controller() {}

    public void exit() {
        System.exit(0);
    }

    @Override
    public void displayCreateReferent() {
        ui.displayCreateReferent();
    }

    @Override
    public void displayUpdateReferent() {
        ui.displayUpdateReferent();
    }

    @Override
    public void displayDeleteReferent() {
        ui.displayDeleteReferent();
    }

    @Override
    public void displayItemSearch() {
        ui.displayItemSearch();
    }

    @Override
    public void displayReferentSearch() {
        ui.displayReferentSearch();
    }

    @Override
    public void displayHome() {
        ui.displayHome();
    }

    @Override
    public void setUI(IUserInterface ui) {
        this.ui = ui;
    }

    @Override
    public void setBusinessLogic(IBusinessLogic bL) {
        this.bL = bL;
    }

    @Override
    public ArrayList<SchoolOfThought> getAllSchools()
        throws DataAccessException {
        return bL.getAllSchools();
    }

    @Override
    public ArrayList<Referent> getReferentsByDesignation(String search)
        throws DataAccessException {
        return bL.getReferentsByDesignation(search);
    }

    @Override
    public Referent getReferentById(int id) throws DataAccessException {
        return bL.getReferentById(id);
    }

    @Override
    public ArrayList<Referent> getAllReferent() throws DataAccessException {
        return bL.getAllReferent();
    }

    @Override
    public void deleteReferent(int id) throws DataAccessException {
        bL.deleteReferent(id);
    }

    @Override
    public void addReferent(Referent referent) throws DataAccessException {
        bL.addReferent(referent);
    }
}
