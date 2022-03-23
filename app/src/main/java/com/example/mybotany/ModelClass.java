package com.example.mybotany;

public class ModelClass {
    private String plantName;
    private int plantTime;
    private int plantImage;

    public ModelClass(String plantName, int plantTime, int plantImage) {
        this.plantName = plantName;
        this.plantTime = plantTime;
        this.plantImage = plantImage;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getPlantTime() {
        return plantTime;
    }

    public void setPlantTime(int plantTime) {
        this.plantTime = plantTime;
    }

    public int getPlantImage() {
        return plantImage;
    }

    public void setPlantImage(int plantImage) {
        this.plantImage = plantImage;
    }
}
