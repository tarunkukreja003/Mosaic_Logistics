package com.example.tarunkukreja.event_log_sponsor;

/**
 * Created by tarunkukreja on 07/07/17.
 */

class CustomClass {

    String category_text ;
    String sub_category_text ;
    String rent_price ;
    String buying_price ;
    String buying_text ;
    String rent_text ;
    String venue_header_name ;
    String venue_name ;
    String venue_price ;
    String merchandise_name ;
    String merchandise_price ;


    public CustomClass(String venue_name, String venue_price) {
        this.venue_name = venue_name;
        this.venue_price = venue_price;
    }



    public CustomClass(String venue_header_name) {
        this.venue_header_name = venue_header_name;
    }

    public CustomClass(String sub_category_text, String rent_price, String buying_price) {
        this.sub_category_text = sub_category_text;
        this.rent_price = rent_price;
        this.buying_price = buying_price;
    }

    public CustomClass(String sub_category_text, String rent_price, String buying_price, String buying_text, String rent_text) {
        this.sub_category_text = sub_category_text;
        this.rent_price = rent_price;
        this.buying_price = buying_price;
        this.buying_text = buying_text;
        this.rent_text = rent_text;
    }

    public String getCategory_text() {
        return category_text;
    }

    public void setCategory_text(String category_text) {
        this.category_text = category_text;
    }

    public String getSub_category_text() {
        return sub_category_text;
    }

    public void setSub_category_text(String sub_category_text) {
        this.sub_category_text = sub_category_text;
    }

    public String getRent_price() {
        return rent_price;
    }

    public void setRent_price(String rent_price) {
        this.rent_price = rent_price;
    }

    public String getBuying_price() {
        return buying_price;
    }

    public void setBuying_price(String buying_price) {
        this.buying_price = buying_price;
    }

    public String getVenue_header_name() {
        return venue_header_name;
    }

    public void setVenue_header_name(String venue_header_name) {
        this.venue_header_name = venue_header_name;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getVenue_price() {
        return venue_price;
    }

    public void setVenue_price(String venue_price) {
        this.venue_price = venue_price;
    }

    public String getMerchandise_name() {
        return merchandise_name;
    }

    public void setMerchandise_name(String merchandise_name) {
        this.merchandise_name = merchandise_name;
    }

    public String getMerchandise_price() {
        return merchandise_price;
    }

    public void setMerchandise_price(String merchandise_price) {
        this.merchandise_price = merchandise_price;
    }

    public String getBuying_text() {
        return buying_text;
    }

    public void setBuying_text(String buying_text) {
        this.buying_text = buying_text;
    }

    public String getRent_text() {
        return rent_text;
    }

    public void setRent_text(String rent_text) {
        this.rent_text = rent_text;
    }
}

