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
    @BindView(R.id.item_topping5_plus)
    ImageView item_topping5_plus;
    @BindView(R.id.item_topping5_minus)
    ImageView item_topping5_minus;
    @BindView(R.id.item_topping5_quantity)
    TextView item_topping5_quantity;
    @BindView(R.id.item_topping6_plus)
    ImageView item_topping6_plus;
    @BindView(R.id.item_topping6_minus)
    ImageView item_topping6_minus;
    @BindView(R.id.item_topping6_quantity)
    TextView item_topping6_quantity;
    @BindView(R.id.item_topping7_plus)
    ImageView item_topping7_plus;
    @BindView(R.id.item_topping7_minus)
    ImageView item_topping7_minus;
    @BindView(R.id.item_topping7_quantity)
    TextView item_topping7_quantity;
    @BindView(R.id.item_topping8_plus)
    ImageView item_topping8_plus;
    @BindView(R.id.item_topping8_minus)
    ImageView item_topping8_minus;
    @BindView(R.id.item_topping8_quantity)
    TextView item_topping8_quantity;
    @BindView(R.id.item_topping9_plus)
    ImageView item_topping9_plus;
    @BindView(R.id.item_topping9_minus)
    ImageView item_topping9_minus;
    @BindView(R.id.item_topping9_quantity)
    TextView item_topping9_quantity;
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
    @BindView(R.id.item_topping5)
    ConstraintLayout item_topping5;
    @BindView(R.id.item_topping6)
    ConstraintLayout item_topping6;
    @BindView(R.id.item_topping7)
    ConstraintLayout item_topping7;
    @BindView(R.id.item_topping8)
    ConstraintLayout item_topping8;
    @BindView(R.id.item_topping9)
    ConstraintLayout item_topping9;
    @BindView(R.id.item_topping1_cost)
    TextView item_topping1_cost;
    @BindView(R.id.item_topping2_cost)
    TextView item_topping2_cost;
    @BindView(R.id.item_topping3_cost)
    TextView item_topping3_cost;
    @BindView(R.id.item_topping4_cost)
    TextView item_topping4_cost;
    @BindView(R.id.item_topping5_cost)
    TextView item_topping5_cost;
    @BindView(R.id.item_topping6_cost)
    TextView item_topping6_cost;
    @BindView(R.id.item_topping7_cost)
    TextView item_topping7_cost;
    @BindView(R.id.item_topping8_cost)
    TextView item_topping8_cost;
    @BindView(R.id.item_topping9_cost)
    TextView item_topping9_cost;
    @BindView(R.id.item_cost)
    TextView item_cost;

    ICartLoadListener cartLoadListener;

    PPpizzaModel pPpizzaModel = null;
    AvocadoModel avocadoModel = null;
    DjoModel djoModel = null;

    int item_toppingQuantity1 = 0, item_topping2Quantity = 0, item_topping3Quantity = 0,
            item_topping4Quantity = 0, item_topping5Quantity = 0, item_topping6Quantity = 0,
            item_topping7Quantity = 0, item_topping8Quantity = 0, item_topping9Quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        ButterKnife.bind(this);

        cartLoadListener = this;

        btnBack.setOnClickListener(v -> finish());

        final Object object = getIntent().getSerializableExtra("details");

        toppingsCountPpizza();

        loadPpizza(object);

        loadAvocado(object);

        loadDjo(object);


    }

    private void loadPpizza(Object object) {
        if (object instanceof PPpizzaModel) {
            pPpizzaModel = (PPpizzaModel) object;
        }

        if (pPpizzaModel != null) {
            Glide.with(getApplicationContext()).load(pPpizzaModel.getItem_image()).into(item_image);
            item_descr.setText(pPpizzaModel.getItem_details());
            item_name.setText(pPpizzaModel.getItem_name());
            item_category.setText(pPpizzaModel.getItem_category());
            item_cost.setText(pPpizzaModel.getItem_cost() + " ₽");

            itemSizeRadioButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioButton1:
                            item_cost.setText(pPpizzaModel.getItem_cost() + " ₽");
                            break;
                        case R.id.radioButton2:
                            item_cost.setText(pPpizzaModel.getItem_cost_var1() + " ₽");
                            break;
                        default:
                            break;
                    }
                }
            });

            switch (pPpizzaModel.getItem_category()) {
                case "Пицца":
                    item_topping1.setVisibility(View.VISIBLE);
                    item_topping2.setVisibility(View.VISIBLE);
                    item_topping3.setVisibility(View.VISIBLE);
                    item_topping4.setVisibility(View.VISIBLE);
                    item_topping5.setVisibility(View.VISIBLE);
                    item_topping6.setVisibility(View.VISIBLE);
                    item_topping7.setVisibility(View.VISIBLE);
                    item_topping8.setVisibility(View.VISIBLE);
                    item_topping9.setVisibility(View.VISIBLE);

                    final int VETCHINA_PRICE = 60;
                    item_topping1_cost.setText(VETCHINA_PRICE + " ₽");


                    final int KRAB_PRICE = 80;
                    item_topping2_cost.setText(KRAB_PRICE + " ₽");

                    final int BEKON_PRICE = 60;
                    item_topping3_cost.setText(BEKON_PRICE + " ₽");

                    final int KREVETKA_PRICE = 100;
                    item_topping4_cost.setText(KREVETKA_PRICE + " ₽");

                    final int KURICA_PRICE = 50;
                    item_topping5_cost.setText(KURICA_PRICE + " ₽");

                    final int OSTR_KURICA_PRICE = 50;
                    item_topping6_cost.setText(OSTR_KURICA_PRICE + " ₽");

                    final int KOLBASKI_PRICE = 60;
                    item_topping7_cost.setText(KOLBASKI_PRICE + " ₽");

                    final int PEPPERONI_PRICE = 50;
                    item_topping8_cost.setText(PEPPERONI_PRICE + " ₽");

                    final int PEREC_PRICE = 30;
                    item_topping9_cost.setText(PEREC_PRICE + " ₽");


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

                                                } else { // если в корзине нет предмета, то добавить новый

                                                    int toppingPizza =
                                                        (VETCHINA_PRICE * item_toppingQuantity1) +
                                                        (KRAB_PRICE * item_topping2Quantity) +
                                                        (BEKON_PRICE * item_topping3Quantity) +
                                                        (KREVETKA_PRICE * item_topping4Quantity) +
                                                        (KURICA_PRICE * item_topping5Quantity) +
                                                        (OSTR_KURICA_PRICE * item_topping6Quantity) +
                                                        (KOLBASKI_PRICE * item_topping7Quantity) +
                                                        (PEPPERONI_PRICE * item_topping8Quantity) +
                                                        (PEREC_PRICE * item_topping9Quantity);

                                                    Map<String, Object> toppingsMap = new HashMap<>();

                                                    if (item_toppingQuantity1 > 0 ) {
                                                        toppingsMap.put("Доп Ветчина", item_toppingQuantity1);
                                                    }
                                                    if (item_topping2Quantity > 0) {
                                                        toppingsMap.put("Доп Краб", item_topping2Quantity);
                                                    }
                                                    if (item_topping3Quantity > 0) {
                                                        toppingsMap.put("Доп Бекон", item_topping3Quantity);
                                                    }
                                                    if (item_topping4Quantity > 0) {
                                                        toppingsMap.put("Доп Креветка", item_topping4Quantity);
                                                    }
                                                    if (item_topping5Quantity > 0) {
                                                        toppingsMap.put("Доп Куриное филе", item_topping5Quantity);
                                                    }
                                                    if (item_topping6Quantity > 0) {
                                                        toppingsMap.put("Доп Маринованная курица (острая)", item_topping6Quantity);
                                                    }
                                                    if (item_topping7Quantity > 0) {
                                                        toppingsMap.put("Доп Охотничье колбаски", item_topping7Quantity);
                                                    }
                                                    if (item_topping8Quantity > 0) {
                                                        toppingsMap.put("Доп Пепперони", item_topping8Quantity);
                                                    }
                                                    if (item_topping9Quantity > 0) {
                                                        toppingsMap.put("Доп Перец острый", item_topping9Quantity);
                                                    }

                                                        CartModel cartModel = new CartModel();
                                                        cartModel.setItem_name(pPpizzaModel.getItem_name());
                                                        cartModel.setItem_image(pPpizzaModel.getItem_image());
                                                        cartModel.setItem_details(pPpizzaModel.getItem_details());
                                                        cartModel.setKey(pPpizzaModel.getKey());
                                                        /*cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);*/
                                                        cartModel.setQuantity(1);
                                                        /*cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);*/
                                                        cartModel.setId(pPpizzaModel.getId());
                                                        cartModel.setДопы(toppingsMap);
                                                        cartModel.setItem_category(pPpizzaModel.getItem_category());

                                                        Map<String, Object> dopSizeMap = new HashMap<>();

                                                        if (itemSizeRadioButton.getCheckedRadioButtonId() == R.id.radioButton1) {
                                                            /*dopSizeMap.put("Доп размер", "32 см");*/

                                                            cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                            cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);

                                                            FirebaseFirestore.getInstance()
                                                                    .collection("Users_Cart")
                                                                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                    .collection("Корзина")
                                                                    .document(pPpizzaModel.getKey())
                                                                    .set(cartModel)
                                                                    .addOnSuccessListener(aVoid -> {
                                                                        cartLoadListener.OnCartloadFailed("Добавлено");
                                                                        finish();
                                                                    });

                                                        } else if (itemSizeRadioButton.getCheckedRadioButtonId() == R.id.radioButton2) {
                                                            dopSizeMap.put("Доп размер", "40 см");

                                                            cartModel.setItem_cost(pPpizzaModel.getItem_cost_var1() + toppingPizza);
                                                            cartModel.setTotalPrice(pPpizzaModel.getItem_cost_var1() + toppingPizza);

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

    /*                                                                FirebaseFirestore.getInstance()
                                                                            .collection("Users_Cart")
                                                                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                            .collection("Корзина")
                                                                            .document(id)
                                                                            .update(toppingsMapArray);*/

                                                                        cartLoadListener.OnCartloadFailed("Добавлено");
                                                                        finish();
                                                                    })
                                                                    .addOnFailureListener(e -> cartLoadListener.OnCartloadFailed(e.getMessage()));
                                                        }
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

                                                    toppingPizza = VETCHINA_PRICE * item_toppingQuantity1 +
                                                            KRAB_PRICE * item_topping2Quantity;


                                                    Map<String, Object> toppingsMap = new HashMap<>();
                                                    toppingsMap.put("Доп Ветчина", item_toppingQuantity1);
                                                    toppingsMap.put("Доп Краб", item_topping2Quantity);
                                                    toppingsMap.put("Доп Бекон", item_topping3Quantity);
                                                    toppingsMap.put("Доп Креветка", item_topping4Quantity);


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

    private void loadAvocado(Object object) {
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

    private void loadDjo(Object object) {
        if (object instanceof DjoModel) {
            djoModel = (DjoModel) object;
        }

        if (djoModel != null) {
            Glide.with(getApplicationContext()).load(djoModel.getItem_image()).into(item_image);
            item_descr.setText(djoModel.getItem_details());
            item_name.setText(djoModel.getItem_name());
        }
    }





    private void toppingsCountPpizza() {
        item_topping1_plus.setOnClickListener(v ->{

            if (item_toppingQuantity1 >= 0) {
                item_toppingQuantity1++;
                item_topping1_quantity.setText(String.valueOf(item_toppingQuantity1));
            }
        });

        item_topping1_minus.setOnClickListener(v ->{

            if (item_toppingQuantity1 > 0) {
                item_toppingQuantity1--;
                item_topping1_quantity.setText(String.valueOf(item_toppingQuantity1));
            }
        });


        item_topping2_plus.setOnClickListener(v ->{

            if (item_topping2Quantity >= 0) {
                item_topping2Quantity++;
                item_topping2_quantity.setText(String.valueOf(item_topping2Quantity));
            }
        });

        item_topping2_minus.setOnClickListener(v ->{

            if (item_topping2Quantity > 0) {
                item_topping2Quantity--;
                item_topping2_quantity.setText(String.valueOf(item_topping2Quantity));
            }
        });


        item_topping3_plus.setOnClickListener(v ->{

            if (item_topping3Quantity >= 0) {
                item_topping3Quantity++;
                item_topping3_quantity.setText(String.valueOf(item_topping3Quantity));
            }
        });

        item_topping3_minus.setOnClickListener(v ->{

            if (item_topping3Quantity > 0) {
                item_topping3Quantity--;
                item_topping3_quantity.setText(String.valueOf(item_topping3Quantity));
            }
        });


        item_topping4_plus.setOnClickListener(v ->{

            if (item_topping4Quantity >= 0) {
                item_topping4Quantity++;
                item_topping4_quantity.setText(String.valueOf(item_topping4Quantity));
            }
        });

        item_topping4_minus.setOnClickListener(v ->{

            if (item_topping4Quantity > 0) {
                item_topping4Quantity--;
                item_topping4_quantity.setText(String.valueOf(item_topping4Quantity));
            }
        });

        item_topping5_plus.setOnClickListener(v ->{

            if (item_topping5Quantity >= 0) {
                item_topping5Quantity++;
                item_topping5_quantity.setText(String.valueOf(item_topping5Quantity));
            }
        });

        item_topping5_minus.setOnClickListener(v ->{

            if (item_topping5Quantity > 0) {
                item_topping5Quantity--;
                item_topping5_quantity.setText(String.valueOf(item_topping5Quantity));
            }
        });


        item_topping6_plus.setOnClickListener(v ->{

            if (item_topping6Quantity >= 0) {
                item_topping6Quantity++;
                item_topping6_quantity.setText(String.valueOf(item_topping6Quantity));
            }
        });

        item_topping6_minus.setOnClickListener(v ->{

            if (item_topping6Quantity > 0) {
                item_topping6Quantity--;
                item_topping6_quantity.setText(String.valueOf(item_topping6Quantity));
            }
        });


        item_topping7_plus.setOnClickListener(v ->{

            if (item_topping6Quantity >= 0) {
                item_topping7Quantity++;
                item_topping7_quantity.setText(String.valueOf(item_topping7Quantity));
            }
        });

        item_topping7_minus.setOnClickListener(v ->{

            if (item_topping6Quantity > 0) {
                item_topping7Quantity--;
                item_topping7_quantity.setText(String.valueOf(item_topping7Quantity));
            }
        });


        item_topping8_plus.setOnClickListener(v ->{

            if (item_topping8Quantity >= 0) {
                item_topping8Quantity++;
                item_topping8_quantity.setText(String.valueOf(item_topping8Quantity));
            }
        });

        item_topping8_minus.setOnClickListener(v ->{

            if (item_topping8Quantity > 0) {
                item_topping8Quantity--;
                item_topping8_quantity.setText(String.valueOf(item_topping8Quantity));
            }
        });


        item_topping9_plus.setOnClickListener(v ->{

            if (item_topping9Quantity >= 0) {
                item_topping9Quantity++;
                item_topping9_quantity.setText(String.valueOf(item_topping9Quantity));
            }
        });

        item_topping9_minus.setOnClickListener(v ->{

            if (item_topping9Quantity > 0) {
                item_topping9Quantity--;
                item_topping9_quantity.setText(String.valueOf(item_topping9Quantity));
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