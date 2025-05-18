Feature: AI Assistance

  Background:
    Given the following recipes exist:
      | Recipe ID | Name            | Prep Time (min) | Ingredients                        | Dietary Tags         |
      | r1        | Veggie Stir Fry | 20              | bell pepper, broccoli, soy sauce   | vegetarian, vegan    |
      | r2        | Chicken Curry   | 45              | chicken, curry powder, coconut milk| gluten-free          |
      | r3        | Fruit Salad     | 10              | apple, banana, orange              | vegan, gluten-free   |
      | r4        | Cheese Omelette | 15              | eggs, cheese, butter               | vegetarian           |
    And the user has the following available ingredients:
      | Ingredient       |
      | bell pepper      |
      | broccoli         |
      | soy sauce        |
      | apple            |
      | banana           |
      | orange           |
    And the user’s dietary preferences are:
      | Preference       |
      | vegetarian       |

  Scenario: Suggest recipes matching dietary preferences
    When the user asks for recipe suggestions
    Then the system should recommend:
      | Recipe ID | Name            |
      | r1        | Veggie Stir Fry |


  Scenario: Suggest recipes within available time
    Given the user has 15 minutes available
    When the user asks for recipe suggestions
    Then the system should recommend:
      | Recipe ID | Name            |


  Scenario: Suggest recipes using only available ingredients
    Given the user has no time constraint
    And the user’s dietary preferences are unchanged
    When the user asks for recipe suggestions
    Then the system should recommend:
      | Recipe ID | Name            |
      | r1        | Veggie Stir Fry |


  Scenario: No matching recipes found
    Given the user’s dietary preference is "gluten-free"
    And the user has 10 minutes available
    When the user asks for recipe suggestions
    Then the system should respond with "No recipes match your criteria."

