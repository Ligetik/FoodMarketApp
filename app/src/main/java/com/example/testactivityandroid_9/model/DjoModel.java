package com.example.testactivityandroid_9.model;

import java.io.Serializable;

public class DjoModel implements Serializable {
    private String key, item_name, item_image, item_details, item_ratings, item_weight,
            item_category;
    private int item_cost, id, item_cost_var1, item_cost_var2, item_cost_var3, item_cost_var4,
            item_cost_var5, item_cost_var6, item_cost_var7, item_cost_var8;

    public DjoModel() {
    }

    public DjoModel(String key, String item_name, String item_image, int item_cost,
                    String item_details, String item_ratings, String item_weight,
                    String item_category, int id, int item_cost_var1, int item_cost_var2,
                    int item_cost_var3, int item_cost_var4, int item_cost_var5, int item_cost_var6, int item_cost_var7, int item_cost_var8) {
        this.key = key;
        this.item_name = item_name;
        this.item_image = item_image;
        this.item_cost = item_cost;
        this.item_details = item_details;
        this.item_ratings = item_ratings;
        this.item_weight = item_weight;
        this.item_category = item_category;
        this.id = id;
        this.item_cost_var1 = item_cost_var1;
        this.item_cost_var2 = item_cost_var2;
        this.item_cost_var3 = item_cost_var3;
        this.item_cost_var4 = item_cost_var4;
        this.item_cost_var5 = item_cost_var5;
        this.item_cost_var6 = item_cost_var6;
        this.item_cost_var7 = item_cost_var7;
        this.item_cost_var8 = item_cost_var8;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getItem_cost_var1() {
        return item_cost_var1;
    }

    public void setItem_cost_var1(int item_cost_var1) {
        this.item_cost_var1 = item_cost_var1;
    }

    public int getItem_cost_var2() {
        return item_cost_var2;
    }

    public void setItem_cost_var2(int item_cost_var2) {
        this.item_cost_var2 = item_cost_var2;
    }

    public int getItem_cost_var3() {
        return item_cost_var3;
    }

    public void setItem_cost_var3(int item_cost_var3) {
        this.item_cost_var3 = item_cost_var3;
    }

    public int getItem_cost_var4() {
        return item_cost_var4;
    }

    public void setItem_cost_var4(int item_cost_var4) {
        this.item_cost_var4 = item_cost_var4;
    }

    public int getItem_cost_var5() {
        return item_cost_var5;
    }

    public void setItem_cost_var5(int item_cost_var5) {
        this.item_cost_var5 = item_cost_var5;
    }

    public String getItem_category() {
        return item_category;
    }

    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }

    public int getItem_cost_var6() {
        return item_cost_var6;
    }

    public void setItem_cost_var6(int item_cost_var6) {
        this.item_cost_var6 = item_cost_var6;
    }

    public int getItem_cost_var7() {
        return item_cost_var7;
    }

    public void setItem_cost_var7(int item_cost_var7) {
        this.item_cost_var7 = item_cost_var7;
    }

    public int getItem_cost_var8() {
        return item_cost_var8;
    }

    public void setItem_cost_var8(int item_cost_var8) {
        this.item_cost_var8 = item_cost_var8;
    }

/*    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }*/
}