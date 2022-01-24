package com.example.testactivityandroid_9.model;

import java.io.Serializable;

public class DjoModel implements Serializable {
    private String key, item_name, item_image, item_details, item_ratings, item_weight;
    private int item_cost;
/*    private List<String> details;*/

    public DjoModel() {
    }

    public DjoModel(String key, String item_name, String item_image, int item_cost,
                    String item_details, String item_ratings, String item_weight/*, List<String> details*/) {
        this.key = key;
        this.item_name = item_name;
        this.item_image = item_image;
        this.item_cost = item_cost;
        this.item_details = item_details;
        this.item_ratings = item_ratings;
        this.item_weight = item_weight;
/*        this.details = details;*/
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

    public String getItem_details() {
        return item_details;
    }

    public void setItem_details(String item_details) {
        this.item_details = item_details;
    }

    public String getItem_ratings() {
        return item_ratings;
    }

    public void setItem_ratings(String item_ratings) {
        this.item_ratings = item_ratings;
    }

    public String getItem_weight() {
        return item_weight;
    }

    public void setItem_weight(String item_weight) {
        this.item_weight = item_weight;
    }

/*    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }*/
}