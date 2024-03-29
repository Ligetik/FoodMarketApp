package com.example.testactivityandroid_9.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Map;


public class CartModel implements Serializable {
    private String key,item_name,item_image, item_details, item_category, Вариант_блюда;
    private Map<String,Object>  Доп_ингредиенты;
    private int quantity, item_cost, totalPrice, id/*, item_cost_var1*/;
/*    private List<String> details;*/

    static int ppizzaCounter = 1;
    static int avocadoCounter = 1;
    static int djoCounter = 1;

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
        return Доп_ингредиенты;
    }

    public void setДопы(Map<String,Object>  Доп_ингредиенты) {
        this.Доп_ингредиенты = Доп_ингредиенты;
    }

    public void setВариант_блюда(String вариант_блюда) {
        Вариант_блюда = вариант_блюда;
    }

    public String getВариант_блюда() {
        return Вариант_блюда;
    }

    @Override
    public String toString() {

        //Если допы имеют null, то возвращает пустоту
        if (Доп_ингредиенты == null) {
            Доп_ингредиенты = Collections.emptyMap();
        }
        if (Вариант_блюда == null) {
            Вариант_блюда = "По умолчанию";
        }
        if (id == 1) {
            return
                    "<p><b><u>PodkrePizza</u></b>" +
                    "<br>№ " + ppizzaCounter++ +
                    "<br>" + item_category +
                    "<br>Название: " + item_name +
                    ", <br>Количество: " + "<b>" + quantity + " шт." + "</b>" +
                    ", <br>Цена: " + item_cost + " ₽" +
                    ", <br>Описание: " + item_details +
                    ", <br>Вариант блюда: " + Вариант_блюда +
                    ", <br>Доп ингредиенты: " + Доп_ингредиенты /*+
                    "</p>"*/;
        }
        else if (id == 2) {
            return "<p><b><u>Avocado</u></b>" +
                    "<br>№ " + avocadoCounter++ +
                    "<br>" + item_category +
                    "<br>Название: " + item_name +
                    ", <br>Количество: " + "<b>" + quantity + " шт." + "</b>" +
                    ", <br>Цена: " + item_cost + " ₽" +
                    ", <br>Описание: " + item_details +
                    ", <br>Вариант блюда: " + Вариант_блюда +
                    ", <br>Доп ингредиенты: " + Доп_ингредиенты;
        } else if (id == 3) {
            return "<p><b><u>Джо</u></b>" +
                    "<br>№ " + djoCounter++ +
                    "<br>" + item_category +
                    "<br>Название: " + item_name +
                    ", <br>Количество: " + "<b>" + quantity + " шт." + "</b>" +
                    ", <br>Цена: " + item_cost + " ₽" +
                    ", <br>Описание: " + item_details +
                    ", <br>Вариант блюда: " + Вариант_блюда +
                    ", <br>Доп ингредиенты: " + Доп_ингредиенты;
        }

       return "";
    }
    public static void resetCounter() {
        ppizzaCounter = 1;
        avocadoCounter = 1;
        djoCounter = 1;
    }
}
