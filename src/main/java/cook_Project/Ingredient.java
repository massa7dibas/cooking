package cook_Project;

import java.util.List;

public class Ingredient {
    private String id;
    private String name;
    private boolean available;
    private List<String> dietaryTags;

    public Ingredient(String id, String name, boolean available, List<String> dietaryTags) {
        this.id = id;
        this.name = name;
        this.available = available;
        this.dietaryTags = dietaryTags;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }

    public List<String> getDietaryTags() {
        return dietaryTags;
    }
}
