package com.example.testactivityandroid_9.listener;

import com.example.testactivityandroid_9.model.DjoModel;
import com.example.testactivityandroid_9.model.PPpizzaModel;

import java.util.List;

public interface IDjoLoadListener {

    void OnDjoloadSuccess(List<DjoModel> djoModels);
    void OnDjoloadFailed(String message);

}
