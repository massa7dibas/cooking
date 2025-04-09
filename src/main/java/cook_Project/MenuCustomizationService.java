
package cook_Project;

import java.util.ArrayList;
import java.util.List;

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
        List<Ingredient> all = IngredientRepository.getAllIngredients();
        List<Substitution> subs = new ArrayList<>();
        for (Ingredient i : ingredients) {
            if (!i.isAvailable() || !i.getDietaryTags().containsAll(restrictions)) {
                Ingredient substitute = findSubstitute(i, restrictions, all);
                subs.add(new Substitution(i, substitute));
            }
        }
        return subs;
    }

    private static Ingredient findSubstitute(Ingredient original, List<String> restrictions, List<Ingredient> all) {
        for (Ingredient candidate : all) {
            if (candidate.isAvailable() && !candidate.getId().equals(original.getId())
                    && candidate.getDietaryTags().containsAll(restrictions)) {
                return candidate;
            }
        }
        return original;
    }
}
