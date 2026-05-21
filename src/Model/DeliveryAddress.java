package Model;

import Utils.StringUtils;

public class DeliveryAddress {
    private Integer id, localityId;
    private String numInStreet, streetName, box;
    private boolean isPickupPoint;

    public DeliveryAddress(Integer id, Integer localityId, String numInStreet, String streetName, boolean isPickupPoint, String box) {
        this.setNumInStreet(numInStreet);
        this.setStreetName(streetName);
        this.setBox(box);
        this.id = id;
        this.localityId = localityId;
        this.isPickupPoint = isPickupPoint;
    }


    public String getNumInStreet() {
        return numInStreet;
    }

    public void setNumInStreet(String numInStreet) {
        this.numInStreet = StringUtils.requireNotBlank(numInStreet,"numInStreet");
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = StringUtils.requireNotBlank(streetName,"streetName");
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = StringUtils.requireNotBlankNullable(box,"box");
    }
}
