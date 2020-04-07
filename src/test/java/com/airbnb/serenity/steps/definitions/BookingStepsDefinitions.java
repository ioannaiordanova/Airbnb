package com.airbnb.serenity.steps.definitions;

import com.airbnb.serenity.entities.BookingOptions;
import com.airbnb.serenity.steps.libraries.BookingActions;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import static com.airbnb.serenity.page_objects.HomePage.*;

import java.util.List;
import java.util.Map;

import static com.airbnb.serenity.utils.SetBookingOptionsByList.prepareBookingOptionsObject;


public class BookingStepsDefinitions {
    @Steps
    BookingActions dimo;

    private BookingOptions bookingOptions;

    @Given("^(?:.*) is on the popular vacation booking site$")
    public void johnIsOnTheHomePage() {
        dimo.openPage();
    }

    @Given("^s?he select english language plus currency \"([^\"]*)\"$")
    public void heSelectCurrencyEuro(String currency) {
        dimo.clicksOn(LANGUAGE_AND_CURRENCY_BUTTON);
        dimo.clicksOn(CURRENCY_TABLE_LINK);
        dimo.setCurrency(currency);
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

    @And("^John has a requirements for his room:$")
    public void johnHasARequirementsForHisRoom(List<Map<String, String>> requirements) {
        prepareBookingOptionsObject(bookingOptions, requirements);
        dimo.setPriceRange(bookingOptions.getMinPrice(), bookingOptions.getMaxPrice());
        dimo.selectRoomsAndBeds(bookingOptions.getBathRooms());
        dimo.setAdditionalAmenities(bookingOptions.isAirConditioner(),bookingOptions.isJacuzzi());


    }

    @And("^he choose the first with \"([^\"]*)\" stars$")
    public void heChooseTheFirstWithStars(Float stars)  {
        dimo.selectTheFirstStayWithAtLeastGivenStar(stars);
    }
}