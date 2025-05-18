package test_Package;

import ook_project.Ingredient;
import ook_project.MenuCustomizationService;
import ook_project.Substitution;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import static ook_project.Application.addIngredient;
import static ook_project.Application.getAllIngredients;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class OrderandMenuCustomizationSteps {
    private List<String> restrictions;
    private List<Ingredient> selectedIngredients;
    private boolean validCombination;
    private List<Substitution> substitutions;

    @Given("the system has the following ingredients:")
    public void the_system_has_the_following_ingredients(DataTable dataTable) {
        List<String> tags = new ArrayList<>();
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            String id = row.get("id");
            String name = row.get("name");
            boolean available = Boolean.parseBoolean(row.get("available"));
            String tagsValue = row.get("tags");
            tags.clear();
            if (tagsValue != null && !tagsValue.isEmpty()) {
                tags.addAll(Arrays.asList(tagsValue.split(",\\s*")));
            }
            addIngredient(new Ingredient(id, name, available, tags));
        }
    }

    @Given("a customer with id {string} has dietary restrictions:")
    public void a_customer_with_id_has_dietary_restrictions(String id, DataTable dataTable) {
        restrictions = new ArrayList<>(dataTable.asList(String.class));
    }

    @When("the customer {string} selects ingredients:")
    public void the_customer_selects_ingredients(String id, DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        selectedIngredients = new ArrayList<>();
        for (List<String> row : rows) {
            String ingId = row.get(0);
            for (Ingredient i : getAllIngredients()) {
                if (i.getId().equals(ingId)) {
                    selectedIngredients.add(i);
                    break;
                }
            }
        }
    }

    @Then("the system should validate the ingredient combination successfully")
    public void the_system_should_validate_the_ingredient_combination_successfully() {
        validCombination = MenuCustomizationService.validateIngredients(selectedIngredients, restrictions);
        assertFalse(validCombination);
    }

    @Then("the system should reject the combination as invalid")
    public void the_system_should_reject_the_combination_as_invalid() {
        validCombination = MenuCustomizationService.validateIngredients(selectedIngredients, restrictions);
        assertFalse(validCombination);
    }

    @Then("suggest substitutions for unavailable ingredients")
    public void suggest_substitutions_for_unavailable_ingredients() {
        substitutions = MenuCustomizationService.suggestSubstitutions(selectedIngredients, restrictions);
        assertFalse(substitutions.isEmpty());
    }

    @Then("suggest substitutions for conflicting ingredients")
    public void suggest_substitutions_for_conflicting_ingredients() {
        substitutions = MenuCustomizationService.suggestSubstitutions(selectedIngredients, restrictions);
        assertFalse(substitutions.isEmpty());
    }

    @Given("the customer {string} has selected unavailable ingredient {string}")
    public void the_customer_has_selected_unavailable_ingredient(String id, String name) {
        selectedIngredients = new ArrayList<>();
        for (Ingredient i : getAllIngredients()) {
            if (i.getName().equals(name)) {
                selectedIngredients.add(i);
            }
        }
    }

    @When("the system suggests substitutions")
    public void the_system_suggests_substitutions() {
        substitutions = MenuCustomizationService.suggestSubstitutions(selectedIngredients, restrictions);
    }

    @Then("the system should propose {string} as a substitute for {string}")
    public void the_system_should_propose_as_a_substitute_for(String expectedSubstitute, String originalName) {
        Ingredient original = null;
        for (Ingredient i : selectedIngredients) {
            if (i.getName().equals(originalName)) {
                original = i;
                break;
            }
        }
        assertNotNull("Original ingredient not found", original);
        Substitution sub = null;
        for (Substitution s : substitutions) {
            if (s.getOriginal().getName().equals(originalName)) {
                sub = s;
                break;
            }
        }
        assertNotNull("No substitution found for " + originalName, sub);

    }

    @Given("the customer {string} has selected conflicting ingredient {string}")
    public void the_customer_has_selected_conflicting_ingredient(String id, String name) {
        selectedIngredients = new ArrayList<>();
        for (Ingredient i : getAllIngredients()) {
            if (i.getName().equals(name)) {
                selectedIngredients.add(i);
            }
        }
    }
}
