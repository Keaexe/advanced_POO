package Models;

import Utils.ValidationUtils;

public class Item {
    private Integer id;
    private String name;
    private Double priceExVAT;
    private Double VATPercentage;
    private Integer leftInStock;
    private String description;
    private String imageURL;
    private String categoryName;

    public Item(Integer id, String name, Double priceExVAT, Double VATPercentage, Integer leftInStock,
                String description, String imageURL, String categoryName) {
        this.id = id;
        setName(name);
        setVATPercentage(VATPercentage);
        setPriceExVAT(priceExVAT);
        setLeftInStock(leftInStock);
        setDescription(description);
        this.imageURL = imageURL;
        setCategoryName(categoryName);
    }

    public Item(String name, Double priceExVAT, Double VATPercentage, Integer leftInStock,
                String description, String imageURL, String categoryName) {
        this(null,name, priceExVAT, VATPercentage, leftInStock, description, imageURL, categoryName);
    }



    public Double getFullPrice(){
        Double coefficient = 1 + (this.VATPercentage/100);

        return this.priceExVAT * coefficient;
    }

    public Double getPriceExVAT(){
        return this.priceExVAT;
    }

    public void setPriceExVAT(Double priceExVAT){
        this.priceExVAT = ValidationUtils.validateDouble(priceExVAT, "Price without VAT", true, 0.00, Double.MAX_VALUE);
    }

    public Double getVATPercentage(){
        return this.VATPercentage;
    }

    public void setVATPercentage(Double VATPercentage){
        this.VATPercentage = ValidationUtils.validateDouble(VATPercentage, "VAT percentage", true, 0.00, 100.00);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = ValidationUtils.validateString(name, "Name", true, 255);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = ValidationUtils.validateString(description,"Description", true);
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = ValidationUtils.validateString(categoryName, "Category's name", true, 255);

    }

    public Integer getLeftInStock() {
        return leftInStock;
    }

    public void setLeftInStock(Integer leftInStock) {
        this.leftInStock = ValidationUtils.validateInteger(leftInStock,"'Left in stock'", true, 0, Integer.MAX_VALUE);
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Integer getId() {
        return id;
    }
}
