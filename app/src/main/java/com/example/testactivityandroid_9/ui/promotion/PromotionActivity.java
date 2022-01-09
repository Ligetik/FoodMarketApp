package com.example.testactivityandroid_9.ui.promotion;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivityandroid_9.R;
import com.example.testactivityandroid_9.utils.SpaceItemDeconstration;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PromotionActivity extends AppCompatActivity {
    @BindView(R.id.recycler_promotion)
    RecyclerView recycler_promotion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);
        ButterKnife.bind(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recycler_promotion.setLayoutManager(gridLayoutManager);
        recycler_promotion.addItemDecoration(new SpaceItemDeconstration());
    }
}