package com.example.testactivityandroid_9.model;

public class PromotionModel {
    private String item_image, item_name;

    public PromotionModel(String item_image, String item_name) {
        this.item_image = item_image;
        this.item_name = item_name;
    }

    public String getItem_image() {
        return item_image;
    }

    public void setItem_image(String item_image) {
        this.item_image = item_image;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }
}
