package com.example.testactivityandroid_9.model;

public class PPpizzaModel {
    private String key, item_name, item_image;
    private int item_cost;

    public PPpizzaModel() {
    }

    public PPpizzaModel(String key, String item_name, String item_image, int item_cost) {
        this.key = key;
        this.item_name = item_name;
        this.item_image = item_image;
        this.item_cost = item_cost;
    }

    /*public String getKey() {
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
    }*/


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public int getItem_cost() {
        return item_cost;
    }

    public void setItem_cost(int item_cost) {
        this.item_cost = item_cost;
    }
}