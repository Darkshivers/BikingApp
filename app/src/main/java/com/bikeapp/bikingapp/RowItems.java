package com.bikeapp.bikingapp;




public class RowItems {

    private String title, length, Description, filename;
    private BikeTypes biketype;


    public enum BikeTypes{MountainBike, RoadBike, BMXBike}

    public RowItems(String title, String length, BikeTypes biketype, String Description, String filename){

        this.title = title;
        this.length = length;
        this.biketype = biketype;
        this.Description = Description;
        this.filename = filename;

    }


    public String getTitle() {
        return title;
    }

    public String getLength() {
        return length;
    }

    public String getFilename() {return filename;}

    public String getDescription(){return Description;}

    public BikeTypes getBiketype() {return biketype;}




    @Override
    public String toString() {return "Title: " + title + "Length: "  + length + biketype.name() + "Description: " + Description + "filename" + filename;}

    public int getDrawable() {return BikeTypeDrawables(biketype);}


    public static int BikeTypeDrawables(BikeTypes biketype) {

        switch (biketype) {
            case MountainBike:
                return R.drawable.mountain;
            case RoadBike:
                return R.drawable.road;
            case BMXBike:
                return R.drawable.people;
        }

        return R.drawable.transport;
    }
}


