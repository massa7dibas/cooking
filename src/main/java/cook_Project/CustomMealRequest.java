package cook_Project;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomMealRequest {
    private String requestId;
    private String customerId;
    private List<Ingredient> selectedIngredients;
    private List<Substitution> substitutions;

    public CustomMealRequest(String customerId, List<Ingredient> selectedIngredients) {
        this.requestId = UUID.randomUUID().toString();
        this.customerId = customerId;
        this.selectedIngredients = selectedIngredients;
        this.substitutions = new ArrayList<>();
    }

    public String getRequestId() {
        return requestId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public List<Ingredient> getSelectedIngredients() {
        return selectedIngredients;
    }

    public List<Substitution> getSubstitutions() {
        return substitutions;
    }

    public void setSubstitutions(List<Substitution> substitutions) {
        this.substitutions = substitutions;
    }
}
