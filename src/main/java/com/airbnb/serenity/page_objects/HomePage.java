package com.airbnb.serenity.page_objects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;

import javax.swing.text.html.CSS;
import java.util.List;

@DefaultUrl("/")
public class HomePage
extends PageObject {
    //Select localization currency EUR
    public static final By LANGUAGE_AND_CURRENCY_BUTTON = By.cssSelector("button._ndgmt0b");
    public static final By LANGUAGES_LINK = By.cssSelector("div#simple-header-locale-menu a[href$=language]");
    public static final By LANGUAGE_US_LINK = By.cssSelector("ul._19s389u li");
    public static final By CURRENCY_TABLE_LINK = By.cssSelector("div#simple-header-locale-menu a[href$=currency]");
    public static final By LIST_WITH_CURRENCIES = By.cssSelector("ul._19s389u li button");
    public static final By EURO_CURRENCIES = By.cssSelector("ul._19s389u li button");

    @FindBy (css ="div#simple-header-locale-menu a[href$=currency]")
    public WebElementFacade openCurrencyTableLink;

    @FindBy (css = "ul._19s389u li button")
    public List<WebElementFacade> listWithCurrencies;


    @FindBy (css = "ul._19s389u li button")
    public List<WebElementFacade> listWithLanguages;


    //Search criteria controls
    public static final By WHERE_SEARCH = By.id("Koan-magic-carpet-koan-search-bar__input");

    public static final By FROM_DATE_INPUT = By.id("checkin_input");
    public static final By TO_DATE_INPUT = By.id("checkout_input");

    public static final By CALENDAR_MONTH_NAME = By.cssSelector("div[class=_1svux14][data-visible=true] ._gucugi strong");
    public static final By NEXT_MONTH_BUTTON = By.className("_1h5uiygl");


    @FindBy ( css = "div[class=_1svux14][data-visible=true] table td[aria-disabled=false]")
    public List<WebElementFacade>  tdDatesEnabled;


    public static final By GUEST_PICKER_BTN = By.id("lp-guestpicker");
    @FindBy (css = "._42uiotg ._hgs47m")
    public List<WebElementFacade> listTypesOfGuests;

    public static final By ADULTS_LABEL = By.id("StepIncrementerRow-title-GuestCountFilter-GuestPicker-p0_magic_carpet-marquee_search_bar-adults");
    public static final By CHILDRENS_LABEL = By.id("StepIncrementerRow-title-GuestCountFilter-GuestPicker-p0_magic_carpet-marquee_search_bar-children");
    public static final By INCREASE_GUESTS_BTN = By.cssSelector("._1a72ixey button");
    public static final By SAVE_GUESTS_BTN = By.className("_b0ybw8s");

    public static final By SUBMIT_BUTTON = By.cssSelector("button[type=submit]");

    //other filters

    //price filter
    public static By menuItemButtonPriceRange = By.cssSelector("div#menuItemButton-price_range button");

    @FindBy (id="price_filter_min")
    public WebElementFacade priceFilterMin;

    @FindBy(id="price_filter_max")
    public WebElementFacade priceFilterMax;

    public static By filterPanelSaveButton = By.id("filter-panel-save-button");

    //Rooms and beds
    public static By menuItemButtonRoomsAndBeds = By.cssSelector("div#menuItemButton-rooms_and_beds button");

    @FindBy ( css = "div#filterItem-stepper-min_bathrooms-0 button:nth-of-type(2)")
    public WebElementFacade plusBathRoomButton;


    //More filters
    public static By menuItemButtonDynamicMoreFilters = By.cssSelector("div#menuItemButton-dynamicMoreFilters button");

    //label for Jaccuzi
    public static By jaccuziLabel = By.cssSelector("label[for=filterItem-checkbox-amenities-25]");

    //label Air conditioner
    public static By airConditionerLabel = By.cssSelector("label[for=filterItem-checkbox-amenities-5]");

    public static By showMoreThanThreeThousandPlacesButton = By.className("_2i58o3a");

    //List of Stays
    @FindBy (className = "_dx669kc")
    public List<WebElementFacade> listOfStays;

    public static By stars = By.cssSelector("span._3zgr580");//text
    public static By priceForOneNight = By.cssSelector("span._1p7iugi");

    //Check the results
    //"//div[@class='_1svux14' and @data-visible='true']//h3[contains(text(),'April')]"
    public static By CALENDARS_TIME_PERIOD = By.cssSelector("div[class=_1svux14][data-visible=true]");

    public static By FILTER_CALENDARS_BY_CURRENT_MONTH = By.xpath("//h3[contains(text(),'April')]");

    @FindBy (css = "td._12fun97 div._7ll8pi0.notranslate")
    List<WebElementFacade> listWithStartAndEndDateMarked;

    public static final By GESTS_LABEL = By.className(".guest-label");

    public static final By NUMBER_ADULTS_DISPAYED = By.cssSelector("div#GuestPicker-book_it-form-adults-stepper div._1665lvv span:nth-of-type(1)");
    public static final By NUMBER_CHILDREN_DISPLAYED = By.cssSelector("div#GuestPicker-book_it-form-children-stepper div._1665lvv span:nth-of-type(1)");
    public static final By CLOSE_BUTTON =By.cssSelector("div[aria-labelledby=GuestPicker-book_it-form] div._1jdtwz4 button");

    public static final By TOTAL_PRICE =By.className("_1d3ext9m");

    @FindBy (className = "_ra05uc")
    List<WebElementFacade> listOfPricesThatCumulateTheTotal;

    @FindBy(className = "_l1ngr4")
    List<WebElementFacade> listOfTaxesThatDecreaseTheTotal;

}
