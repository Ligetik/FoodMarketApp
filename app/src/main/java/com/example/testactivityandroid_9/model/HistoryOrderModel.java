package com.example.testactivityandroid_9.model;

public class HistoryOrderModel {
    private String  Адрес_доставки, Дата_и_время, Имя, Комментарий, Номер_телефона,
            Способ_оплаты_курьеру;
    private int Номер_заказа, Сумма_заказа;

    public HistoryOrderModel() {
    }

    public String getАдрес_доставки() {
        return Адрес_доставки;
    }

    public void setАдрес_доставки(String адрес_доставки) {
        Адрес_доставки = адрес_доставки;
    }

    public String getДата_и_время() {
        return Дата_и_время;
    }

    public void setДата_и_время(String дата_и_время) {
        Дата_и_время = дата_и_время;
    }

    public String getИмя() {
        return Имя;
    }

    public void setИмя(String имя) {
        Имя = имя;
    }

    public String getКомментарий() {
        return Комментарий;
    }

    public void setКомментарий(String комментарий) {
        Комментарий = комментарий;
    }

    public String getНомер_телефона() {
        return Номер_телефона;
    }

    public void setНомер_телефона(String номер_телефона) {
        Номер_телефона = номер_телефона;
    }

    public String getСпособ_оплаты_курьеру() {
        return Способ_оплаты_курьеру;
    }

    public void setСпособ_оплаты_курьеру(String способ_оплаты_курьеру) {
        Способ_оплаты_курьеру = способ_оплаты_курьеру;
    }

    public int getНомер_заказа() {
        return Номер_заказа;
    }

    public void setНомер_заказа(int номер_заказа) {
        Номер_заказа = номер_заказа;
    }

    public int getСумма_заказа() {
        return Сумма_заказа;
    }

    public void setСумма_заказа(int сумма_заказа) {
        Сумма_заказа = сумма_заказа;
    }
}
