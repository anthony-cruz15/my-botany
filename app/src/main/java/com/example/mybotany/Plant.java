package com.example.mybotany;

public class Plant {
    private String name;
    private String time;
    private String plantInfo;
    private String waterInfo;
    private final  int imageResource;

    public String getPlantInfo() {
        return plantInfo;
    }

    public void setPlantInfo(String plantInfo) {
        this.plantInfo = plantInfo;
    }

    public String getWaterInfo() {
        return waterInfo;
    }

    public void setWaterInfo(String waterInfo) {
        this.waterInfo = waterInfo;
    }

    Plant(String name, String time, int imageResource) {
        this.name = name;
        this.time = time;
        this.imageResource = imageResource;
    }

    public Plant(String name, String time, String plantInfo, String waterInfo, int imageResource) {
        this.name = name;
        this.time = time;
        this.plantInfo = plantInfo;
        this.waterInfo = waterInfo;
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
