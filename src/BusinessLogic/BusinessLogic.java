package BusinessLogic;

import Exceptions.DataAccessException;
import Interfaces.*;
import Model.Referent;
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

    @Override
    public ArrayList<Referent> getReferentsByDesignation(String search)
        throws DataAccessException {
        return dataAccess.getReferentsByDesignation(search);
    }

    @Override
    public Referent getReferentById(int id) throws DataAccessException {
        return dataAccess.getReferentById(id);
    }

    @Override
    public ArrayList<Referent> getAllReferent() throws DataAccessException {
        return dataAccess.getAllReferent();
    }

    @Override
    public void deleteReferent(int id) throws DataAccessException {
        dataAccess.deleteReferent(id);
    }

    @Override
    public void addReferent(Referent referent) throws DataAccessException {
        dataAccess.addReferent(referent);
    }

    @Override
    public SchoolOfThought getSchoolsByID(int id) throws DataAccessException {
        return dataAccess.getSchoolsByID(id);
    }

    @Override
    public void updateReferent(Referent referent) throws DataAccessException {
        dataAccess.updateReferent(referent);
    }
}
