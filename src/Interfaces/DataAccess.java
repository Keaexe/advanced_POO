package Interfaces;

import Model.Item;

import java.util.Dictionary;

public interface DataAccess {
    Dictionary<Integer,Item> getAllItems();
    Item getItemById(Integer id);
    Integer getItemCount();
}
