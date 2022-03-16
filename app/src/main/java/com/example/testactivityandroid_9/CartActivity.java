package com.example.testactivityandroid_9;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
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
    TextView txtMinDelivery;
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
    @BindView(R.id.txtTotalDelivery)
    TextView txtTotalDelivery;
    @BindView(R.id.btnToOrder)
    Button btnToOrder;
    @BindView(R.id.btnGetBonus)
    Button btnGetBonus;
    @BindView(R.id.sliderBonus)
    Slider sliderBonus;
    @BindView(R.id.orderBonusEditText)
    EditText orderBonusEditText;
    @BindView(R.id.orderPayment)
    RelativeLayout orderPayment;
    @BindView(R.id.orderBonusInfo)
    ConstraintLayout orderBonusInfo;
    @BindView(R.id.orderBonusWriteOff)
    ConstraintLayout orderBonusWriteOff;
    @BindView(R.id.selectDeliveryZone_TextView)
    AutoCompleteTextView selectDeliveryZone_TextView;

    ICartLoadListener cartLoadListener;
    CartModel cartModels;
    /*List<CartModel> cartModel;*/
    List<CartModel> cartModel2 = new ArrayList<>(); // УДАЛИТЬ
    private int bonus;

    int deliverySum;
    int sumTotal = 0 , totalMinOrder;
    int deliveryZoneCost = 160;

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

        /**
         *            ВЫРЕЗАНО
         *            Зона доставки
         *
         * selectDeliveryZone();*/

        loadCartFromFribase();

        loadBonuses();

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

        orderBonusEditText.setTransformationMethod(null);

    }

/**
 *            ВЫРЕЗАНО
 *          Зона доставки
 *
 * private void selectDeliveryZone() {
        ArrayList<String> list = new ArrayList<>();
        list.add("район Центральный - 160 ₽");
        list.add("район Семи Ветров - 200 ₽");
        list.add("район Слободка - 250 ₽");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,
                R.layout.activity_cart_list_items,
                list);

        selectDeliveryZone_TextView.setAdapter(arrayAdapter);

        selectDeliveryZone_TextView.setText("район Центральный - 160 ₽", false);

        selectDeliveryZone_TextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        deliveryZoneCost = 160;
                        break;
                    case 1:
                        deliveryZoneCost = 200;
                        break;
                    case 2:
                        deliveryZoneCost = 250;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + position);
                }

                cartLoadListener.OnCartloadSuccess(cartModel2);

                updateBonuses(cartModel2);
            }
        });
    }*/


    private void loadBonuses() {

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
                .orderBy("id")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                                CartModel cartModel = documentSnapshot.toObject(CartModel.class);

                                cartModel.setKey(documentSnapshot.getId());

                                cartModels.add(cartModel);
                            }

                            cartLoadListener.OnCartloadSuccess(cartModels);

                            if (FirebaseAuth.getInstance().getCurrentUser().isAnonymous()) {

                                orderBonusInfo.setVisibility(View.GONE);

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

                                                updateBonuses(cartModels);

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(CartActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                btnGetBonus.setOnClickListener(v -> {
                                    String buttonText = btnGetBonus.getText().toString();
                                    String bonusCount = String.valueOf(orderBonusEditText.getText());
                                    int deliverySum = CartActivity.this.deliverySum;
                                    int deliverySumCount = 0;

                                    if (sumTotal > 0) {
                                        if (buttonText.equals("Списать")) {
                                            btnGetBonus.setText("Сбросить");
                                            sliderBonus.setValue(Float.parseFloat(bonusCount));

                                            orderBonusTitleText.setText("Оплата доставки бонусами");
                                            orderBonusTitleCount.setText("-" + bonusCount + " ₽");

                                            deliverySum -= Integer.parseInt(bonusCount);

                                        }
                                        if (buttonText.equals("Сбросить")) {
                                            btnGetBonus.setText("Списать");

                                            orderBonusTitleText.setText("");
                                            orderBonusTitleCount.setText("");

                                            deliverySum += deliverySumCount;
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Добавьте любое блюдо в корзину",Toast.LENGTH_SHORT).show();
                                    }
                                    txtTotalDelivery.setText(deliverySum + " ₽");
                                    txtTotal.setText( sumTotal + deliverySum  + " ₽");
                                });
                            }

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

    private void updateBonuses(List<CartModel> cartModels) {
        if (bonus == 0 || cartModels.isEmpty()) {
            orderBonusWriteOff.setVisibility(View.GONE);
            sliderBonus.setVisibility(View.GONE);
        } else if (bonus >= deliverySum) {
            orderBonusEditText.setText(deliverySum + "");
            sliderBonus.setValueTo(Integer.parseInt(String.valueOf(deliverySum)));
            sliderBonus.setValue(Integer.parseInt(String.valueOf(deliverySum)));
        } else {
            orderBonusEditText.setText(bonus + "");
            sliderBonus.setValueTo(bonus);
            sliderBonus.setValue(bonus);
        }
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

                if (totalMinOrder > 0) {
                    Toast toast = Toast.makeText(CartActivity.this, "Соберите корзину до минимального заказа", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                } else {
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
/*                                        for (DocumentSnapshot document : task.getResult()) {

                                        }*/
                                            Intent intent = new Intent(getApplicationContext(), CartOrderingActivity.class);

                                            intent.putExtra("itemList", (Serializable) cartModel2);
                                            intent.putExtra("bonus", orderBonusEditText.getText().toString());
                                            intent.putExtra("btnGetBonus", btnGetBonus.getText().toString());
                                            intent.putExtra("deliverySum", deliverySum);
                                            intent.putExtra("totalSum", sumTotal /**
                                             *            ВЫРЕЗАНО
                                             *          Зона доставки
                                             *          + deliverySum*/);
                                            intent.putExtra("deliveryZoneCost", deliveryZoneCost);

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
            }
        });
    }

    @Override
    public void OnCartloadSuccess(List<CartModel> cartModelList) {
        int sum = 0;
        int price2 = 800;
        int PodkrePizza = 0;
        int Avocado = 0;
        int Djo = 0;

        for (CartModel cartModel : cartModelList) {
            sum += cartModel.getTotalPrice();

            int price = cartModel.getTotalPrice();
            price2 -= price;

            switch (cartModel.getId()) {
                case 1:
                    PodkrePizza = deliveryZoneCost;
                    break;
                case 2:
                    Avocado = deliveryZoneCost;
                    break;
                case 3:
                    Djo = deliveryZoneCost;
                    break;
            }
        }
        if (price2 < 0) {
            txtMinDelivery.setText("0 ₽");
        } else {
            txtMinDelivery.setText(price2 + " ₽");
        }

        totalMinOrder = price2;

        deliverySum = PodkrePizza + Avocado + Djo;

        txtTotalDelivery.setText("от " + deliverySum + " ₽");

        btnGetBonus.setText("Списать");
        orderBonusTitleText.setText("");
        orderBonusTitleCount.setText("");

        txtTotal.setText(sum /**
         *            ВЫРЕЗАНО
         *          Зона доставки
         *          + deliverySum*/ + " ₽");

        sumTotal = sum;

        MyCartAdapter adapter = new MyCartAdapter(this, cartModelList);
        recyclerCart.setAdapter(adapter);
    }

    @Override
    public void OnCartloadFailed(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();
    }
}