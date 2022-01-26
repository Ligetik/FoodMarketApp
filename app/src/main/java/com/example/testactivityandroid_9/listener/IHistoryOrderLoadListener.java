package com.example.testactivityandroid_9.listener;

import com.example.testactivityandroid_9.model.HistoryOrderModel;
import com.example.testactivityandroid_9.model.PromotionModel;

import java.util.List;

public interface IHistoryOrderLoadListener {
    void OnHistoryOrderLoadSuccess(List<HistoryOrderModel> historyOrderModels);
    void OnHistoryOrderLoadFailed(String message);
}
