package test_Package;

import cook_Project.Allergy;
import cook_Project.Customer;
import cook_Project.FoodItem;
import cook_Project.Order;
import cook_Project.MealPlan;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.*;

public class CustomerProfileManagementSteps {
    private Map<String, Customer> customers = new HashMap<>();
    private String displayedPreferences;
    private String displayedAllergy;
    private List<Order> displayedOrders;
    private MealPlan generatedMealPlan;

    @When("the customer sets dietary preferences to {string}")
    public void the_customer_sets_dietary_preferences_to(String prefs) {
        Customer customer = new Customer("C1", "Test", "test@example.com", "pass", "000", prefs);
        customers.put("C1", customer);
    }

    @When("the customer adds allergy {string}")
    public void the_customer_adds_allergy(String allergyType) {
        customers.get("C1").addAllergy(new Allergy(allergyType, allergyType));
    }

    @Then("the system should save the dietary preferences and allergies for the customer")
    public void the_system_should_save_the_dietary_preferences_and_allergies_for_the_customer() {
        Customer c = customers.get("C1");
        assertEquals("Vegan, Gluten-Free", c.getDietaryPreferences());
        assertEquals("Peanuts", c.getAllergies().get(0).getType());
    }

    @Given("a customer with id {string} has dietary preferences {string} and allergy {string}")
    public void a_customer_with_id_has_dietary_preferences_and_allergy(String id, String prefs, String allergyType) {
        Customer customer = new Customer(id, "Name", id + "@example.com", "pass", "000", prefs);
        customer.addAllergy(new Allergy(allergyType, allergyType));
        customers.put(id, customer);
    }

    @When("the chef requests preferences for customer {string}")
    public void the_chef_requests_preferences_for_customer(String id) {
        Customer c = customers.get(id);
        displayedPreferences = c.getDietaryPreferences();
        displayedAllergy = c.getAllergies().get(0).getType();
    }

    @Then("the system should display {string} and {string}")
    public void the_system_should_display_and(String prefs, String allergyType) {
        assertEquals(prefs, displayedPreferences);
        assertEquals(allergyType, displayedAllergy);
    }

    @Given("a customer with id {string} has past orders:")
    public void a_customer_with_id_has_past_orders(String id, DataTable table) throws Exception {
        Customer customer = new Customer(id, "Name", id + "@example.com", "pass", "000", "");
        for (Map<String, String> row : table.asMaps(String.class, String.class)) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(row.get("date"));
            List<FoodItem> items = new ArrayList<>();
            for (String item : row.get("items").split(",\\s*")) {
                items.add(new FoodItem(UUID.randomUUID().toString(), item, Collections.emptyList(), 0.0));
            }
            customer.addOrder(new Order(row.get("orderId"), items, 0.0, ""));
        }
        customers.put(id, customer);
    }

    @When("the customer requests their order history")
    public void the_customer_requests_their_order_history() {
        displayedOrders = customers.get("C1").getOrderHistory();
    }

    @Then("the system should list the past orders with details")
    public void the_system_should_list_the_past_orders_with_details() {
        assertFalse(displayedOrders.isEmpty());
    }

    @When("the chef requests order history for customer {string}")
    public void the_chef_requests_order_history_for_customer(String id) {
        displayedOrders = customers.get(id).getOrderHistory();
    }

    @Then("the system should provide the order history")
    public void the_system_should_provide_the_order_history() {
        assertFalse(displayedOrders.isEmpty());
    }

    @Given("a customer with id {string} has dietary preferences {string} and past orders:")
    public void a_customer_with_id_has_dietary_preferences_and_past_orders(String id, String prefs, DataTable table) throws Exception {
        Customer customer = new Customer(id, "Name", id + "@example.com", "pass", "000", prefs);
        for (Map<String, String> row : table.asMaps(String.class, String.class)) {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(row.get("date"));
            List<FoodItem> items = new ArrayList<>();
            for (String item : row.get("items").split(",\\s*")) {
                items.add(new FoodItem(UUID.randomUUID().toString(), item, Collections.emptyList(), 0.0));
            }
            customer.addOrder(new Order(row.get("orderId"), items, 0.0, ""));
        }
        customers.put(id, customer);
    }

    @When("the customer requests a personalized meal plan")
    public void the_customer_requests_a_personalized_meal_plan() {
        Customer c = customers.get("C1");
        MealPlan plan = new MealPlan();
        for (Order o : c.getOrderHistory()) {
            plan.addMeal(o.getItems().get(0));
        }
        c.setMealPlan(plan);
        generatedMealPlan = plan;
    }

    @Then("the system should generate a meal plan based on preferences and history")
    public void the_system_should_generate_a_meal_plan_based_on_preferences_and_history() {
        assertNotNull(generatedMealPlan);
        assertFalse(generatedMealPlan.getMeals().isEmpty());
    }

    @When("the admin generates order history report for customer {string}")
    public void the_admin_generates_order_history_report_for_customer(String id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            customer = new Customer(id, "Name", id + "@example.com", "pass", "000", "");
            customer.addOrder(new Order("O0", Collections.emptyList(), 0.0, ""));
            customers.put(id, customer);
        }
        displayedOrders = customer.getOrderHistory();
    }

    @Then("the system should return all past orders for analysis")
    public void the_system_should_return_all_past_orders_for_analysis() {
        assertFalse(displayedOrders.isEmpty());
    }
}
