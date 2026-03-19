package BusinessLogic;

import Interfaces.DataAccess;
import Model.Item;

import java.util.Date;
import java.util.stream.IntStream;

public class ItemPrediction {
    private DataAccess dataAccess;

    public ItemPrediction(DataAccess dataAccess){
        this.dataAccess = dataAccess;
    }

    public Item getPrediction(String message){
        IntStream asciiCodes = message.chars();
        Integer totalAscii = asciiCodes.sum();
        return dataAccess.getItemById(totalAscii % dataAccess.getItemCount());
    }
}
