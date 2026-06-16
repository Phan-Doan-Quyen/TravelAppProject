package com.example.travelappproject.model.tour;

public class tour_City_Model {
    private String name;
    private int imageResource;

    public tour_City_Model(String name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }
}
