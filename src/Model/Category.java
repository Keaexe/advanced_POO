package Model;

public class Category {
    private String name;
    private String description;

    public Category(String name, String description){
        try {
            this.setName(name);
            this.setDescription(description);
        } catch (Exception e) {
            // Should be removed
            System.out.println(e.getMessage());
        }
    }

    public void setName(String name) throws Exception {
        boolean isBlank = name.isBlank();

        if(!isBlank){
            String strippedName = name.strip();
            this.name = name;
        } else{
            throw new IllegalArgumentException("<name> shouldn't be an empty string or null.");
        }
    }

    public String getName(){
        return this.name;
    }

    public void setDescription(String description) throws Exception {
        boolean isBlank = description.isBlank();

        if(!isBlank){
            String strippedDescription = description.strip();
            this.description = description;
        } else{
            throw new IllegalArgumentException("<description> shouldn't be an empty string or null.");
        }
    }

    public String getDescription(){
        return this.description;
    }


}
