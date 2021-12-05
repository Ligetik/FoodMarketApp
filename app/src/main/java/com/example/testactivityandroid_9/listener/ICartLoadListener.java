package com.example.testactivityandroid_9.listener;

import com.example.testactivity.model.CartModel;

import java.util.List;

public interface ICartLoadListener{
    void OnCartloadSuccess(List<CartModel> cartModelList);
    void OnCartloadFailed(String message);
}
