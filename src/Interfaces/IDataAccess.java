package Interfaces;

import java.util.ArrayList;

import Model.*;

public interface IDataAccess {
    ArrayList<Item> getAllItems() throws Exception;
    Item getItemById(int id) throws Exception;
    Integer getItemCount() throws Exception;
    Referent getReferentById(int id) throws Exception;
    ArrayList<Referent> getAllReferent() throws Exception;
    void addReferent(Referent referent) throws Exception;
}
