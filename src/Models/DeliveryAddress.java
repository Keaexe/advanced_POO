package Models;

import Utils.ValidationUtils;

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

    public DeliveryAddress(Integer localityId, String numInStreet, String streetName, boolean isPickupPoint, String box) {
        this(null, localityId, numInStreet, streetName, isPickupPoint, box);
    }

    public String getNumInStreet() {
        return numInStreet;
    }

    public void setNumInStreet(String numInStreet) {
        this.numInStreet = ValidationUtils.validateString(numInStreet,"numInStreet", true, 20);
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = ValidationUtils.validateString(streetName,"streetName", true, 255);
    }

    public String getBox() {
        return box;
    }

    public void setBox(String box) {
        this.box = ValidationUtils.validateString(box,"box",false,10);
    }

    public boolean isPickupPoint() {
        return isPickupPoint;
    }

    public void setPickupPoint(boolean pickupPoint) {
        isPickupPoint = pickupPoint;
    }

    public Integer getLocalityId() {
        return localityId;
    }

    public void setLocalityId(Integer localityId) {
        this.localityId = localityId;
    }

    public Integer getId() {
        return id;
    }
}
