package com.example.testactivityandroid_9.listener;

import com.example.testactivityandroid_9.model.PPpizzaModel;

import java.util.List;

public interface IPPpizzaLoadListener {

    void OnPPpizzaloadSuccess(List<PPpizzaModel> ppizzaModels);
    void OnPPpizzaloadFailed(String message);
}
