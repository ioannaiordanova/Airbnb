package com.airbnb.serenity.steps.libraries;

import com.airbnb.serenity.entities.BookingOptions;
import com.airbnb.serenity.page_objects.ReservePage;
import net.serenitybdd.core.pages.WebElementFacade;
import org.assertj.core.api.SoftAssertions;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.airbnb.serenity.page_objects.ReservePage.GESTS_LABEL;

public class ReserveActions
        extends BaseActions {

    private ReservePage reservePage;

    public String calculateDaysAndNights(BookingOptions bookingOptions) {
        return String.format("%.0f", bookingOptions.getPrice() * bookingOptions.getNights());
    }

    public void checkDates(BookingOptions options){
        List<WebElementFacade> tripDates =  getAllWebElementFacadeABy(ReservePage.START_OF_TRIP_DATE);
        String startOfTripDate=tripDates.get(0).getText();
        System.out.println("The start Of trip date on the Reservation Page is: "+tripDates.get(0).getText());

        String endOfTripDateDisplayed =  tripDates.get(1).getText();
        System.out.println("The End Of trip date on the Reservation Page is: "+tripDates.get(1).getText());

        LocalDate startDate = convertStringToDate("M/d/yyyy",startOfTripDate);
        LocalDate endDate = convertStringToDate("M/d/yyyy",endOfTripDateDisplayed);
        //"yyyy-MM-dd"

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(options.getStartDate())
                .as("Start date of the user input has to be equal with displayed:")
                .isEqualTo(startDate);
        softly.assertThat(options.getEndDate())
                .as("The End Date has to be the same as the user input")
                .isEqualTo(endDate);
        softly.assertAll();

    }

    public void checkGuests(BookingOptions options){
       currentPage.waitForRenderedElements(GESTS_LABEL);
        System.out.println("Overall guests: "+getWebElementFacadeBy(GESTS_LABEL).getText());
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(getWebElementFacadeBy(GESTS_LABEL).getText());
        while(m.find()) {
            System.out.println(m.group());
        }

        clicksOn(GESTS_LABEL);
        String adultsDisplayed = getWebElementFacadeBy(ReservePage.NUMBER_ADULTS_DISPAYED).getText();
        String kidsDisplayed = getWebElementFacadeBy(ReservePage.NUMBER_CHILDREN_DISPLAYED).getText();
        System.out.println("Adults "+adultsDisplayed+" Kids: "+kidsDisplayed);
        clicksOn(GESTS_LABEL);

    }

    public void open(){
        reservePage.open();
    }

/*    @Step
    public void opensReservePage() {
        reservePage.open();
    }*/
}
