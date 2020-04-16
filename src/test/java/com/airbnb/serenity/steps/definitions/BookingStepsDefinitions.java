package com.airbnb.serenity.steps.definitions;

import com.airbnb.serenity.entities.BookingOptions;
import com.airbnb.serenity.page_objects.ReservePage;
import com.airbnb.serenity.steps.libraries.BookingActions;

import com.airbnb.serenity.steps.libraries.ReserveActions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.assertj.core.api.SoftAssertions;

import static com.airbnb.serenity.page_objects.HomePage.*;

import java.util.List;
import java.util.Map;

import static com.airbnb.serenity.page_objects.ReservePage.*;
import static com.airbnb.serenity.utils.SetBookingOptionsByList.prepareBookingOptionsObject;


public class BookingStepsDefinitions {

    public static String currency;
    private BookingOptions bookingOptions;
    @Steps
    BookingActions dimo;

    @Steps
    private ReserveActions anni;

    @Steps
    private ReservePage reservePage;


    @Given("^(?:.*) is on the popular vacation booking site$")
    public void johnIsOnTheHomePage() {
        dimo.openPage();
    }

    @Given("^s?he select currency \"([^\"]*)\"$")
    public void heSelectCurrencyEuro(String data) {
        currency = data;
        dimo.clicksOn(LANGUAGE_AND_CURRENCY_BUTTON);
        dimo.clicksOn(CURRENCY_TABLE_LINK);
        dimo.setCurrency(currency);
    }

    @When("^John search for a place where to stay with the following options:$")
    public void johnSearchForAPlaceWhereToStayWithTheFollowingOptions(List<BookingOptions> data) {
        bookingOptions =data.get(0);
        bookingOptions.setStartDate();
        bookingOptions.setEndDate();
        dimo.startSearchingWithPlace(bookingOptions.getPlace());
        dimo.applyDate(bookingOptions.getStartDate());
        dimo.applyDate(bookingOptions.getEndDate());
        dimo.selectAdditionalGuests(bookingOptions);


    }


    @When("^(?:.*) has a requirements for his room:$")
    public void userHasARequirementsForHisRoom(List<Map<String, String>> requirements) {
        prepareBookingOptionsObject(bookingOptions, requirements);
        dimo.setPriceRange(bookingOptions.getMinPrice(), bookingOptions.getMaxPrice());
        dimo.selectRoomsAndBeds(bookingOptions.getBathRooms());
        dimo.setAdditionalAmenities(bookingOptions.isAirConditioner(), bookingOptions.isJacuzzi());


    }

    @When("^s?he choose the first with \"([^\"]*)\" stars$")
    public void heChooseTheFirstWithStars(Float stars) throws InterruptedException {
        dimo.selectTheFirstStayWithAtLeastGivenStar(stars);

    }


    @Then("^he should see the reservation details in the widget$")
    public void heShouldSeeTheReservationDetailsInTheWidget() {
        anni.checkDates(bookingOptions);
        anni.checkGuests(bookingOptions);
    }


    @Then("^(?:.*) should see the correct sum according entered data$")
    public void userShouldSeeTheCorrectSumAccordingEnteredData() {
        anni.checkPrice(bookingOptions);
    }


}