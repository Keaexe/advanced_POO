package BusinessLogic;

import Interfaces.IDataAccess;
import Model.Item;

import java.util.Random;

public class ItemPrediction {
    private IDataAccess dataAccess;

    public ItemPrediction(IDataAccess dataAccess){
        this.dataAccess = dataAccess;
    }

    public Item getPrediction(String message) throws Exception{
        Integer hash = message.hashCode();
        var generator = new Random();
        try{
            return dataAccess.getItemById((hash + generator.nextInt(10000)) % dataAccess.getItemCount());
        }catch (Exception e){
            throw new Exception(e.getMessage()); // CHANGE LATER !!!!
        }
    }
}
