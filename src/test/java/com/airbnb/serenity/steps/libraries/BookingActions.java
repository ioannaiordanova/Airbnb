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

import static com.airbnb.serenity.page_objects.HomePage.LANGUAGE_US_LINK;

public class BookingActions
    extends BaseActions {
    private HomePage homePage;


    public void openPage(){
        homePage.open();
    }

    public void startSearchingWithPlace(BookingOptions options){
        //options.getPlace();
        System.out.println(HomePage.WHERE_SEARCH);
        fillsFieldWithData(HomePage.WHERE_SEARCH,options.getPlace());
        this.currentPage.find(HomePage.WHERE_SEARCH).sendKeys(Keys.ENTER);
    }

    public boolean isTheNextYearNeeded(LocalDate date, By locatorMonthAndYear){
        String monthAndYearString= readsTextFrom(locatorMonthAndYear);
        if  ( monthAndYearString.contains(String.valueOf(date.getYear()))){
            return false;
        }
        return true;
    }

    public boolean isTheNextMonthNeeded(LocalDate date, By locatorMonthAndYear){
        String monthAndYearString= readsTextFrom(locatorMonthAndYear);
        if  ( monthAndYearString.contains(formatMonth(date.getMonth()))){
            return false;
        }
        return true;

    }

    public void applyDate(BookingOptions options){
       while (isTheNextYearNeeded(options.getStartDate(),HomePage.CALENDAR_MONTH_NAME) )
           clicksOn(HomePage.NEXT_MONTH_BUTTON);

       while (isTheNextMonthNeeded(options.getStartDate(),HomePage.CALENDAR_MONTH_NAME))
           clicksOn(HomePage.NEXT_MONTH_BUTTON);

        for (WebElementFacade Day:homePage.tdDatesEnabled){
            System.out.println(Day.getText());
            if (options.getDayStartTrip() == Integer.parseInt(Day.getText())){
                clicksOn(Day);
            }
        }
    }

    public void selectLanguage(int productNumber) {
        clicksOn((WebElementFacade) homePage.listWithLanguages.get(productNumber).find(LANGUAGE_US_LINK));
    }



}
