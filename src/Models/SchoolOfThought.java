package Models;

import Utils.ValidationUtils;

public class SchoolOfThought {
    private Integer id;
    private String name, description;


    public SchoolOfThought(String name, String description) {
        this(null, name, description);
    }

    public SchoolOfThought(Integer id, String name, String description) {
        this.id = id;
        setName(name);
        setDescription(description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = ValidationUtils.validateString(name, "name", true, 255);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = ValidationUtils.validateString(description,"description", true);
    }

    public Integer getId() {
        return id;
    }

}
