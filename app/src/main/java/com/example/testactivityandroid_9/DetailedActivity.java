package com.example.testactivityandroid_9;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.testactivityandroid_9.eventbus.MyUpdateCartEvent;
import com.example.testactivityandroid_9.listener.ICartLoadListener;
import com.example.testactivityandroid_9.model.AvocadoModel;
import com.example.testactivityandroid_9.model.CartModel;
import com.example.testactivityandroid_9.model.DjoModel;
import com.example.testactivityandroid_9.model.PPpizzaModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailedActivity extends AppCompatActivity implements ICartLoadListener{
    @BindView(R.id.mainLayout)
    ConstraintLayout mainLayout;
    @BindView(R.id.item_image)
    ImageView item_image;
    @BindView(R.id.item_descr)
    TextView item_descr;
    @BindView(R.id.item_name)
    TextView item_name;
    @BindView(R.id.item_category)
    TextView item_category;
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.item_topping1_plus)
    ImageView item_topping1_plus;
    @BindView(R.id.item_topping1_minus)
    ImageView item_topping1_minus;
    @BindView(R.id.item_topping1_quantity)
    TextView item_topping1_quantity;
    @BindView(R.id.item_topping2_plus)
    ImageView item_topping2_plus;
    @BindView(R.id.item_topping2_minus)
    ImageView item_topping2_minus;
    @BindView(R.id.item_topping2_quantity)
    TextView item_topping2_quantity;
    @BindView(R.id.item_topping3_plus)
    ImageView item_topping3_plus;
    @BindView(R.id.item_topping3_minus)
    ImageView item_topping3_minus;
    @BindView(R.id.item_topping3_quantity)
    TextView item_topping3_quantity;
    @BindView(R.id.item_topping4_plus)
    ImageView item_topping4_plus;
    @BindView(R.id.item_topping4_minus)
    ImageView item_topping4_minus;
    @BindView(R.id.item_topping4_quantity)
    TextView item_topping4_quantity;
    @BindView(R.id.btnAddToCart_Detailed)
    Button btnAddToCart_Detailed;
    @BindView(R.id.itemSizeRadioButton)
    RadioGroup itemSizeRadioButton;
    @BindView(R.id.item_topping1)
    ConstraintLayout item_topping1;
    @BindView(R.id.item_topping2)
    ConstraintLayout item_topping2;
    @BindView(R.id.item_topping3)
    ConstraintLayout item_topping3;
    @BindView(R.id.item_topping4)
    ConstraintLayout item_topping4;

    ICartLoadListener cartLoadListener;
    PPpizzaModel pPpizzaModel = null;
    AvocadoModel avocadoModel = null;
    DjoModel djoModel = null;

    int vetchinaQuantity = 0 , krabQuantity = 0, bekonQuantity = 0, krevetkaQuantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        ButterKnife.bind(this);

        cartLoadListener = this;

        btnBack.setOnClickListener(v -> finish());

        final Object object = getIntent().getSerializableExtra("details");

        ToppingsCount();

        LoadPpizza(object);

        LoadAvocado(object);

        LoadDjo(object);



    }
    private void LoadPpizza(Object object) {
        if (object instanceof PPpizzaModel) {
            pPpizzaModel = (PPpizzaModel) object;
        }

        if (pPpizzaModel != null) {
            Glide.with(getApplicationContext()).load(pPpizzaModel.getItem_image()).into(item_image);
            item_descr.setText(pPpizzaModel.getItem_details());
            item_name.setText(pPpizzaModel.getItem_name());
            item_category.setText(pPpizzaModel.getItem_category());

            switch (pPpizzaModel.getItem_category()) {
                case "Пицца":
                    item_topping1.setVisibility(View.VISIBLE);
                    item_topping2.setVisibility(View.VISIBLE);
                    item_topping3.setVisibility(View.VISIBLE);
                    item_topping4.setVisibility(View.VISIBLE);

                    btnAddToCart_Detailed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                FirebaseFirestore.getInstance()
                                        .collection("Users_Cart")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Корзина")
                                        .document(pPpizzaModel.getKey())
                                        .get()
                                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                                String id = FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document()
                                                        .getId();

                                                if (documentSnapshot.exists()) //если у пользователя уже есть товар в корзине
                                                { // Обновляет количество и общую цену
                                                    CartModel cartModel = documentSnapshot.toObject(CartModel.class);

                                                    cartModel.setQuantity(cartModel.getQuantity() + 1);
                                                    Map<String, Object> updateData = new HashMap<>();
                                                    updateData.put("quantity", cartModel.getQuantity());
                                                    updateData.put("totalPrice", cartModel.getQuantity() * cartModel.getItem_cost());

                                                    FirebaseFirestore.getInstance()
                                                            .collection("Users_Cart")
                                                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                            .collection("Корзина")
                                                            .document(pPpizzaModel.getKey())
                                                            .update(updateData)
                                                            .addOnSuccessListener(aVoid -> {
                                                                cartLoadListener.OnCartloadFailed("Добавлено");
                                                                finish();
                                                            })
                                                            .addOnFailureListener(e -> cartLoadListener.OnCartloadFailed(e.getMessage()));

                                                } else // если в корзине нет предмета, то добавить новый
                                                {
                                                    int toppingPizza = 0;
                                                    int VETCHINA_PRICE = 60;
                                                    int KRAB_PRICE = 80;

                                                    toppingPizza = VETCHINA_PRICE * vetchinaQuantity +
                                                            KRAB_PRICE * krabQuantity;


                                                    Map<String, Object> toppingsMap = new HashMap<>();
                                                    toppingsMap.put("Доп Ветчина", vetchinaQuantity);
                                                    toppingsMap.put("Доп Краб", krabQuantity);
                                                    toppingsMap.put("Доп Бекон", bekonQuantity);
                                                    toppingsMap.put("Доп Креветка", krevetkaQuantity);

                                                    Map<String, Object> toppingsMapArray = new HashMap<>();
                                                    toppingsMapArray.put("допы", toppingsMap);

                                                    CartModel cartModel = new CartModel();
                                                    cartModel.setItem_name(pPpizzaModel.getItem_name());
                                                    cartModel.setItem_image(pPpizzaModel.getItem_image());
                                                    cartModel.setItem_details(pPpizzaModel.getItem_details());
                                                    cartModel.setKey(pPpizzaModel.getKey());
                                                    cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                    cartModel.setQuantity(1);
                                                    cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);
                                                    cartModel.setId(pPpizzaModel.getId());
                                                    cartModel.setItem_category(pPpizzaModel.getItem_category());

                                                    Map<String, Object> dopSizeMap = new HashMap<>();

                                                    if (itemSizeRadioButton.getCheckedRadioButtonId() == R.id.radioButton1) {
                                                        dopSizeMap.put("Доп размер", "32 см");
                                                    } else if (itemSizeRadioButton.getCheckedRadioButtonId() == R.id.radioButton2) {
                                                        dopSizeMap.put("Доп размер", "40 см");
                                                    } else {
                                                        dopSizeMap.put("Доп размер", "-");
                                                    }

                                                    FirebaseFirestore.getInstance()
                                                            .collection("Users_Cart")
                                                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                            .collection("Корзина")
                                                            .document(id)
                                                            .set(cartModel)
                                                            .addOnSuccessListener(aVoid -> {

                                                                FirebaseFirestore.getInstance()
                                                                        .collection("Users_Cart")
                                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                        .collection("Корзина")
                                                                        .document(id)
                                                                        .update(dopSizeMap);

                                                                FirebaseFirestore.getInstance()
                                                                        .collection("Users_Cart")
                                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                        .collection("Корзина")
                                                                        .document(id)
                                                                        .update(toppingsMapArray);

                                                                cartLoadListener.OnCartloadFailed("Добавлено");
                                                                finish();
                                                            })
                                                            .addOnFailureListener(e -> cartLoadListener.OnCartloadFailed(e.getMessage()));
                                                }
                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());

                                            }

                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });

                    break;
                case "Роллы":
                    item_topping1.setVisibility(View.VISIBLE);
                    item_topping2.setVisibility(View.VISIBLE);
                    item_topping3.setVisibility(View.VISIBLE);
                    item_topping4.setVisibility(View.VISIBLE);

                    btnAddToCart_Detailed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                FirebaseFirestore.getInstance()
                                        .collection("Users_Cart")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Корзина")
                                        .document(pPpizzaModel.getKey())
                                        .get()
                                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                                String id = FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document()
                                                        .getId();

                                                if (documentSnapshot.exists()) //если у пользователя уже есть товар в корзине
                                                { // Обновляет количество и общую цену
                                                    CartModel cartModel = documentSnapshot.toObject(CartModel.class);

                                                    cartModel.setQuantity(cartModel.getQuantity() + 1);
                                                    Map<String, Object> updateData = new HashMap<>();
                                                    updateData.put("quantity", cartModel.getQuantity());
                                                    updateData.put("totalPrice", cartModel.getQuantity() * cartModel.getItem_cost());

                                                    FirebaseFirestore.getInstance()
                                                            .collection("Users_Cart")
                                                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                            .collection("Корзина")
                                                            .document(pPpizzaModel.getKey())
                                                            .update(updateData)
                                                            .addOnSuccessListener(aVoid -> {
                                                                cartLoadListener.OnCartloadFailed("Добавлено");
                                                                finish();
                                                            })
                                                            .addOnFailureListener(e -> cartLoadListener.OnCartloadFailed(e.getMessage()));

                                                } else // если в корзине нет предмета, то добавить новый
                                                {
                                                    int toppingPizza = 0;
                                                    int VETCHINA_PRICE = 60;
                                                    int KRAB_PRICE = 80;

                                                    toppingPizza = VETCHINA_PRICE * vetchinaQuantity +
                                                            KRAB_PRICE * krabQuantity;


                                                    Map<String, Object> toppingsMap = new HashMap<>();
                                                    toppingsMap.put("Доп Ветчина", vetchinaQuantity);
                                                    toppingsMap.put("Доп Краб", krabQuantity);
                                                    toppingsMap.put("Доп Бекон", bekonQuantity);
                                                    toppingsMap.put("Доп Креветка", krevetkaQuantity);

                                                    Map<String, Object> toppingsMapArray = new HashMap<>();
                                                    toppingsMapArray.put("допы", toppingsMap);

                                                    CartModel cartModel = new CartModel();
                                                    cartModel.setItem_name(pPpizzaModel.getItem_name());
                                                    cartModel.setItem_image(pPpizzaModel.getItem_image());
                                                    cartModel.setItem_details(pPpizzaModel.getItem_details());
                                                    cartModel.setKey(pPpizzaModel.getKey());
                                                    cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                    cartModel.setQuantity(1);
                                                    cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);
                                                    cartModel.setId(pPpizzaModel.getId());
                                                    cartModel.setItem_category(pPpizzaModel.getItem_category());

                                                    Map<String, Object> dopSizeMap = new HashMap<>();

                                                    if (itemSizeRadioButton.getCheckedRadioButtonId() == R.id.radioButton1) {
                                                        dopSizeMap.put("Доп размер", "32 см");
                                                    } else if (itemSizeRadioButton.getCheckedRadioButtonId() == R.id.radioButton2) {
                                                        dopSizeMap.put("Доп размер", "40 см");
                                                    } else {
                                                        dopSizeMap.put("Доп размер", "-");
                                                    }

                                                    FirebaseFirestore.getInstance()
                                                            .collection("Users_Cart")
                                                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                            .collection("Корзина")
                                                            .document(id)
                                                            .set(cartModel)
                                                            .addOnSuccessListener(aVoid -> {

                                                                FirebaseFirestore.getInstance()
                                                                        .collection("Users_Cart")
                                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                        .collection("Корзина")
                                                                        .document(id)
                                                                        .update(dopSizeMap);

                                                                FirebaseFirestore.getInstance()
                                                                        .collection("Users_Cart")
                                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                        .collection("Корзина")
                                                                        .document(id)
                                                                        .update(toppingsMapArray);

                                                                cartLoadListener.OnCartloadFailed("Добавлено");
                                                                finish();
                                                            })
                                                            .addOnFailureListener(e -> cartLoadListener.OnCartloadFailed(e.getMessage()));
                                                }
                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());

                                            }

                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });

                    break;
            }


        }
    }

    private void LoadAvocado(Object object) {
        if (object instanceof AvocadoModel) {
            avocadoModel = (AvocadoModel) object;
        }

        if (avocadoModel != null) {
            Glide.with(getApplicationContext()).load(avocadoModel.getItem_image()).into(item_image);
            item_descr.setText(avocadoModel.getItem_details());
            item_name.setText(avocadoModel.getItem_name());

            btnAddToCart_Detailed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        FirebaseFirestore.getInstance()
                                .collection("Users_Cart")
                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .collection("Корзина")
                                .document(avocadoModel.getKey())
                                .get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()) //если у пользователя уже есть товар в корзине
                                        { // Обновляет количество и общую цену
                                            CartModel cartModel = documentSnapshot.toObject(CartModel.class);

                                            cartModel.setQuantity(cartModel.getQuantity()+1);
                                            Map<String,Object> updateData = new HashMap<>();
                                            updateData.put("quantity", cartModel.getQuantity());
                                            updateData.put("totalPrice", cartModel.getQuantity() * cartModel.getItem_cost());

                                            FirebaseFirestore.getInstance()
                                                    .collection("Users_Cart")
                                                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .collection("Корзина")
                                                    .document(avocadoModel.getKey())
                                                    .update(updateData)
                                                    .addOnSuccessListener(aVoid -> {

                                                        cartLoadListener.OnCartloadFailed("Добавлено");
                                                    })
                                                    .addOnFailureListener(e -> cartLoadListener.OnCartloadFailed(e.getMessage()));

                                        }
                                        else // если в корзине нет предмета, то добавить новый
                                        {
                                            CartModel cartModel = new CartModel();
                                            cartModel.setItem_name(avocadoModel.getItem_name());
                                            cartModel.setItem_image(avocadoModel.getItem_image());
                                            cartModel.setItem_details(avocadoModel.getItem_details());
                                            cartModel.setKey(avocadoModel.getKey());
                                            cartModel.setItem_cost(avocadoModel.getItem_cost());
                                            cartModel.setQuantity(1);
                                            cartModel.setTotalPrice(avocadoModel.getItem_cost());
                                            cartModel.setId(avocadoModel.getId());

                                            Map<String,Object> dop = new HashMap<>();
                                            dop.put("size", "32");
                                            dop.put("dops", "krab");

                                            FirebaseFirestore.getInstance()
                                                    .collection("Users_Cart")
                                                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .collection("Корзина")
                                                    .document(avocadoModel.getKey())
                                                    .set(cartModel)
                                                    .addOnSuccessListener(aVoid -> {

                                                        FirebaseFirestore.getInstance()
                                                                .collection("Users_Cart")
                                                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                .collection("Корзина")
                                                                .document(avocadoModel.getKey())
                                                                .update(dop);

                                                        cartLoadListener.OnCartloadFailed("Добавлено");
                                                    })
                                                    .addOnFailureListener(e -> cartLoadListener.OnCartloadFailed(e.getMessage()));
                                        }
                                        EventBus.getDefault().postSticky(new MyUpdateCartEvent());

                                    }

                                });
                    } catch (Exception e) {
                        FirebaseCrashlytics.getInstance().recordException(e);
                    }
                }
            });
        }
    }

    private void LoadDjo(Object object) {
        if (object instanceof DjoModel) {
            djoModel = (DjoModel) object;
        }

        if (djoModel != null) {
            Glide.with(getApplicationContext()).load(djoModel.getItem_image()).into(item_image);
            item_descr.setText(djoModel.getItem_details());
            item_name.setText(djoModel.getItem_name());
        }
    }





    private void ToppingsCount() {
        item_topping1_plus.setOnClickListener(v ->{

            if (vetchinaQuantity >= 0) {
                vetchinaQuantity++;
                item_topping1_quantity.setText(String.valueOf(vetchinaQuantity));
            }
        });

        item_topping1_minus.setOnClickListener(v ->{

            if (vetchinaQuantity > 0) {
                vetchinaQuantity--;
                item_topping1_quantity.setText(String.valueOf(vetchinaQuantity));
            }
        });

        item_topping2_plus.setOnClickListener(v ->{

            if (krabQuantity >= 0) {
                krabQuantity++;
                item_topping2_quantity.setText(String.valueOf(krabQuantity));
            }
        });

        item_topping2_minus.setOnClickListener(v ->{

            if (krabQuantity > 0) {
                krabQuantity--;
                item_topping2_quantity.setText(String.valueOf(krabQuantity));
            }
        });

        item_topping3_plus.setOnClickListener(v ->{

            if (bekonQuantity >= 0) {
                bekonQuantity++;
                item_topping3_quantity.setText(String.valueOf(bekonQuantity));
            }
        });

        item_topping3_minus.setOnClickListener(v ->{

            if (bekonQuantity > 0) {
                bekonQuantity--;
                item_topping3_quantity.setText(String.valueOf(bekonQuantity));
            }
        });

        item_topping4_plus.setOnClickListener(v ->{

            if (krevetkaQuantity >= 0) {
                krevetkaQuantity++;
                item_topping4_quantity.setText(String.valueOf(krevetkaQuantity));
            }
        });

        item_topping4_minus.setOnClickListener(v ->{

            if (krevetkaQuantity > 0) {
                krevetkaQuantity--;
                item_topping4_quantity.setText(String.valueOf(krevetkaQuantity));
            }
        });
    }

    @Override
    public void OnCartloadSuccess(List<CartModel> cartModelList) {
        int cartSum = 0;
        for (CartModel cartModel: cartModelList)
            cartSum += cartModel.getQuantity();
    }

    @Override
    public void OnCartloadFailed(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();
    }
}