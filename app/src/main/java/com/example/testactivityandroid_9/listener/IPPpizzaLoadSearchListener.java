package com.example.testactivityandroid_9.listener;

import com.example.testactivityandroid_9.model.PPpizzaModel;

import java.util.List;

public interface IPPpizzaLoadSearchListener {
    void OnPPpizzaLoadSearchSuccess(List<PPpizzaModel> ppizzaModels);
    void OnPPpizzaLoadSearchFailed(String message);
}
