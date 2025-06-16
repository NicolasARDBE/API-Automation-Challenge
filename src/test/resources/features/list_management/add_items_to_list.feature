Feature: Add Items to a List

  @list_management
  Scenario: Create list and add items
    Given a new list is created
    When items are added to the list
    Then the list should contain the added items
    And the list should be retrievable