package BusinessLogic;

import Exceptions.DataAccessException;
import Interfaces.*;
import Model.Item;
import Model.Referent;
import Model.SchoolOfThought;

import java.time.LocalDate;
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

    @Override
    public ArrayList<Object[]> getItemSearchResultsByReferentId(int referentId) throws DataAccessException{
        return dataAccess.getItemSearchResultsByReferentId(referentId);
    }

    @Override
    public ArrayList<Object[]> getAllClientsForCombo()
            throws DataAccessException {
        return dataAccess.getAllClientsForCombo();
    }

    @Override
    public ArrayList<Object[]> getOrderLinesByClientId(int clientId)
            throws DataAccessException {
        return dataAccess.getOrderLinesByClientId(clientId);
    }

    @Override
    public ArrayList<String> getAllCountryNames()
            throws DataAccessException {
        return dataAccess.getAllCountryNames();
    }

    @Override
    public ArrayList<Object[]> getOrdersByCountryAndDates(
            String countryName,
            LocalDate startDate,
            LocalDate endDate
    ) throws DataAccessException {
        return dataAccess.getOrdersByCountryAndDates(countryName, startDate, endDate);
    }
}
