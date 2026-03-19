package Model;

import java.awt.*;

public class Item {
    private Integer id;
    private String name;
    private Double priceExVAT;
    private Double VATPercentage;
    private Integer leftInStock;
    private String description;
    private Image image;

    public Item(Integer id, String name, Double priceExVAT, Double VATPercentage, Integer leftInStock,
                String description, Image image) {
        this.id = id;
        this.name = name;
        this.priceExVAT = priceExVAT;
        this.VATPercentage = VATPercentage;
        this.leftInStock = leftInStock;
        this.description = description;
        this.image = image;
    }
}
