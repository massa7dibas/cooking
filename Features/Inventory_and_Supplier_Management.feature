Feature: Inventory and Supplier Management

  Background:
    Given the following inventory items exist:
      | ingredientId | quantityOnHand | reorderThreshold | reorderQuantity |
      | I1           | 5              | 10               | 20              |
      | I2           | 50             | 30               | 50              |
      | I3           | 2              | 5                | 10              |
    And the following suppliers are registered:
      | supplierId | ingredientId | price |
      | S1         | I1           | 2.50  |
      | S2         | I1           | 2.00  |
      | S1         | I3           | 1.00  |

  Scenario: Suggest restock for low inventory items
    When the system checks inventory levels
    Then it should suggest restock for ingredient "I1" with quantity 20 and best price from supplier "S2"
    And it should suggest restock for ingredient "I3" with quantity 10 and best price from supplier "S1"

  Scenario: Generate purchase order automatically
    Given restock suggestions exist:
      | ingredientId | suggestedQuantity | supplierId | price |
      | I1           | 20                | S2         | 2.00  |
      | I3           | 10                | S1         | 1.00  |
    When the system generates a purchase order
    Then a purchase order should be created for supplier "S2" with lines:
      | ingredientId | quantity | price |
      | I1           | 20       | 2.00  |
      | I3           | 10       | 1.00  |
    And the order status should be "CREATED"

  Scenario: Send purchase order
    Given a purchase order for supplier "S2" exists with status "CREATED"
    When the system sends the purchase order
    Then the order status should be "SENT"
