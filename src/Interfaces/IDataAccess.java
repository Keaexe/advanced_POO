package Interfaces;

import Model.Item;

import java.sql.SQLException;
import java.util.Dictionary;

public interface IDataAccess {
    Dictionary<Integer,Item> getAllItems();
    Item getItemById(Integer id) throws SQLException;
    Integer getItemCount() throws SQLException;
}
