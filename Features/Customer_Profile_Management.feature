Feature: Customer Profile Management

  Background:
    Given the system is running

  Scenario: Customer inputs dietary preferences and allergies
    When the customer sets dietary preferences to "Vegan, Gluten-Free"
    And the customer adds allergy "Peanuts"
    Then the system should save the dietary preferences and allergies for the customer

  Scenario: Chef views customer dietary preferences and allergies
    Given a customer with id "C1" has dietary preferences "Vegan, Gluten-Free" and allergy "Peanuts"
    When the chef requests preferences for customer "C1"
    Then the system should display "Vegan, Gluten-Free" and "Peanuts"

  Scenario: Customer views past meal orders
    Given a customer with id "C1" has past orders:
      | orderId | date       | items                |
      | O1      | 2025-04-01 | Salad, Soup          |
      | O2      | 2025-04-05 | Pasta, Tomato Soup   |
    When the customer requests their order history
    Then the system should list the past orders with details

  Scenario: Chef accesses customer order history
    Given a customer with id "C1" has past orders:
      | orderId | date       | items                |
      | O1      | 2025-04-01 | Salad, Soup          |
      | O2      | 2025-04-05 | Pasta, Tomato Soup   |
    When the chef requests order history for customer "C1"
    Then the system should provide the order history

  Scenario: Customer requests personalized meal plan
    Given a customer with id "C1" has dietary preferences "Vegan, Gluten-Free" and past orders:
      | orderId | date       | items                |
      | O1      | 2025-04-01 | Salad, Soup          |
      | O2      | 2025-04-05 | Pasta, Tomato Soup   |
    When the customer requests a personalized meal plan
    Then the system should generate a meal plan based on preferences and history

  Scenario: Admin retrieves customer order history for analytics
    When the admin generates order history report for customer "C1"
    Then the system should return all past orders for analysis
