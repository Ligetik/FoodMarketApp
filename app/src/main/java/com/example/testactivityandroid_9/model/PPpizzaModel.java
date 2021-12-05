package com.example.testactivityandroid_9.model;

public class PPpizzaModel {
    private String key, name, image;
    private float price;

    public PPpizzaModel() {
    }

    public PPpizzaModel(String key, String item_name, String item_image, float item_cost) {
        this.key = key;
        this.name = item_name;
        this.image = item_image;
        this.price = item_cost;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String item_name) {
        this.name = item_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String item_image) {
        this.image = item_image;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(float item_cost) {
        this.price = item_cost;
    }
}