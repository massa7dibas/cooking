package ook_project;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static ook_project.Application.getAllRecipes;

public class AISuggestionService {
    private AISuggestionService() {}

    public static List suggestRecipes(RecipeRequest request) {
        return getAllRecipes().stream()
                .filter(r -> request.getDietaryPreferences().isEmpty() || new HashSet<>(r.getDietaryTags()).containsAll(request.getDietaryPreferences()))
                .filter(r -> request.getMaxPrepTime() == null || r.getPrepTime() <= request.getMaxPrepTime())
                .filter(r -> new HashSet<>(request.getAvailableIngredients()).containsAll(r.getIngredients()))
                .collect(Collectors.toList());
    }
}
