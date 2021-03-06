package com.airbnb.serenity.steps.libraries;

import com.airbnb.serenity.entities.BookingOptions;
import com.airbnb.serenity.page_objects.HomePage;
import com.airbnb.serenity.page_objects.MenuWithFilters;
import com.airbnb.serenity.page_objects.StaysPage;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.time.LocalDate;



import static com.airbnb.serenity.page_objects.HomePage.*;
import static com.airbnb.serenity.page_objects.MenuWithFilters.ROOMS_AND_BEDS_MENU_ITEM;
import static com.airbnb.serenity.page_objects.StaysPage.*;


public class BookingActions
        extends BaseActions {
    private HomePage homePage;
    private MenuWithFilters menuWithFilters;
    private StaysPage staysPage;




    public void startSearchingWithPlace(String place) {
        WebDriverWait wait = new WebDriverWait(currentPage.getDriver(), 5000); // 5 seconds timeout
        wait.until(ExpectedConditions.invisibilityOfElementLocated(HomePage.DIALOG));

        System.out.println(HomePage.SEARCH_FOR_CITY);
        fillsFieldWithData(HomePage.SEARCH_FOR_CITY, place);

        this.currentPage.find(HomePage.SEARCH_FOR_CITY).sendKeys(Keys.ENTER);
    }

    public boolean isTheNextYearNeeded(LocalDate date, By locatorMonthAndYear) {
        String monthAndYearString = readsTextFrom(locatorMonthAndYear);
        if (monthAndYearString.contains(String.valueOf(date.getYear()))) {
            return false;
        }
        return true;
    }

    public boolean isTheNextMonthNeeded(LocalDate date, By locatorMonthAndYear) {
        String monthAndYearString = readsTextFrom(locatorMonthAndYear);
        if (monthAndYearString.contains(formatMonth(date.getMonth()))) {
            return false;
        }
        return true;

    }

    @Step
    public void applyDate(LocalDate date) {
        while (isTheNextYearNeeded(date, HomePage.CALENDAR_MONTH_NAME))
            clicksOn(HomePage.NEXT_MONTH_BUTTON);

        while (isTheNextMonthNeeded(date, HomePage.CALENDAR_MONTH_NAME))
            clicksOn(HomePage.NEXT_MONTH_BUTTON);

        for (WebElementFacade Day : homePage.tdDatesEnabled) {
            //System.out.println(String.valueOf(Day.getText()));
            if (date.getDayOfMonth() == Integer.parseInt(Day.getText())) {
                clicksOn(Day);
                return;
            }
        }
    }

    public void setCurrency(String currency) {
        for (int index = 0; index < homePage.listWithCurrencies.size(); index++) {
            if (homePage.listWithCurrencies.get(index).getText().contains(currency)) {
                clicksOn(homePage.listWithCurrencies.get(index));
                return;
            }
        }

    }

    public void increaseAdults(Integer countAdults) {
        for (int i = 0; i < countAdults; i++) {
            WebElementFacade increaseAdultsButton = getWebElementFacadeBy(INCREASE_GUESTS_ADULTS);
            clicksOn(increaseAdultsButton);
        }
    }

    public void increaseKids(Integer countKids) {
        for (int i = 0; i < countKids; i++) {
            WebElementFacade increaseKidsButton = getWebElementFacadeBy(INCREASE_GUESTS_KIDS);
            clicksOn(increaseKidsButton);
        }
    }

    public void selectAdditionalGuests(BookingOptions options) {
        clicksOn(GUEST_PICKER_BTN);
        increaseAdults(options.getAdults());
        increaseKids(options.getKids());
        clicksOn(SUBMIT_BUTTON);
    }

    public void setPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        clicksOn(MenuWithFilters.PRICE_RANGE_MENU_BUTTON);
        setElementInVisibleScreen(menuWithFilters.priceFilterMin);
        fillsFieldWithData(menuWithFilters.priceFilterMin, minPrice.toString());
        fillsFieldWithData(menuWithFilters.priceFilterMax, maxPrice.toString());
        clicksOn(menuWithFilters.filterPanelSaveButton);

    }

    public void selectRoomsAndBeds(int bathrooms) {
        boolean singleMenuItem = false;
        if (currentPage.findAll(ROOMS_AND_BEDS_MENU_ITEM).size() == 0) {
            clicksOn(MenuWithFilters.MORE_FILTERS_MENU_ITEM);
        } else {
            singleMenuItem = true;
            clicksOn(ROOMS_AND_BEDS_MENU_ITEM);
        }
        clicksOn(menuWithFilters.plusBathRoomButton);
        if (singleMenuItem) clicksOn(menuWithFilters.filterPanelSaveButton);
    }

    public void setAdditionalAmenities(boolean isAirConditioner, boolean isJacuzzi) {
        if (isAirConditioner) {
            clicksOn(menuWithFilters.airConditionerLabel);
        }

        if (isJacuzzi) {
            clicksOn(menuWithFilters.jaccuziLabel);
        }
        clicksOn(menuWithFilters.showMoreThanThreeThousandPlacesButton);

        WebDriverWait wait = new WebDriverWait(currentPage.getDriver(), 5000); // 5 seconds timeout
        wait.until(ExpectedConditions.invisibilityOfElementLocated(HomePage.DIALOG));


    }

    @Step("The Stay with {0} Stars or above not found with this search criteria")
    public void elementNotFound(float stars) {
        System.out.println("The element with stars >= " + String.valueOf(stars) + " not found");
    }


    @Step
    public void selectTheFirstStayWithAtLeastGivenStar(Float stars) throws InterruptedException {

        boolean nextPageExists = true;
        while (nextPageExists) {

            for (WebElementFacade linkElement : staysPage.listOfStays) {

                if (linkElement.containsElements(STARS_CSS) &&
                        Double.parseDouble(readsTextFrom((WebElementFacade) linkElement.findBy(STARS_CSS))) >= stars) {
                        goToURL(linkElement.findBy(STAY_HREF).getAttribute("href"));
                        return;
                }

            }

            WebElementFacade nextArrow = getWebElementFacadeBy(StaysPage.NEXT_PAGE_ARROW);
            if (nextArrow.isPresent()) {
                System.out.println("Clicking on the next page...");
                clicksOn(StaysPage.NEXT_PAGE_ARROW);

            } else {
                nextPageExists = false;
                elementNotFound(stars);

            }
        }
    }



}
