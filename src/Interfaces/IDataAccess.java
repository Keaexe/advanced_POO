package Interfaces;

import Exceptions.*;
import Model.*;
import java.util.ArrayList;

public interface IDataAccess {
    public ArrayList<Item> getAllItems() throws DataAccessException;
    public Item getItemById(int id) throws DataAccessException;
    public Integer getItemCount() throws DataAccessException;
    public Referent getReferentById(int id) throws DataAccessException;
    public ArrayList<Referent> getAllReferent() throws DataAccessException;
    public void addReferent(Referent referent) throws DataAccessException;
    public ArrayList<SchoolOfThought> getAllSchools()
        throws DataAccessException;
    public ArrayList<Referent> getReferentsByDesignation(String search)
        throws DataAccessException;
}
