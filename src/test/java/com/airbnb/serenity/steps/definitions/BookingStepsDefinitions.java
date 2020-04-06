package com.airbnb.serenity.steps.definitions;

import com.airbnb.serenity.entities.BookingOptions;
import com.airbnb.serenity.steps.libraries.BookingActions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static com.airbnb.serenity.page_objects.HomePage.*;


public class BookingStepsDefinitions {
    @Steps
    BookingActions dimo;

    private BookingOptions bookingOptions;

    @Given("^(?:.*) is on the popular vacation booking site$")
    public void johnIsOnTheHomePage() {
        dimo.openPage();
    }

    @Given("^s?he select english language plus currency euro$")
    public void heSelectCurrencyEuro() {
        dimo.clicksOn(LANGUAGE_AND_CURRENCY_BUTTON);
        dimo.clicksOn(LANGUAGES_LINK);
        dimo.selectLanguage(0);

        dimo.clicksOn(CURRENCY_TABLE_LINK);
        dimo.clicksOn(LIST_WITH_CURRENCIES);
    }


    @When("^he has started searching a place to stay in \"([^\"]*)\" for his \"([^\"]*)\"-days trip after \"([^\"]*)\" days$")
    public void heHasStartedSearchingAPlaceToStayInForHisDaysTripAfterDays(String place, Integer howLong, Integer daysFromNow)  {
        bookingOptions=new BookingOptions();
        bookingOptions.setPlace(place);
        bookingOptions.setStartDate(daysFromNow);
        bookingOptions.setEndDate(daysFromNow,howLong);
        dimo.startSearchingWithPlace(bookingOptions);
        dimo.applyDate(bookingOptions);
        dimo.applyDate(bookingOptions);

    }
}
