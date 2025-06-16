Feature: Add Movie Review

  @add_review
  Scenario: Add a review to a movie
    Given a movie is available to rate
    When a rating is added to the movie
    Then the movie should appear in the rated movies list
    And the new rating should be correctly recorded