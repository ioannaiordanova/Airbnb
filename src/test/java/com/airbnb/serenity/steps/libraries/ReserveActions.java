package com.airbnb.serenity.steps.libraries;

import com.airbnb.serenity.entities.BookingOptions;
import com.airbnb.serenity.page_objects.HomePage;
import com.airbnb.serenity.page_objects.ReservePage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import static com.airbnb.serenity.page_objects.ReservePage.GUESTS_LABEL;
import static com.airbnb.serenity.page_objects.ReservePage.GUESTS_LABEL_2;

public class ReserveActions
        extends BaseActions {
    private ReservePage resevrationPage;
    public void openPage(){
        resevrationPage.open();
    }


    @Step("Assert the dates")
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

    @Step("Assert the number of guests")
    public void checkGuests(BookingOptions options){

        /*
        JavascriptExecutor js = (JavascriptExecutor) resevrationPage.getDriver();
        js.executeScript("return document.readyState").equals("complete");

        js.executeScript("arguments[0].scrollIntoView(true);", resevrationPage.find(By.cssSelector("[data-testid=book-it-default]")));
        //wait.until(ExpectedConditions.presenceOfElementLocated(resevrationPage.find(By.cssSelector("[data-testid=book-it-default]"))));
        js.executeScript("return arguments[0].value", resevrationPage.find(By.cssSelector("input[type=hidden][name=number_of_guests]")));
        js.executeScript("return arguments[0].value", resevrationPage.find(By.cssSelector("input[type=hidden][name=number_of_adults]")));
        js.executeScript("return arguments[0].value", resevrationPage.find(By.cssSelector("input[type=hidden][name=number_of_adults]")));
      */
        WebElementFacade guestLabel = null;
        guestLabel = getWebElementFacadeBy(resevrationPage.GUESTS_LABEL);

        System.out.println("Overall guests: "+guestLabel.getText());

        int overallGuests = 0;
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(readsTextFrom(guestLabel));
        if (m.find()) {
            System.out.println(m.group());
            overallGuests =Integer.parseInt(m.group());
        }


        clicksOn(GUESTS_LABEL);
        String adultsDisplayed = readsTextFrom(getWebElementFacadeBy(ReservePage.NUMBER_ADULTS_DISPLAYED));
        String kidsDisplayed =readsTextFrom(getWebElementFacadeBy(ReservePage.NUMBER_CHILDREN_DISPLAYED));
        System.out.println("Adults "+adultsDisplayed+" Kids: "+kidsDisplayed);
         int overallGuestsExpetcted =options.getAdults()+options.getKids();

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat( overallGuests)
                .as("Number of all guests has to be as expected:")
                .isEqualTo(overallGuestsExpetcted);
        softly.assertThat(Integer.parseInt(adultsDisplayed))
                .as("Number of all adults has to be as expected:")
                .isEqualTo(options.getAdults());
        softly.assertAll();

        if (guestLabel.isClickable()) {
            clicksOn(GUESTS_LABEL);
        }


    }

    @Step
    public void checkPrice() {

        currentPage.waitForRenderedElementsToBePresent(By.xpath("//div[@class='_80f7zz']//span[@class='_pgfqnw'] | //div[@class='_n4om66']//span[@class='_doc79r']"));
        String priceDisplayed = readsTextFrom(By.xpath("//div[@class='_80f7zz']//span[@class='_pgfqnw'] | //div[@class='_n4om66']//span[@class='_doc79r']"));

        NumberFormat numberFormat = new DecimalFormat("¤#.00", new DecimalFormatSymbols(Locale.GERMANY));

        // NumberFormat number = NumberFormat.getCurrencyInstance(Locale.GERMANY);
        System.out.println("Currency Symbol " + numberFormat.getCurrency().getSymbol());
        System.out.println("Currency Display Name " + numberFormat.getCurrency().getDisplayName());
        Number num = 0;
        try {
            num = numberFormat.parse(priceDisplayed);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't convert " + priceDisplayed + " to Double!");
        }
        System.out.println(num);
    }


}
