package com.airbnb.serenity.steps.definitions;

import com.airbnb.serenity.entities.BookingOptions;
import com.airbnb.serenity.steps.libraries.BookingActions;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class BookingStepsDefinitions {
    @Steps
    BookingActions dimo;

    private BookingOptions bookingOptions;

    @Given("^John is on the popular vacation booking site$")
    public void johnIsOnTheHomePage() {
        dimo.openPage();
    }


    @When("^he search for a place where to stay in \"([^\"]*)\"$")
    public void heSearchForAPlaceWhereToStayIn(String place) {
        bookingOptions = new BookingOptions();
        bookingOptions.setPlace(place);

        dimo.startSearchingWithPlace(bookingOptions.getPlace());
    }

    @When("^he searching for vacation \"([^\"]*)\"-days trip after \"([^\"]*)\" days$")
    public void heSearchingForVacationDaysTripAfterDays(Integer howLong, Integer daysFromNow) {
        bookingOptions.setStartDate(daysFromNow);
        bookingOptions.setEndDate(daysFromNow, howLong);
        dimo.applyDate(bookingOptions.getStartDate());
        dimo.applyDate(bookingOptions.getEndDate());
    }

    @And("^he search for number of people to accompany him:$")
    public void heSearchForNumberOfPeopleToAccompanyHim(List<BookingOptions> guests) { //List<Map<String,Integer>>
        bookingOptions.setKids(guests.get(0).getKids());
        bookingOptions.setAdults(guests.get(0).getAdults());

        dimo.selectAdditionalGuests(bookingOptions);

    }
}