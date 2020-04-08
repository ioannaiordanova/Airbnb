package com.airbnb.serenity.steps.libraries;

import com.airbnb.serenity.entities.BookingOptions;
import com.airbnb.serenity.page_objects.HomePage;
import com.airbnb.serenity.steps.definitions.BookingStepsDefinitions;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import org.jruby.RubyProcess;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

import static com.airbnb.serenity.page_objects.HomePage.*;

public class BookingActions
        extends BaseActions {
    private HomePage homePage;


    public void openPage() {
        homePage.open();
    }

    public void startSearchingWithPlace(String place) {
        //options.getPlace();
//        System.out.println(HomePage.WHERE_SEARCH);
        fillsFieldWithData(HomePage.WHERE_SEARCH, place);
        this.currentPage.find(HomePage.WHERE_SEARCH).sendKeys(Keys.ENTER);
    }

    public boolean isTheNextYearNeeded(LocalDate date, By locatorMonthAndYear) {
        String monthAndYearString = readsTextFrom(locatorMonthAndYear);
        if (monthAndYearString.contains(String.valueOf(date.getYear()))) {
            return false;
        }
        return true;
    }

    public boolean isTheNextMonthNeeded(LocalDate date, By locatorMonthAndYear) {
        String monthAndYearString = readsTextFrom(locatorMonthAndYear);
        if (monthAndYearString.contains(formatMonth(date.getMonth()))) {
            return false;
        }
        return true;

    }

    @Step
    public void applyDate(LocalDate date) {
        while (isTheNextYearNeeded(date, HomePage.CALENDAR_MONTH_NAME))
            clicksOn(HomePage.NEXT_MONTH_BUTTON);

        while (isTheNextMonthNeeded(date, HomePage.CALENDAR_MONTH_NAME))
            clicksOn(HomePage.NEXT_MONTH_BUTTON);

        for (WebElementFacade Day : homePage.tdDatesEnabled) {
            //System.out.println(String.valueOf(Day.getText()));
            if (date.getDayOfMonth() == Integer.parseInt(Day.getText())) {
                clicksOn(Day);
                return;
            }
        }
    }

    public void setCurrency(String currency) {
        for (int index = 0; index < homePage.listWithCurrencies.size(); index++) {
            if (homePage.listWithCurrencies.get(index).getText().contains(currency)) {
                clicksOn(homePage.listWithCurrencies.get(index));
                return;
            }
        }

    }

    public void increaseGuests(By guestLabel, Integer count) {
        for (WebElementFacade guestType : homePage.listTypesOfGuests) {
            if (guestType.thenFindAll(guestLabel).size() == 1) {
                for (int i = 0; i < count; i++) {
                    WebElementFacade increaseGuestsButton = guestType.findBy(HomePage.INCREASE_GUESTS_BTN);
                    clicksOn(increaseGuestsButton);
                }
            }
        }
    }

    public void selectAdditionalGuests(BookingOptions options) {
        clicksOn(HomePage.GUEST_PICKER_BTN);
        increaseGuests(HomePage.ADULTS_LABEL, options.getAdults());
        increaseGuests(HomePage.CHILDRENS_LABEL, options.getKids());
        clicksOn(HomePage.SAVE_GUESTS_BTN);
        clicksOn(HomePage.SUBMIT_BUTTON);
    }

    @Step
    public void setPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        clicksOn(PRICE_RANGE_MENU_BUTTON);
        setElementInVisibleScreen(homePage.priceFilterMin);
        fillsFieldWithData(homePage.priceFilterMin, minPrice.toString());
        fillsFieldWithData(homePage.priceFilterMax, maxPrice.toString());
        clicksOn(homePage.filterPanelSaveButton);

    }


    public void selectRoomsAndBeds(int bathrooms) {
        boolean singleMenuItem = false;
        if (currentPage.findAll(ROOMS_AND_BEDS_MENU_ITEM).size() == 0) {
            clicksOn(MORE_FILTERS_MENU_ITEM);
        } else {
            singleMenuItem = true;
            clicksOn(ROOMS_AND_BEDS_MENU_ITEM);
        }
        clicksOn(homePage.plusBathRoomButton);
        if (singleMenuItem) clicksOn(homePage.filterPanelSaveButton);
    }


    public void setAdditionalAmenities(boolean isAirConditioner, boolean isJacuzzi) {
        if (isAirConditioner) {
            clicksOn(homePage.airConditionerLabel);
        }

        if (isJacuzzi) {
            clicksOn(homePage.jaccuziLabel);
        }
        clicksOn(homePage.showMoreThanThreeThousandPlacesButton);
    }

    public By returnStarsByStayNumberInPage(int number) {
        By StarsBy = By.cssSelector("." + CLASS_OF_STAY + ":nth-of-type(" + String.valueOf(number) + ") " + STARS_CSS);
        return StarsBy;
    }

    public By returnLinkOFStayByStayNumberInPage(int number) {
        By linkBy = By.cssSelector("." + CLASS_OF_STAY + ":nth-of-type(" + String.valueOf(number) + ") a");
        return linkBy;
    }

    public void selectTheFirstStayWithAtLeastGivenStar(Float stars) {
        boolean nextPageExists = true;
        int elementNumber = 0;
        homePage.waitFor(homePage.listOfStays.get(1));
        WebElementFacade linkTo = getWebElementFacadeBy(returnLinkOFStayByStayNumberInPage(1));
        goToURL(linkTo.getAttribute("href"));

/*      while (nextPageExists) {
          elementNumber = 0;
          for (WebElementFacade stay : homePage.listOfStays) {
              elementNumber++;

              WebElementFacade star = getWebElementFacadeBy(returnStarsByStayNumberInPage(elementNumber));

              if (star.isPresent()) {
                  float starValue = Float.parseFloat(star.getText());
                  if (starValue >= stars) {
                     WebElementFacade link = getWebElementFacadeBy(returnLinkOFStayByStayNumberInPage(elementNumber));
                      System.out.println(link.getAttribute("href"));
                      goToURL(link.getAttribute("href"));
                      return;
                  }
              }
          }

          if (getWebElementFacadeCountBy(NEXT_PAGE_ARROW) > 0) {
              clicksOn(NEXT_PAGE_ARROW);

          } else nextPageExists = false;
      }*/


    }
}
