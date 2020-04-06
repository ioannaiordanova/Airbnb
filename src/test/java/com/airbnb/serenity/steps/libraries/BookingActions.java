package com.airbnb.serenity.steps.libraries;

import com.airbnb.serenity.entities.BookingOptions;
import com.airbnb.serenity.page_objects.HomePage;
import com.airbnb.serenity.steps.definitions.BookingStepsDefinitions;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import static com.airbnb.serenity.page_objects.HomePage.CURRENCY_TYPE;
import static com.airbnb.serenity.page_objects.HomePage.LANGUAGE_US_LINK;

public class BookingActions
        extends BaseActions {
    private HomePage homePage;


    public void openPage() {
        homePage.open();
    }

    public void startSearchingWithPlace(String place) {
        //options.getPlace();
        System.out.println(HomePage.WHERE_SEARCH);
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

    public void applyDate(LocalDate date) {
        while (isTheNextYearNeeded(date, HomePage.CALENDAR_MONTH_NAME))
            clicksOn(HomePage.NEXT_MONTH_BUTTON);

        while (isTheNextMonthNeeded(date, HomePage.CALENDAR_MONTH_NAME))
            clicksOn(HomePage.NEXT_MONTH_BUTTON);

        for (WebElementFacade Day : homePage.tdDatesEnabled) {
            System.out.println(String.valueOf(Day.getText()));
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
                for (Integer i = 0; i < count; i++) {
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
}
