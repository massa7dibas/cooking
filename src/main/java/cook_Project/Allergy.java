package cook_Project;

public class Allergy {
    private final String type;
    private final String description;

    public Allergy(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}