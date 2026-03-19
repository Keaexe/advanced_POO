package BusinessLogic;

import Interfaces.IDataAccess;
import Model.Item;

import java.util.stream.IntStream;

public class ItemPrediction {
    private IDataAccess dataAccess;

    public ItemPrediction(IDataAccess dataAccess){
        this.dataAccess = dataAccess;
    }

    public Item getPrediction(String message){
        IntStream asciiCodes = message.chars();
        Integer totalAscii = asciiCodes.sum();
        return dataAccess.getItemById(totalAscii % dataAccess.getItemCount());
    }
}
