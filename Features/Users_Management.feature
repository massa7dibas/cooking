Feature: User Management

  Scenario: Customer Sign Up
    Given the system is running
    When a customer provides valid name, email, password, and phone number
    And selects the role "Customer"
    Then the customer account should be created successfully
    And a confirmation message should be shown

  Scenario: Supplier Sign Up
    Given the system is running
    When a supplier provides valid name, email, password, phone number, and company name
    And selects the role "Supplier"
    Then the supplier account should be created successfully
    And a confirmation message should be shown

  Scenario: User Sign In with correct credentials
    Given an existing user with email "user@example.com" and password "1234"
    When the user enters "user@example.com" and "1234"
    Then the user should be signed in successfully
    And the system should identify the role and redirect accordingly

  Scenario: User Sign In with incorrect credentials
    Given an existing user with email "user@example.com" and password "1234"
    When the user enters "user@example.com" and "wrongpass"
    Then the system should show an "Incorrect email or password" message

  Scenario: Edit Profile Information
    Given a signed-in user
    When the user updates their name, email, or phone number
    Then the user's profile should be updated successfully
    And a success message should be displayed
