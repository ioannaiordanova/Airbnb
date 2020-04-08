package com.airbnb.serenity.page_objects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;

public class MenuWithFilters
extends PageObject {

    //price filter
    public static final By PRICE_RANGE_MENU_BUTTON = By.cssSelector("div#menuItemButton-price_range button");

    @FindBy(id="price_filter_min")
    public WebElementFacade priceFilterMin;

    @FindBy(id="price_filter_max")
    public WebElementFacade priceFilterMax;

    public static By filterPanelSaveButton = By.id("filter-panel-save-button");

    //Rooms and beds
    public static final By ROOMS_AND_BEDS_MENU_ITEM = By.cssSelector("div#menuItemButton-rooms_and_beds button");

    @FindBy ( css = "div#filterItem-stepper-min_bathrooms-0 button:nth-of-type(2)")
    public WebElementFacade plusBathRoomButton;


    //More filters
    public static By MORE_FILTERS_MENU_ITEM = By.cssSelector("div#menuItemButton-dynamicMoreFilters button");

    //label for Jaccuzi
    public static By jaccuziLabel = By.cssSelector("label[for=filterItem-checkbox-amenities-25]");

    //label Air conditioner
    public static By airConditionerLabel = By.cssSelector("label[for=filterItem-checkbox-amenities-5]");

    public static By showMoreThanThreeThousandPlacesButton = By.className("_2i58o3a");

}
