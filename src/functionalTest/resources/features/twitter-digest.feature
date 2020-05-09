Feature: Download tweets digest

  Scenario: Send Reply to a tweet with mention
    Given a tweet with mention
    When the tweet is processed
    Then I should reply back with acknowledgement