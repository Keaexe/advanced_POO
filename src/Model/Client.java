package Model;

import Utils.StringUtils;

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
        this.firstName = StringUtils.requireNotBlank(firstName,"firstname");
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = StringUtils.requireNotBlank(lastName, "lastName");
    }
}
