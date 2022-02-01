package com.example.testactivityandroid_9;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivityandroid_9.adapter.MyCartAdapter;
import com.example.testactivityandroid_9.eventbus.MyUpdateCartEvent;
import com.example.testactivityandroid_9.listener.ICartLoadListener;
import com.example.testactivityandroid_9.model.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.slider.Slider;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity implements ICartLoadListener {

    @BindView(R.id.recycler_cart)
    RecyclerView recyclerCart;
    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.txtTotal)
    TextView txtTotal;
    @BindView(R.id.txtFreeDelivery)
    TextView txtFreeDelivery;
    @BindView(R.id.orderBonus)
    TextView orderBonus;
    @BindView(R.id.orderBonusTitleCount)
    TextView orderBonusTitleCount;
    @BindView(R.id.orderBonusTitleText)
    TextView orderBonusTitleText;
    @BindView(R.id.textView12)
    TextView textView12;
    @BindView(R.id.textView13)
    TextView textView13;
    @BindView(R.id.btnToOrder)
    Button btnToOrder;
    @BindView(R.id.btnGetBonus)
    Button btnGetBonus;
    @BindView(R.id.sliderBonus)
    Slider sliderBonus;
    @BindView(R.id.orderBonusEditText)
    EditText orderBonusEditText;

    ICartLoadListener cartLoadListener;

    /*List<CartModel> cartModel;*/
    List<CartModel> cartModel2 = new ArrayList<>(); // УДАЛИТЬ
    private int bonus;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        if (EventBus.getDefault().hasSubscriberForEvent(MyUpdateCartEvent.class))
            EventBus.getDefault().removeStickyEvent(MyUpdateCartEvent.class);
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void onUpdateCart(MyUpdateCartEvent event)
    {
        loadCartFromFribase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        init();
        loadCartFromFribase();

        LoadBonuses();

        orderBonusEditText.setTransformationMethod(null);

        if (FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {
            sliderBonus.setVisibility(View.GONE);
            orderBonusEditText.setVisibility(View.GONE);
            btnGetBonus.setVisibility(View.GONE);
            orderBonus.setVisibility(View.GONE);
            textView12.setVisibility(View.GONE);
            textView13.setVisibility(View.GONE);
        } else {
            FirebaseFirestore.getInstance()
                    .collection("user")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            bonus = documentSnapshot.getLong("bonus").intValue();
                            orderBonus.setText(bonus + "");

                            if (bonus == 0) {
                                sliderBonus.setVisibility(View.GONE);
                                orderBonusEditText.setVisibility(View.GONE);
                                btnGetBonus.setVisibility(View.GONE);
                            } else {
                                orderBonusEditText.setText(bonus + "");

                                sliderBonus.setValueTo(bonus);
                                sliderBonus.setValue(bonus);
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(CartActivity.this, "Error", Toast.LENGTH_SHORT).show();
                        }
                    });


            sliderBonus.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onStartTrackingTouch(@NonNull Slider slider) {

                }

                @SuppressLint("RestrictedApi")
                @Override
                public void onStopTrackingTouch(@NonNull Slider slider) {
                    String sliderBonusValue = String.valueOf((int) sliderBonus.getValue());
                    orderBonusEditText.setText(sliderBonusValue);
                }
            });

            btnGetBonus.setOnClickListener(v -> {
                String buttonText = btnGetBonus.getText().toString();
                String bonusCount = String.valueOf(orderBonusEditText.getText());

                if (buttonText.equals("Списать")) {
                    btnGetBonus.setText("Сбросить");
                    sliderBonus.setValue(Float.parseFloat(bonusCount));

                    orderBonusTitleText.setText("Оплата доставки бонусами");
                    orderBonusTitleCount.setText("-" + bonusCount + " ₽");
                } else {
                    btnGetBonus.setText("Списать");

                    orderBonusTitleText.setText("");
                    orderBonusTitleCount.setText("");
                }
            });
        }


            /*orderBonusEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String value = s.toString();
                if (s.toString().length() > 0) {
                    String sliderBonusValue = String.valueOf(sliderBonus.getValue());
                    orderBonusEditText.setText(sliderBonusValue);
                }
            }
        });*/

    }



    private void LoadBonuses() {

    }

    public void loadCartFromFribase() {
/*        List<CartModel> cartModels = new ArrayList<>();
        cartModel2 = cartModels;*/

        /*FirebaseFirestore.getInstance()
                .collection("Users_Cart")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Корзина")
                .document("Restaurants")
                .collection("PodkrePizza")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                                *//*String documentId = documentSnapshot.getId();*//*

                                CartModel cartModel = documentSnapshot.toObject(CartModel.class);

                                *//*cartModel.setDocumentId(documentId);*//*
                                cartModel.setKey(documentSnapshot.getId());

                                cartModels.add(cartModel);
                                *//*cartAdapter.notifyDataSetChanged();*//*
                            }
                            cartLoadListener.OnCartloadSuccess(cartModels);
                        }
                    }
                })
                .addOnFailureListener(e -> cartLoadListener.OnCartloadFailed(e.getMessage()));;

        FirebaseFirestore.getInstance()
                .collection("Users_Cart")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Корзина")
                .document("Restaurants")
                .collection("Djo")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                                *//*String documentId = documentSnapshot.getId();*//*

                                CartModel cartModel = documentSnapshot.toObject(CartModel.class);

                                *//*cartModel.setDocumentId(documentId);*//*
                                cartModel.setKey(documentSnapshot.getId());

                                cartModels.add(cartModel);
                                *//*cartAdapter.notifyDataSetChanged();*//*
                            }
                            cartLoadListener.OnCartloadSuccess(cartModels);
                        }
                    }
                })
                .addOnFailureListener(e -> cartLoadListener.OnCartloadFailed(e.getMessage()));*/


            //new 2_1
        List<CartModel> cartModels = new ArrayList<>(); //ВЕРНУТЬ
        cartModel2 = cartModels;

        FirebaseFirestore.getInstance()
                .collection("Users_Cart")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Корзина")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                                String documentId = documentSnapshot.getId();

                                CartModel cartModel = documentSnapshot.toObject(CartModel.class);

                                cartModel.setKey(documentSnapshot.getId());

                                cartModels.add(cartModel);
                            }
                            cartLoadListener.OnCartloadSuccess(cartModels);
                        }
                    }
                })
                .addOnFailureListener(e -> cartLoadListener.OnCartloadFailed(e.getMessage()));


/*               //new3
        List<CartModel> cartModels = new ArrayList<>();
        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
            FirebaseFirestore.getInstance()
                    .collection("Users_Cart")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .collection("Корзина")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if (error != null) {
                                Log.e("Firestore error", error.getMessage());
                                return;
                            }
                            for (DocumentSnapshot documentSnapshot : value.getDocuments()) {
                                String documentId = documentSnapshot.getId();
                                CartModel cartModel = documentSnapshot.toObject(CartModel.class);
                                cartModel.setDocumentId(documentId);
                            }

                            for (DocumentChange dc : value.getDocumentChanges()) {
                                if (dc.getType() == DocumentChange.Type.ADDED) {

                                    cartModels.add(dc.getDocument().toObject(CartModel.class));
                                }
                            }
                            cartLoadListener.OnCartloadSuccess(cartModels);
                        }
                    });
        });*/


        //new 2     РАБОЧИЙ
        /*List<CartModel> cartModels = new ArrayList<>();
        MyCartAdapter cartAdapter = new MyCartAdapter (this,cartModels);
        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
            FirebaseFirestore.getInstance()
                    .collection("Users_Cart")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .collection("Корзина")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    CartModel cartModel = documentSnapshot.toObject(CartModel.class);
                                    cartModels.add(cartModel);
                                    *//*cartAdapter.notifyDataSetChanged();*//*
                                }
                                cartLoadListener.OnCartloadSuccess(cartModels);
                            }
                        }
                    });
        });*/


                //new       РАБОЧИЙ
        /* List<CartModel> cartModels = new ArrayList<>();
         FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
             FirebaseFirestore.getInstance()
                    .collection("Users_Cart")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .collection("Корзина")
                    .addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                            if (error != null) {
                                Log.e("Firestore error", error.getMessage());
                                return;
                            }

                            for (DocumentChange dc : value.getDocumentChanges()) {
                                if (dc.getType() == DocumentChange.Type.ADDED) {

                                    cartModels.add(dc.getDocument().toObject(CartModel.class));
                                }
                            }
                            cartLoadListener.OnCartloadSuccess(cartModels);
                        }
                    });
        });*/


                    //old
       /* List<CartModel> cartModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Cart")
                .child("UNIQUE_USER_ID")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            for (DataSnapshot cartSnapshot: snapshot.getChildren())
                            {
                                CartModel cartModel = cartSnapshot.getValue(CartModel.class);
                                cartModel.setKey(cartSnapshot.getKey());
                                cartModels.add(cartModel);
                            }
                            cartLoadListener.OnCartloadSuccess(cartModels);
                        }
                        else
                            cartLoadListener.OnCartloadFailed("Корзина пустая");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        cartLoadListener.OnCartloadFailed(error.getMessage());
                    }
                });*/
    }

    private void init(){

        ButterKnife.bind(this);

        cartLoadListener = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerCart.setLayoutManager(linearLayoutManager);
        recyclerCart.addItemDecoration(new DividerItemDecoration(this,linearLayoutManager.getOrientation()));

        btnBack.setOnClickListener(v -> finish());


/*        btnToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                                        Intent intent = new Intent(getApplicationContext(), CartOrderingActivity.class);
                                        intent.putExtra("itemList", (Serializable) cartModel2);
                                        startActivity(intent);

            }
        });*/

        btnToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Проверяет есть ли в корзине товар
                FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Корзина")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    if(task.getResult().size() > 0) {
                                        for (DocumentSnapshot document : task.getResult()) {

                                        }
                                            Intent intent = new Intent(getApplicationContext(), CartOrderingActivity.class);
                                            intent.putExtra("itemList", (Serializable) cartModel2);
                                            startActivity(intent);

                                            /*startActivity(new Intent(getApplicationContext(), CartOrderingActivity.class));*/
                                    } else {
                                        Toast toast = Toast.makeText(CartActivity.this, "Корзина пуста!", Toast.LENGTH_SHORT);
                                        toast.setGravity(Gravity.TOP, 0, 0);
                                        toast.show();
                                    }
                                }
                            }
                        });
            }
        });
    }

    @Override
    public void OnCartloadSuccess(List<CartModel> cartModelList) {
        int sum = 0;
        int price2 = 800;
        for (CartModel cartModel : cartModelList) {
            sum+=cartModel.getTotalPrice();

            int price = cartModel.getTotalPrice();
            price2 -= price;
        }

        if (price2 < 0) {
            txtFreeDelivery.setText("Бесплатная доставка");
        }
        else {
            txtFreeDelivery.setText(price2 + " ₽");
        }

        txtTotal.setText(sum + " ₽");



        MyCartAdapter adapter = new MyCartAdapter (this,cartModelList);
        recyclerCart.setAdapter(adapter);
    }

    @Override
    public void OnCartloadFailed(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();
    }
}