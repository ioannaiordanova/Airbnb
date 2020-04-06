package com.airbnb.serenity.steps.definitions;

import com.airbnb.serenity.entities.BookingOptions;
import com.airbnb.serenity.steps.libraries.BookingActions;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;

import java.math.BigDecimal;
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

    @And("^John has a requirements for his room:$")
    public void johnHasARequirementsForHisRoom(List<Map<String,String>> requirements) {
     //   prepareBookingOptionsObject(bookingOptions,requirements);
        for(Map<String, String> requirementtMap: requirements){
            for(Map.Entry<String, String> requirement: requirementtMap.entrySet()){
                System.out.println(requirement.getKey()+"  "+requirement.getValue());
                if (requirement.getKey().contains("min value")){
                    BigDecimal dc = new BigDecimal(requirement.getValue());
                    bookingOptions.setMinPrice(dc);
                }

                if (requirement.getKey().contains("max value")){
                    BigDecimal dc = new BigDecimal(requirement.getValue());
                    bookingOptions.setMaxPrice(dc);
                }

                if (requirement.getKey().contains("bathrooms")){
                    bookingOptions.setBathRooms(Integer.parseInt(requirement.getValue()));
                }

                if (requirement.getKey().contains("additional")){
                    if (requirement.getKey().contains("Air conditioner")){
                        bookingOptions.setAirConditioner(true);
                    }
                    else bookingOptions.setAirConditioner(false);

                    if (requirement.getKey().contains("Jacuzzi")){
                        bookingOptions.setJacuzzi(true);
                    }
                    else bookingOptions.setJacuzzi(false);

                }

            }
        }


    }
}