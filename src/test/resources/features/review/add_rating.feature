Feature: Add Movie Rating

  @add_rating
  Scenario: Add a rating to a movie
    Given a movie is available to rate
    When a rating is added to the movie
    Then the movie should appear in the rated movies list