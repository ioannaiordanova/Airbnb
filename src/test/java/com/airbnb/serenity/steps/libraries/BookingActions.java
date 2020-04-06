package com.airbnb.serenity.steps.libraries;

import com.airbnb.serenity.page_objects.HomePage;
import com.airbnb.serenity.steps.definitions.BookingStepsDefinitions;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public class BookingActions {
    private HomePage homePage;


    public void openPage(){
        homePage.open();
    }


}
