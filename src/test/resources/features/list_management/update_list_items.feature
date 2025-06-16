Feature: Update Item in List

  @list_management
  Scenario: Update items within a list
    Given a new list is created
    And items are added to the list
    When the items in the list are updated
    Then the list should reflect the updated items