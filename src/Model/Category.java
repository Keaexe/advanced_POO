package Model;

import Utils.ValidationUtils;

public class Category {
    private String name;
    private String description;

    public Category(String name, String description) throws Exception {
            this.setName(name);
            this.setDescription(description);

    }

    public void setName(String name) throws Exception {
        this.name = ValidationUtils.validateString(name, "name", true, 50);
    }

    public String getName(){
        return this.name;
    }

    public void setDescription(String description) throws Exception {
        this.description = ValidationUtils.validateString(description, "description", true);
    }

    public String getDescription(){
        return this.description;
    }


}
