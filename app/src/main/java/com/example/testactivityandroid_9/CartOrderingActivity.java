package com.example.testactivityandroid_9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testactivityandroid_9.model.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartOrderingActivity extends AppCompatActivity {
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.btnOrder)
    Button btnOrder;
    @BindView(R.id.orderName)
    EditText orderName;
    @BindView(R.id.orderNumber)
    EditText orderNumber;
    @BindView(R.id.orderAddress)
    EditText orderAddress;
    @BindView(R.id.orderCommentary)
    EditText orderCommentary;

    /*List<CartModel> cartModel = new ArrayList<>();*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_ordering);
        ButterKnife.bind(this);

        btnBack.setOnClickListener(v -> finish());

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                Intent intent = new Intent(getApplicationContext(), Ca.class);
                intent.putExtra("itemList", (Serializable) cartModel);
                startActivity(intent);*/

                 List<CartModel> list = (ArrayList<CartModel>)
                    getIntent().getSerializableExtra("itemList");

                if (list != null && list.size() > 0) {
                    for (CartModel model : list) {

                        final HashMap<String, Object> cartMap = new HashMap<>();
                        cartMap.put("item_name", model.getItem_name());
                        cartMap.put("item_cost", model.getItem_cost());
                        cartMap.put("item_image", model.getItem_image());
                        cartMap.put("item_details", model.getItem_details());
                        cartMap.put("key", model.getKey());
                        /*cartMap.put("totalPrice", model.getItem_cost());*/
                        cartMap.put("quantity", model.getQuantity());
                        cartMap.put("details", model.getItem_details());

                        FirebaseFirestore.getInstance()
                                .collection("Users_Cart")
                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .collection("Заказы")
                                .add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                Toast.makeText(CartOrderingActivity.this, "Заказ оформлен" , Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                    }
                }


                /*final HashMap<String, Object> cartMap2 = new HashMap<>();
                cartMap2.put("totalPrice", cagetItem_cost());
                FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Оформление заказа")
                        .add(cartMap2).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(CartOrderingActivity.this, "Заказ оформлен" , Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });*/
            }
        });


/*        CartModel cartModel = new CartModel();
        cartModel.setItem_name(pPpizzaModel.getItem_name());
        cartModel.setItem_image(pPpizzaModel.getItem_image());
        cartModel.setItem_details(pPpizzaModel.getItem_details());
        cartModel.setItem_cost(pPpizzaModel.getItem_cost());
        cartModel.setQuantity(pPpizzaModel));
        cartModel.setTotalPrice(pPpizzaModel.getItem_cost());

        FirebaseFirestore.getInstance()
                .collection("Users_Cart")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Корзина")
                .document(pPpizzaModel.getKey())
                .set(cartModel)
                .addOnSuccessListener(aVoid -> {
                    iCartLoadListener.OnCartloadFailed("Добавлено");
                })
                .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));
        }*/
    }
}