package ook_project;

import java.util.List;

public class Ingredient {
    private final String id;
    private final String name;
    private final boolean available;
    private final List<String> dietaryTags;

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