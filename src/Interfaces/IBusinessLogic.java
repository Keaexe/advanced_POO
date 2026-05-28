package Interfaces;

import Exceptions.*;
import Model.*;
import java.util.ArrayList;

public interface IBusinessLogic {
    public ArrayList<SchoolOfThought> getAllSchools()
        throws DataAccessException;
    public ArrayList<Referent> getReferentsByDesignation(String search)
        throws DataAccessException;
    public Referent getReferentById(int id) throws DataAccessException;
    public ArrayList<Referent> getAllReferent() throws DataAccessException;
    public void deleteReferent(int id) throws DataAccessException;
    public void addReferent(Referent referent) throws DataAccessException;
    public SchoolOfThought getSchoolsByID(int id) throws DataAccessException;
    public void updateReferent(Referent referent) throws DataAccessException;
    public ArrayList<Object[]> getItemSearchResultsByReferentId(int referentId) throws DataAccessException;
}
