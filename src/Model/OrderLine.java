package Model;

public class OrderLine {
    private Integer orderId, itemId, quantity;
    private Double priceAtTheTime;


    public OrderLine(Integer orderId, Integer itemId, Integer quantity, Double priceAtTheTime) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.quantity = quantity;
        this.priceAtTheTime = priceAtTheTime;
    }
}
