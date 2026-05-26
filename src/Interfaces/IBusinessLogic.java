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
}
