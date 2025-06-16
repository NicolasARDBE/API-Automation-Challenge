Feature: Delete List

  @delete_list
  Scenario: Delete a list
    Given a new list is created
    When the list is deleted
    Then retrieving the list should confirm it no longer exists