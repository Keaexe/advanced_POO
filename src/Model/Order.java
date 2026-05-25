package Model;

import Utils.ValidationUtils;
import java.time.LocalDate;

public class Order {
    private Integer id, employeeId, clientId;
    private LocalDate creationTime;


    public Order(Integer employeeId, Integer clientId, LocalDate creationTime) {
        this(null, employeeId, clientId, creationTime);
    }

    public Order(Integer id, Integer employeeId, Integer clientId, LocalDate creationTime) {
        this.id = id;
        this.employeeId = employeeId;
        this.clientId = clientId;
        setCreationTime(creationTime);
    }

    public Integer getId() {
        return id;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public LocalDate getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDate creationTime) {
        this.creationTime = ValidationUtils.validateDate(creationTime, "Creation time", true, false,true,false);
    }
}
