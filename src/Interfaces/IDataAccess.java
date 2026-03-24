package Interfaces;

import Model.Item;

import java.util.Dictionary;

public interface IDataAccess {
    Dictionary<Integer,Item> getAllItems();
    Item getItemById(Integer id) throws Exception;
    Integer getItemCount() throws Exception;
}
