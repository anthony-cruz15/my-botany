package com.example.mybotany;

public class Plant {
    private String name;
    private String time;
    private final  int imageResource;

    Plant(String name, String time, int imageResource) {
        this.name = name;
        this.time = time;
        this.imageResource = imageResource;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getImageResource() {
        return imageResource;
    }
}
