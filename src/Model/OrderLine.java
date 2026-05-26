package Model;

import Utils.ValidationUtils;

public class OrderLine {
    private Integer orderId, itemId, quantity;
    private Double priceAtTheTime;

    public OrderLine(Integer orderId, Integer itemId, Integer quantity, Double priceAtTheTime) {
        this.orderId = orderId;
        this.itemId = itemId;
        setQuantity(quantity);
        setPriceAtTheTime(priceAtTheTime);
    }

    public OrderLine(Integer itemId, Integer quantity, Double priceAtTheTime) {
        this(null, itemId, quantity, priceAtTheTime);
    }


    public Integer getOrderId() {
        return orderId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = ValidationUtils.validateInteger(quantity,"quantity", true, 1, Integer.MAX_VALUE);
    }

    public Double getPriceAtTheTime() {
        return priceAtTheTime;
    }
    public void setPriceAtTheTime(Double priceAtTheTime) {
        this.priceAtTheTime = ValidationUtils.validateDouble(priceAtTheTime,"price at the time", true, 0.00, Double.MAX_VALUE);
    }
}
