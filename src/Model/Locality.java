package Model;

import Utils.ValidationUtils;

public class Locality {
    private Integer id;
    private String countryName;
    private String name;
    private String zipCode;
    private boolean isSupported;

    public Locality(Integer id,String name, String zipCode, String countryName, boolean isSupported){
        this.id = id;
        this.setName(name);
        this.setCountryName(countryName);
        this.setZipCode(zipCode);
        this.isSupported = isSupported;
    }

    public Locality(String name, String zipCode, String countryName, boolean isSupported) {
        this(null,name,zipCode, countryName, isSupported);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = ValidationUtils.validateString(name, "name", true, 255);
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = ValidationUtils.validateString(countryName, "countryName", true,50);
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = ValidationUtils.validateString(zipCode, "zip code", true,20);
    }

    public boolean isSupported() {
        return isSupported;
    }

    public void setSupported(boolean supported) {
        isSupported = supported;
    }
}
