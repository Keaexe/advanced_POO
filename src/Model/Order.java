package Model;

import java.util.Date;

public class Order {
    private Integer id, employeeId, clientId;
    private Date creationTime;


    public Order(Integer id, Integer employeeId, Integer clientId, Date creationTime) {
        this.id = id;
        this.employeeId = employeeId;
        this.clientId = clientId;
        this.creationTime = creationTime;
    }
}
