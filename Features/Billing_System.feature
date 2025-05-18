Feature: Billing System

  Scenario: Generate invoice after order completion
    Given a customer named "Ahmad" placed an order with the following items:
      | Item           | Quantity | Unit Price |
      | Burger         | 2        | 25         |
      | Soft Drink     | 1        | 5          |
    When the order is completed
    Then an invoice should be generated with:
      | Item       | Quantity | Unit Price | Total |
      | Burger     | 2        | 25         | 50    |
      | Soft Drink | 1        | 5          | 5     |
    And the invoice total should be 55
    And the invoice status should be "Issued"

  Scenario: Send invoice to customer
    Given an invoice has been generated for customer "Ahmad"
    When the system sends the invoice via email
    Then the customer should receive the invoice at "ahmad@example.com"

  Scenario: Generate daily financial report
    Given the following orders were completed today:
      | Customer | Total |
      | Ahmad    | 55    |
      | Sara     | 80    |
    When the system generates the daily report
    Then the report should show:
      | Total Revenue | Number of Orders | Average Order Value |
      | 135           | 2                | 67.5                |

  Scenario: Analyze top-performing product
    Given the following product sales data:
      | Product | Quantity Sold |
      | Burger  | 120           |
      | Pizza   | 85            |
      | Drink   | 150           |
    When the system analyzes sales performance
    Then the top-performing product should be "Drink"
