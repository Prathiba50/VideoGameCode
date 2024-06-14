Feature: Validate Video Game API's


#  Get AllVideoGames
  Scenario: Verify it returns all the videos games in the DB
    Given verify "getVedioGamesAPI" receiving proper response from "GET" http method
    Then the API call got success with status 200

# Add VideoGames
  Scenario Outline: Add a new video game to the DB
    Given  Add VideoGame payload <id> and "<name>" and "<releaseDate>" and <reviewScore> and "<category>" and "<rating>"
    Then the API call got success with status 200

    Examples:
      | id  | name    | releaseDate | reviewScore | category | rating    |
      | 102 | Hmamana | 2024-10-07  | 40          | Shooter  | Universal |

#   Get Single Game
  Scenario Outline: Returns the details of a single game by ID
    Given verify user will be able to fetch single game data <id>
    Then the API call got success with status 200

    Examples:
      | id  |
      | 10 |

#    Update Video Game
  Scenario Outline: Update an existing video game in the DB by specifying a new body
    Given Update an existing video game in the DB <id> and "<name>" and "<releaseDate>" and <reviewScore> and "<category>" and "<rating>"
    Then the API call got success with status 200

    Examples:
      | id  | name    | releaseDate | reviewScore | category | rating    |
      | 102 | PacMan1 | 2024-10-07  | 64          | Puzzle  | Universal |

#    Delete Video Game
  Scenario Outline: Deletes a video game from the DB by ID
    Given Validate Video Game is deleted successfully from DB by ID <id> and "<status>"
    Then the API call got success with status 200

    Examples:
      | id  | status                      |
      | 102 | Record Deleted Successfully |



