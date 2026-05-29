package Interfaces;

import Exceptions.*;
import Models.*;
import java.time.LocalDate;
import java.util.ArrayList;

public interface IDataAccess {
    Referent getReferentById(int id) throws DataAccessException;
    ArrayList<Referent> getAllReferent() throws DataAccessException;
    ArrayList<Referent> getReferentsByDesignation(String search)
        throws DataAccessException;
    void addReferent(Referent referent) throws DataAccessException;
    void deleteReferent(int id) throws DataAccessException;
    void updateReferent(Referent referent) throws DataAccessException;

    SchoolOfThought getSchoolsByID(int id) throws DataAccessException;
    ArrayList<SchoolOfThought> getAllSchools() throws DataAccessException;

    ArrayList<Object[]> getItemSearchResultsByReferentId(int referentId)
        throws DataAccessException;

    ArrayList<Object[]> getOrderLinesByClientId(int clientId)
        throws DataAccessException;
    ArrayList<Object[]> getAllClientsForCombo() throws DataAccessException;

    ArrayList<String> getAllCountryNames() throws DataAccessException;
    ArrayList<Object[]> getOrdersByCountryAndDates(
        String countryName,
        LocalDate startDate,
        LocalDate endDate
    ) throws DataAccessException;
}
