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
    public static final By CURRENCY_TABLE_LINK = By.cssSelector("div#simple-header-locale-menu a[href$=currency]");
    public static final By CURRENCY_TYPE = By.className("_a7a5sx");


    @FindBy (css ="div#simple-header-locale-menu a[href$=currency]")
    public WebElementFacade openCurrencyTableLink;

    @FindBy (css = "ul._19s389u li button")
    public List<WebElementFacade> listWithCurrencies;



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



    //Check the results
    //"//div[@class='_1svux14' and @data-visible='true']//h3[contains(text(),'April')]"[contains(text(),'April')]
    public static By CALENDARS_TIME_PERIOD = By.cssSelector("div[class=_1svux14][data-visible=true] h3");


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
