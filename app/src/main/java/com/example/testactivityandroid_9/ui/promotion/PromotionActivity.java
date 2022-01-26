package com.example.testactivityandroid_9.ui.promotion;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivityandroid_9.R;
import com.example.testactivityandroid_9.adapter.PromotionAdapter;
import com.example.testactivityandroid_9.listener.IPromotionLoadListener;
import com.example.testactivityandroid_9.model.PromotionModel;
import com.example.testactivityandroid_9.utils.SpaceItemDeconstration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PromotionActivity extends AppCompatActivity implements IPromotionLoadListener{
    ImageButton imageButton;
    @BindView(R.id.promotionLayout)
    ConstraintLayout promotionLayout;
    @BindView(R.id.recycler_promotion)
    RecyclerView recycler_promotion;

    IPromotionLoadListener promotionLoadListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotion);

        ButterKnife.bind(this);

        clickBtnBack();

        promotionLoadListener = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recycler_promotion.setLayoutManager(gridLayoutManager);
        recycler_promotion.addItemDecoration(new SpaceItemDeconstration());


        List<PromotionModel> promotionModels = new ArrayList<>();
        FirebaseFirestore.getInstance()
                .collection("Items")
                .document("hYBO9afiXVTS9vzx73w9")
                .collection("Promotion")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                PromotionModel promotionModel = documentSnapshot.toObject(PromotionModel.class);
                                promotionModels.add(promotionModel);
                            }
                            promotionLoadListener.OnPromotionLoadSuccess(promotionModels);
                        }
                    }
                }).addOnFailureListener(e -> promotionLoadListener.OnPromotionLoadFailed(e.getMessage()));
    }

    private void clickBtnBack() {
        imageButton = (ImageButton)findViewById(R.id.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onBackPressed(); }
        });
    }

    @Override
    public void OnPromotionLoadSuccess(List<PromotionModel> promotionModels) {
        PromotionAdapter adapter = new PromotionAdapter (this, promotionModels);
        recycler_promotion.setAdapter(adapter);
    }

    @Override
    public void OnPromotionLoadFailed(String message) {
        Snackbar.make(promotionLayout,message,Snackbar.LENGTH_LONG).show();
    }
}