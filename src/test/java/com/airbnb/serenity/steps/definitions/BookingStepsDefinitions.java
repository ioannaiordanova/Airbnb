package com.airbnb.serenity.steps.definitions;

import com.airbnb.serenity.entities.BookingOptions;
import com.airbnb.serenity.steps.libraries.BookingActions;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;


public class BookingStepsDefinitions {
    @Steps
    BookingActions dimo;

    private BookingOptions bookingOptions;

    @Given("^John was on the popular booking site$")
    public void johnIsOnTheHomePage() {
        dimo.openPage();
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
