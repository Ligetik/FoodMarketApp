package com.example.testactivityandroid_9;


import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testactivityandroid_9.model.PPpizzaModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailedActivity extends AppCompatActivity {

    @BindView(R.id.item_image)
    ImageView item_image;
    @BindView(R.id.item_descr)
    TextView item_descr;
    @BindView(R.id.item_name)
    TextView item_name;

    PPpizzaModel pPpizzaModel = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        ButterKnife.bind(this);

        final Object object = getIntent().getSerializableExtra("details");
        if (object instanceof PPpizzaModel) {
            pPpizzaModel = (PPpizzaModel) object;
        }

        if (pPpizzaModel != null) {
            Glide.with(getApplicationContext()).load(pPpizzaModel.getItem_image()).into(item_image);
            item_descr.setText(pPpizzaModel.getItem_details());
            item_name.setText(pPpizzaModel.getItem_name());
        }
    }
}