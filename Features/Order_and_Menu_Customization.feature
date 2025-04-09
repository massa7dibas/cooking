Feature: Order and Menu Customization

  Background:
    Given the system has the following ingredients:
      | id  | name       | available | tags                       |
      | I1  | Tomato     | true      | Vegan, Gluten-Free         |
      | I2  | Cheese     | true      | Vegetarian                 |
      | I3  | Gluten     | true      |                             |
      | I4  | Peanut     | false     | Vegan, Gluten-Free, Nut-Free |
      | I5  | AlmondMilk | true      | Vegan, Dairy-Free, Nut-Free |
    And a customer with id "C1" has dietary restrictions:
      | restriction     |
      | Vegan           |
      | Gluten-Free     |

  Scenario: Customer creates a valid custom meal request
    When the customer "C1" selects ingredients:
      | id  | name    |
      | I1  | Tomato  |
      | I5  | AlmondMilk |
    Then the system should validate the ingredient combination successfully

  Scenario: Customer creates a custom meal request with unavailable ingredient
    When the customer "C1" selects ingredients:
      | id  | name    |
      | I1  | Tomato  |
      | I4  | Peanut  |
    Then the system should reject the combination as invalid
    And suggest substitutions for unavailable ingredients

  Scenario: Customer creates a custom meal request conflicting with dietary restrictions
    When the customer "C1" selects ingredients:
      | id  | name   |
      | I1  | Tomato |
      | I2  | Cheese |
    Then the system should reject the combination as invalid
    And suggest substitutions for conflicting ingredients

  Scenario: System suggests substitutions for unavailable ingredients
    Given the customer "C1" has selected unavailable ingredient "Peanut"
    When the system suggests substitutions
    Then the system should propose "AlmondMilk" as a substitute for "Peanut"

  Scenario: System suggests substitutions for ingredients conflicting with restrictions
    Given the customer "C1" has selected conflicting ingredient "Cheese"
    When the system suggests substitutions
    Then the system should propose "AlmondMilk" as a substitute for "Cheese"
