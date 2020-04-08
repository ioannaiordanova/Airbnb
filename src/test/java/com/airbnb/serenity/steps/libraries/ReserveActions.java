package com.airbnb.serenity.steps.libraries;

import com.airbnb.serenity.entities.BookingOptions;

public class ReserveActions
        extends BaseActions {

    public String calculateDaysAndNights(BookingOptions bookingOptions) {
        return String.format("%.0f", bookingOptions.getPrice() * bookingOptions.getNights());
    }

/*    @Step
    public void opensReservePage() {
        reservePage.open();
    }*/
}
