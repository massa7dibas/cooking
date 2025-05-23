
package ook_project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static ook_project.Application.getAllIngredients;

public class MenuCustomizationService {

    public static boolean validateIngredients(List<Ingredient> ingredients, List<String> restrictions) {
        for (Ingredient i : ingredients) {
            if (!i.isAvailable()) {
                return false;
            }
            for (String restriction : restrictions) {
                if (!i.getDietaryTags().contains(restriction)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static List<Substitution> suggestSubstitutions(List<Ingredient> ingredients, List<String> restrictions) {
        List<Ingredient> all = getAllIngredients();
        List<Substitution> subs = new ArrayList<>();
        for (Ingredient i : ingredients) {
            if (!i.isAvailable() || !new HashSet<>(i.getDietaryTags()).containsAll(restrictions)) {
                Ingredient substitute = findSubstitute(i, restrictions, all);
                subs.add(new Substitution(i));
            }
        }
        return subs;
    }

    private static Ingredient findSubstitute(Ingredient original, List<String> restrictions, List<Ingredient> all) {
        for (Ingredient candidate : all) {
            if (candidate.isAvailable() && !candidate.getId().equals(original.getId())
                    && new HashSet<>(candidate.getDietaryTags()).containsAll(restrictions)) {
                return candidate;
            }
        }
        return original;
    }
}