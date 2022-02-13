package com.example.testactivityandroid_9.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartModel implements Serializable {
    private String key,item_name,item_image, item_details, item_category;
    private Map<String,Object>  допы;
    private int quantity, item_cost, totalPrice, id;
/*    private List<String> details;*/

    public CartModel() {
    }

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getItem_cost() {
        return item_cost;
    }

    public void setItem_cost(int item_cost) {
        this.item_cost = item_cost;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getItem_details() {
        return item_details;
    }

    public void setItem_details(String item_details) {
        this.item_details = item_details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItem_category() {
        return item_category;
    }

    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }

    public Map<String,Object>  getДопы() {
        return допы;
    }

    public void setДопы(Map<String,Object>  допы) {
        допы = допы;
    }

    @Override
    public String toString() {
        switch (id) {
            case 1:
                return "<h3><p><b><u>PodkrePizza</u></b></h3>" +
                        "<br>Название: " + item_name +
                        ", <br>Количество: " + "<b>" + quantity + "</b>" +
                        ", <br>Цена: " + item_cost +
                        ", <br>Описание: " + item_details +
                        ", <br>Допы: " + допы +
                        "</p>";
            case 2:
                return "<h3><p><b><u>Avocado</u></b></h3> Позиция " + key + "<br>[" +
                        "<br>Название: " + item_name +
                        ", <br>Количество: " + "<b>" + quantity + "</b>" +
                        ", <br>Цена: " + item_cost +
                        ", <br>Описание: " + item_details +
                        "<br>]</p>";
            case 3:
                return "<h3><p><b><u>Джо</u></b></h3> Позиция " + key + "<br>[" +
                        "<br>Название: " + item_name +
                        ", <br>Количество: " + "<b>" + quantity + "</b>" +
                        ", <br>Цена: " + item_cost +
                        ", <br>Описание: " + item_details +
                        "<br>]</p>";
        }
       return "";
    }


    /*    public ArrayList<String> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<String> details) {
        this.details = details;
    }*/
/*    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }*/
}
