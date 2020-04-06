# new feature
# Tags: optional

Feature: Booking

  @debug
  Scenario Outline: Successful vacation booking reservation

    Given John is on the popular vacation booking site

    When he search for a place where to stay in "<place>"
    And he searching for vacation "<days stay>"-days trip after "<offset days>" days
    And he search for number of people to accompany him:
      | adults   | children   |
      | <adults> | <children> |

    And John has a requirements for his room "<filter>"
      | currency   | min price   | max price   | bathrooms   | additional  |
      | <currency> | <min price> | <max price> | <bathrooms> | <additional> |

    And he choose the first with "<stars>" stars
    Then he will be able to see all the details of his order along with all the booking amount details


    Then he should see the correct sum according entered data
    And he should see the reservation details in the widget

    Examples:
      | place | offset days | days stay | adults | children | currency | min price | max price | bathrooms | additional              | stars |
      | Bali  | 5           | 7         | 2      | 1        | EUR      | 50        | 100       | 1         | Air conditioner,Jacuzzi | 5     |
