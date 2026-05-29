package Models;

import Utils.ValidationUtils;

public class Client {
    private Integer id, deliveryAddressId;
    private String firstName, lastName;

    public Client(Integer id, Integer deliveryAddressId, String firstName, String lastName){
        this.setFirstName(firstName);
        this.setLastName(lastName);

        this.id = id;
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = ValidationUtils.validateString(firstName,"firstname", true ,50);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = ValidationUtils.validateString(lastName, "lastName", true, 100);
    }

    public Integer getId() {
        return id;
    }

    public Integer getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Integer deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }
}
