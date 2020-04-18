package com.airbnb.serenity.steps.definitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import net.thucydides.core.annotations.Steps;
import com.airbnb.serenity.steps.libraries.HomeActions;


public class HomeStepsDefinitions {
    @Steps
    HomeActions me;


    @Given("^(?:.*) is on home page of the popular booking site$")
    public void johnIsOnTheHomePage() {
        me.openPage();
    }

    @Before("@eur_currency")
    public void initEURCurrency(){
        me.openPage();
        me.setCurrencyStep();
    }

}
