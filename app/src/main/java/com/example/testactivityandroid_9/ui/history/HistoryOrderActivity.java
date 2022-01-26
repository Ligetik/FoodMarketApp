package com.example.testactivityandroid_9.ui.history;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.testactivityandroid_9.R;
import com.example.testactivityandroid_9.adapter.HistoryOrderAdapter;
import com.example.testactivityandroid_9.listener.IHistoryOrderLoadListener;
import com.example.testactivityandroid_9.model.HistoryOrderModel;
import com.example.testactivityandroid_9.utils.SpaceItemDeconstration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryOrderActivity extends AppCompatActivity implements IHistoryOrderLoadListener {

    ImageButton imageButton;
    @BindView(R.id.historyOrderLayout)
    ConstraintLayout historyOrderLayout;
    @BindView(R.id.recycler_historyOrder)
    RecyclerView recycler_historyOrder;

    IHistoryOrderLoadListener historyOrderLoadListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_order);

        ButterKnife.bind(this);

        clickBtnBack();

        historyOrderLoadListener = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        recycler_historyOrder.setLayoutManager(gridLayoutManager);
        recycler_historyOrder.addItemDecoration(new SpaceItemDeconstration());

        List<HistoryOrderModel> historyOrderModels = new ArrayList<>();
        FirebaseFirestore.getInstance()
                .collection("Users_Cart")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("История заказов")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                HistoryOrderModel historyOrderModel = documentSnapshot.toObject(HistoryOrderModel.class);
                                historyOrderModels.add(historyOrderModel);
                            }
                            historyOrderLoadListener.OnHistoryOrderLoadSuccess(historyOrderModels);
                        }
                    }
                }).addOnFailureListener(e -> historyOrderLoadListener.OnHistoryOrderLoadFailed(e.getMessage()));


    }

    private void clickBtnBack() {
        imageButton = (ImageButton)findViewById(R.id.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {onBackPressed(); }
        });
    }

    @Override
    public void OnHistoryOrderLoadSuccess(List<HistoryOrderModel> historyOrderModels) {
        HistoryOrderAdapter adapter = new HistoryOrderAdapter (this, historyOrderModels);
        recycler_historyOrder.setAdapter(adapter);
    }

    @Override
    public void OnHistoryOrderLoadFailed(String message) {
        Snackbar.make(historyOrderLayout,message,Snackbar.LENGTH_LONG).show();
    }
}