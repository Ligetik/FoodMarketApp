package com.example.testactivityandroid_9;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testactivityandroid_9.model.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderPlacedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);

       /* List<CartModel> list = (ArrayList<CartModel>)
                getIntent().getSerializableExtra("itemList");

        if (list != null && list.size() > 0) {
            for (CartModel model : list) {

                final HashMap<String, Object> cartMap = new HashMap<>();
                cartMap.put("item_name", model.getItem_name());
                cartMap.put("item_cost", model.getItem_cost());
                cartMap.put("item_image", model.getItem_image());
                cartMap.put("item_details", model.getItem_details());
                cartMap.put("key", model.getKey());
                cartMap.put("totalPrice", model.getItem_cost());
                cartMap.put("quantity", model.getQuantity());
                cartMap.put("details", model.getItem_details());

                FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Заказы")
                        .add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(OrderPlacedActivity.this, "Заказ оформлен" , Toast.LENGTH_SHORT).show();
                        *//*finish();*//*
                    }
                });
            }
        }*/
    }
}