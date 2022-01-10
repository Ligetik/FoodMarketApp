package com.example.testactivityandroid_9.listener;


import com.example.testactivityandroid_9.model.PromotionModel;

import java.util.List;

public interface IPromotionLoadListener {
    void OnPromotionLoadSuccess(List<PromotionModel> promotionModels);
    void OnPromotionLoadFailed(String message);
}
