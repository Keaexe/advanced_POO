package Model;

import Utils.StringUtils;

import java.awt.*;

public class Item {
    private Integer id;
    private String name;
    private Double priceExVAT;
    private Double VATPercentage;
    private Integer leftInStock;
    private String description;
    private Image image;
    private String categoryName;

    public Item(Integer id, String name, Double priceExVAT, Double VATPercentage, Integer leftInStock,
                String description, Image image, String categoryName) {
        this.id = id;
        setName(name);
        this.priceExVAT = priceExVAT;
        this.VATPercentage = VATPercentage;
        this.leftInStock = leftInStock;
        setDescription(description);
        this.image = image;
        setCategoryName(categoryName);
    }

    public Double getFullPrice(){
        Double coefficient = 1 + (this.VATPercentage/100);

        return this.priceExVAT * coefficient;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.requireNotBlank(name, "name");
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = StringUtils.requireNotBlank(description,"description");
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = StringUtils.requireNotBlank(categoryName, "categoryName");
    }
}
