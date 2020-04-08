package com.airbnb.serenity.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class BookingOptions {
    private String place;
    LocalDate startDate;
    LocalDate endDate;
    private int adults;
    private int kids;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private int bathRooms;
    private boolean airConditioner=false;
    private boolean jacuzzi=false;
    private float stars;

    private int price;
    private int nights;
    private int taxes;
    private int totalPrice;


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(Integer daysFromNow){
        LocalDate today = LocalDate.now();
        LocalDate startDate =  today.plusDays(daysFromNow);
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(Integer daysFromNow,Integer duration) {
        LocalDate today = LocalDate.now();
        LocalDate endDate =  today.plusDays(daysFromNow);
        endDate =  endDate.plusDays(duration);
        this.endDate = endDate;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getKids() {
        return kids;
    }

    public void setKids(int kids) {
        this.kids = kids;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getBathRooms() {
        return bathRooms;
    }

    public void setBathRooms(int bathRooms) {
        this.bathRooms = bathRooms;
    }

    public boolean isAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public boolean isJacuzzi() {
        return jacuzzi;
    }

    public void setJacuzzi(boolean jacuzzi) {
        this.jacuzzi = jacuzzi;
    }

    public float getStars() {
        return stars;
    }

    public void setStars(float stars) {
        this.stars = stars;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTaxes() {
        return taxes;
    }

    public void setTaxes(int taxes) {
        this.taxes = taxes;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNights() {
        return nights;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    public Month getStartOfTripMonth(){
        Month startOfTripMonth = this.getStartDate().getMonth();
        return startOfTripMonth;
    }

    public Month getEndOfTripMonth(){
        Month endOfTripMonth = this.getStartDate().getMonth();
        return endOfTripMonth;

    }

    public int getDayStartTrip(){
        int Day = this.getStartDate().getDayOfMonth();
        return Day;
    }

    public int getDayEndTrip(){
        int Day = this.getStartDate().getDayOfMonth();
        return Day;
    }

}
