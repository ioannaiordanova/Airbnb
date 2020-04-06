package com.airbnb.serenity.utils;

import com.airbnb.serenity.entities.BookingOptions;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class SetBookingOptionsByList {

    public static BookingOptions prepareBookingOptionsObject(BookingOptions bookingOptions ,List<Map<String, String>> requirements){
        for(Map<String, String> requirementtMap: requirements){
            for(Map.Entry<String, String> requirement: requirementtMap.entrySet()){
                System.out.println(requirement.getKey()+"  "+requirement.getValue());
                if (requirement.getKey().contains("min value")){
                    BigDecimal dc = new BigDecimal(requirement.getValue());
                    bookingOptions.setMinPrice(dc);
                }

                if (requirement.getKey().contains("max value")){
                    BigDecimal dc = new BigDecimal(requirement.getValue());
                    bookingOptions.setMaxPrice(dc);
                }

                if (requirement.getKey().contains("bathrooms")){
                    bookingOptions.setBathRooms(Integer.parseInt(requirement.getValue()));
                }

                if (requirement.getKey().contains("additional")){
                    if (requirement.getKey().contains("Air conditioner")){
                        bookingOptions.setAirConditioner(true);
                    }
                    else bookingOptions.setAirConditioner(false);

                    if (requirement.getKey().contains("Jacuzzi")){
                        bookingOptions.setJacuzzi(true);
                    }
                    else bookingOptions.setJacuzzi(false);

                }

            }

        }
        return bookingOptions;
    }
}
