Feature: Download tweets digest

  Scenario: Send Reply to a tagged tweet
    Given a tagged tweet
    When the tweet is processed
    Then I should reply back with acknowledgement