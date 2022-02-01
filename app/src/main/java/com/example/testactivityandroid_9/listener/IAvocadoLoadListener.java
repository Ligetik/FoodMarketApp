package com.example.testactivityandroid_9.listener;

import com.example.testactivityandroid_9.model.AvocadoModel;
import com.example.testactivityandroid_9.model.DjoModel;

import java.util.List;

public interface IAvocadoLoadListener {

    void OnAvocadoloadSuccess(List<AvocadoModel> avocadoModels);
    void OnAvocadoloadFailed(String message);

}
