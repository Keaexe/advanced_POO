package Interfaces;

import Model.*;
import java.sql.SQLException;
import java.util.Dictionary;

public interface IDataAccess {
    Dictionary<Integer, Item> getAllItems();
    Item getItemById(int id) throws SQLException;
    Integer getItemCount() throws SQLException;
    Referent getReferentById(int id) throws SQLException;
    Dictionary<Integer, Referent> getAllReferent();
    void addReferent(Referent referent) throws SQLException;
}
