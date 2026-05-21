package Model;

import Utils.StringUtils;

public class Locality {
    private String countryName;
    private String name;
    private String zipCode;
    private boolean isSupported;

    public Locality(String name, String zipCode, String countryName, boolean isSupported){
        this.setName(name);
        this.setCountryName(countryName);
        this.setZipCode(zipCode);
        this.isSupported = isSupported;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = StringUtils.requireNotBlank(name, "name");
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = StringUtils.requireNotBlank(countryName, "countryName");
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = StringUtils.requireNotBlank(zipCode, "zipCode");
    }

    public boolean isSupported() {
        return isSupported;
    }

    public void setSupported(boolean supported) {
        isSupported = supported;
    }
}
