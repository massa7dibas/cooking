package test_Package;

import ook_project.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

import static ook_project.Application.addRecipe;
import static ook_project.Application.clearRecipes;

public class AIAssistanceSteps {
    private List<String> availableIngredients;
    private List<String> dietaryPreferences;
    private Integer maxPrepTime;
    private List<Recipe> suggestions;

    @Given("the following recipes exist:")
    public void the_following_recipes_exist(DataTable table) {
        clearRecipes();
        for (Map<String, String> row : table.asMaps(String.class, String.class)) {
            String id = row.get("Recipe ID");
            String name = row.get("Name");
            int time = Integer.parseInt(row.get("Prep Time (min)"));
            List<String> ingredients = List.of(row.get("Ingredients").split(",\\s*"));
            List<String> tags = List.of(row.get("Dietary Tags").split(",\\s*"));
            addRecipe(new Recipe(id, name, time, ingredients, tags));
        }
    }

    @Given("the user has the following available ingredients:")
    public void the_user_has_the_following_available_ingredients(DataTable table) {
        var all = table.asList(String.class);
        availableIngredients = all.subList(1, all.size());
    }


    @Given("the user’s dietary preferences are:")
    public void the_user_s_dietary_preferences_are(DataTable table) {
        var all = table.asList(String.class);
        dietaryPreferences = all.subList(1, all.size());
    }


    @Given("the user has {int} minutes available")
    public void the_user_has_minutes_available(Integer minutes) {

        maxPrepTime = minutes;
    }

    @Given("the user has no time constraint")
    public void the_user_has_no_time_constraint() {
        maxPrepTime = null;
    }

    @Given("the user’s dietary preferences are unchanged")
    public void the_user_s_dietary_preferences_are_unchanged() {

    }

    @Given("the user’s dietary preference is {string}")
    public void the_user_s_dietary_preference_is(String pref) {
        dietaryPreferences = List.of(pref);
    }

    @When("the user asks for recipe suggestions")
    public void the_user_asks_for_recipe_suggestions() {

        RecipeRequest request = new RecipeRequest(availableIngredients, dietaryPreferences, maxPrepTime);

        suggestions = AISuggestionService.suggestRecipes(request);


    }

    @Then("the system should recommend:")
    public void the_system_should_recommend(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);

        Assert.assertEquals(rows.size(), suggestions.size());


        for (int i = 0; i < rows.size(); i++) {
            Map<String, String> row = rows.get(i);
            Recipe r = suggestions.get(i);
            Assert.assertEquals(row.get("Recipe ID"), r.getRecipeId());
            Assert.assertEquals(row.get("Name"), r.getName());
        }
    }

    @Then("the system should respond with {string}")
    public void the_system_should_respond_with(String message) {

        Assert.assertFalse(suggestions.isEmpty());

    }
}
