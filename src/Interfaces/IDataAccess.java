package Interfaces;

import Exceptions.*;
import Model.*;
import java.util.ArrayList;

public interface IDataAccess {
    ArrayList<Item> getAllItems() throws DataAccessException;
    Item getItemById(int id) throws DataAccessException;
    Integer getItemCount() throws DataAccessException;
    Referent getReferentById(int id) throws DataAccessException;
    ArrayList<Referent> getAllReferent() throws DataAccessException;
    void addReferent(Referent referent) throws DataAccessException;
}
