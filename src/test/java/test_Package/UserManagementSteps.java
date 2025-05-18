package test_Package;

import ook_project.Application;
import io.cucumber.java.en.*;

import static org.junit.Assert.*;

public class UserManagementSteps {

    @Given("the system is running")
    public void the_system_is_running() {

    }

    @When("a customer provides valid name, email, password, and phone number")
    public void a_customer_provides_valid_name_email_password_and_phone_number() {

    }

    @When("selects the role {string}")
    public void selects_the_role(String role) {
        Application.registerUser("1", "user", "normlauser@example.com", "password123", "1234567890", role);
    }

    @Then("the customer account should be created successfully")
    public void the_customer_account_should_be_created_successfully() {
       
        assertEquals("Registration Successful", Application.getConfirmationMessage());
    }

    @Then("a confirmation message should be shown")
    public void a_confirmation_message_should_be_shown() {
        assertNotNull(Application.getConfirmationMessage());
    }

    @When("a supplier provides valid name, email, password, phone number, and company name")
    public void a_supplier_provides_valid_name_email_password_phone_number_and_company_name() {
        Application.registerUser("2", "normlauser", "supplier@example.com", "password123", "0987654321", "supplier");
    }

    @Then("the supplier account should be created successfully")
    public void the_supplier_account_should_be_created_successfully() {
        assertEquals("Registration Successful", Application.getConfirmationMessage());
    }

    @Given("an existing user with email {string} and password {string}")
    public void an_existing_user_with_email_and_password(String email, String password) {
        Application.registerUser("3", "normlauser", email, password, "1234567890", "customer");
    }

    @When("the user enters {string} and {string}")
    public void the_user_enters_and(String email, String password) {
        Application.loginUser(email, password);
    }

    @Then("the user should be signed in successfully")
    public void the_user_should_be_signed_in_successfully() {
        assertTrue(Application.isUserLoggedIn());
    }

    @Then("the system should identify the role and redirect accordingly")
    public void the_system_should_identify_the_role_and_redirect_accordingly() {
        assertEquals("Customer", Application.mainUser.getRole());
    }

    @Then("the system should show an {string} message")
    public void the_system_should_show_an_message(String message) {
        assertEquals(message, Application.getLoginErrorMessage());
    }

    @Given("a signed-in user")
    public void a_signed_in_user() {
        Application.loginUser("normlauser@example.com", "password123");
    }

    @When("the user updates their name, email, or phone number")
    public void the_user_updates_their_name_email_or_phone_number() {
        Application.updateUserProfile("normlauser", "normlauser@example.com", "0987654321");
    }

    @Then("the user's profile should be updated successfully")
    public void the_user_s_profile_should_be_updated_successfully() {
        assertEquals("Profile Updated Successfully", Application.getProfileUpdateMessage());
    }

    @Then("a success message should be displayed")
    public void a_success_message_should_be_displayed() {
        assertNotNull(Application.getProfileUpdateMessage());
    }
}
