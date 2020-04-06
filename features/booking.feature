# new feature
# Tags: optional

Feature: Booking

  Scenario Outline: Successful Booking
    Given John was on the popular booking site
    When he has started searching a place to stay in "<place>" for his "<days stay>"-days trip after "<offset days>" days
    And added people to accompany him:
      | adults   | children   |
      | <adults> | <children> |
    And he introduced his requirements:
      | currency   | min price   | max price   | bathrooms   | additionals  |
      | <currency> | <min price> | <max price> | <bathrooms> | <additional> |
    And he choose the first with "<stars>" stars
    Then he will be able to see all the details of his order along with all the booking amount details




    Examples:
      | place | offset days | days stay | adults | children | currency | min price | max price | bathrooms | additional              | stars |
      | Bali  | 5           | 7         | 2      | 1        | EUR      | 50        | 100       | 1         | Air conditioner,Jacuzzi | 5     |
