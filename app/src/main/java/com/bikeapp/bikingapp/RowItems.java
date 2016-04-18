package com.bikeapp.bikingapp;




public class RowItems {

    private String title, length;


    public RowItems(String title, String length){

        this.title = title;
        this.length = length;

    }


    public String getTitle() {
        return title;
    }

    public String getLength() {
        return length;
    }


    @Override
    public String toString() {
        return "Title: " + title + "Length: "  + length;
    }
}
