Feature: Update List Information

  @list_management
  Scenario: Create list and update its information
    Given a new list is created
    When the list information is updated
    Then the list should reflect the updated information