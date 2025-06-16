Feature: Delete Items from List

  @list_management
  Scenario: Delete items within a list
    Given a new list is created
    And items are added to the list
    When the items are deleted from the list
    Then the list should reflect the item deletions