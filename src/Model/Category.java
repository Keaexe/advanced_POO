package Model;

import Utils.StringUtils;

public class Category {
    private String name;
    private String description;

    public Category(String name, String description) throws Exception {
            this.setName(name);
            this.setDescription(description);

    }

    public void setName(String name) throws Exception {
        this.name = StringUtils.requireNotBlank(name, "name");
    }

    public String getName(){
        return this.name;
    }

    public void setDescription(String description) throws Exception {
        this.description = StringUtils.requireNotBlank(description, "description");
    }

    public String getDescription(){
        return this.description;
    }


}
