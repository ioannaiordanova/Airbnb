# new feature
# Tags: optional

Feature: Booking

  @debug
  Scenario: Successful vacation booking reservation

    Given John is on the popular vacation booking site
    And he select currency "EUR"
    When John search for a place where to stay with the following options:
      | place | daysFromNow | days | adults | kids |
      | Bali  | 5           | 7    | 2      | 1    |
    And John has a requirements for his room:
      | min price | max price | bathrooms | additional              |
      | 50        | 100       | 1         | Air conditioner,Jacuzzi |
    And he choose the first with "5" stars
    Then he should see the reservation details in the widget
    And John should see the correct sum according entered data

