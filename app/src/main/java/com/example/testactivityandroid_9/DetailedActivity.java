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
    @BindView(R.id.btnBack)
    ImageView btnBack;
    @BindView(R.id.item_vetchina_plus)
    ImageView item_vetchina_plus;
    @BindView(R.id.item_vetchina_minus)
    ImageView item_vetchina_minus;
    @BindView(R.id.item_vetchina_quantity)
    TextView item_vetchina_quantity;
    @BindView(R.id.item_krab_plus)
    ImageView item_krab_plus;
    @BindView(R.id.item_krab_minus)
    ImageView item_krab_minus;
    @BindView(R.id.item_krab_quantity)
    TextView item_krab_quantity;
    @BindView(R.id.item_bekon_plus)
    ImageView item_bekon_plus;
    @BindView(R.id.item_bekon_minus)
    ImageView item_bekon_minus;
    @BindView(R.id.item_bekon_quantity)
    TextView item_bekon_quantity;
    @BindView(R.id.item_krevetka_plus)
    ImageView item_krevetka_plus;
    @BindView(R.id.item_krevetka_minus)
    ImageView item_krevetka_minus;
    @BindView(R.id.item_krevetka_quantity)
    TextView item_krevetka_quantity;
    @BindView(R.id.btnAddToCart_Detailed)
    Button btnAddToCart_Detailed;
    @BindView(R.id.itemSizeRadioButton)
    RadioGroup itemSizeRadioButton;

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

        getItemsFromFirebase();

        ToppingsCount();

    }

    private void ToppingsCount() {
        item_vetchina_plus.setOnClickListener(v ->{

            if (vetchinaQuantity >= 0) {
                vetchinaQuantity++;
                item_vetchina_quantity.setText(String.valueOf(vetchinaQuantity));
            }
        });

        item_vetchina_minus.setOnClickListener(v ->{

            if (vetchinaQuantity > 0) {
                vetchinaQuantity--;
                item_vetchina_quantity.setText(String.valueOf(vetchinaQuantity));
            }
        });

        item_krab_plus.setOnClickListener(v ->{

            if (krabQuantity >= 0) {
                krabQuantity++;
                item_krab_quantity.setText(String.valueOf(krabQuantity));
            }
        });

        item_krab_minus.setOnClickListener(v ->{

            if (krabQuantity > 0) {
                krabQuantity--;
                item_krab_quantity.setText(String.valueOf(krabQuantity));
            }
        });

        item_bekon_plus.setOnClickListener(v ->{

            if (bekonQuantity >= 0) {
                bekonQuantity++;
                item_bekon_quantity.setText(String.valueOf(bekonQuantity));
            }
        });

        item_bekon_minus.setOnClickListener(v ->{

            if (bekonQuantity > 0) {
                bekonQuantity--;
                item_bekon_quantity.setText(String.valueOf(bekonQuantity));
            }
        });

        item_krevetka_plus.setOnClickListener(v ->{

            if (krevetkaQuantity >= 0) {
                krevetkaQuantity++;
                item_krevetka_quantity.setText(String.valueOf(krevetkaQuantity));
            }
        });

        item_krevetka_minus.setOnClickListener(v ->{

            if (krevetkaQuantity > 0) {
                krevetkaQuantity--;
                item_krevetka_quantity.setText(String.valueOf(krevetkaQuantity));
            }
        });
    }

    private void getItemsFromFirebase() {
        final Object object = getIntent().getSerializableExtra("details");

        if (object instanceof PPpizzaModel) {
            pPpizzaModel = (PPpizzaModel) object;
        }

        if (pPpizzaModel != null) {
            Glide.with(getApplicationContext()).load(pPpizzaModel.getItem_image()).into(item_image);
            item_descr.setText(pPpizzaModel.getItem_details());
            item_name.setText(pPpizzaModel.getItem_name());

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
                                                    .document(pPpizzaModel.getKey())
                                                    .update(updateData)
                                                    .addOnSuccessListener(aVoid -> {
                                                        cartLoadListener.OnCartloadFailed("Добавлено");
                                                        finish();
                                                    })
                                                    .addOnFailureListener(e -> cartLoadListener.OnCartloadFailed(e.getMessage()));

                                        }
                                        else // если в корзине нет предмета, то добавить новый
                                        {
                                            CartModel cartModel = new CartModel();
                                            cartModel.setItem_name(pPpizzaModel.getItem_name());
                                            cartModel.setItem_image(pPpizzaModel.getItem_image());
                                            cartModel.setItem_details(pPpizzaModel.getItem_details());
                                            cartModel.setKey(pPpizzaModel.getKey());
                                            cartModel.setItem_cost(pPpizzaModel.getItem_cost());
                                            cartModel.setQuantity(1);
                                            cartModel.setTotalPrice(pPpizzaModel.getItem_cost());
                                            cartModel.setId(pPpizzaModel.getId());

                                            Map<String,Object> dopSizeMap = new HashMap<>();

                                            if (itemSizeRadioButton.getCheckedRadioButtonId() == R.id.radioButton1) {
                                                dopSizeMap.put("Доп размер", "32 см");
                                            } else if (itemSizeRadioButton.getCheckedRadioButtonId() == R.id.radioButton2) {
                                                dopSizeMap.put("Доп размер", "40 см");
                                            } else {
                                                dopSizeMap.put("Доп размер", "-");
                                            }

                                            Map<String,Object> toppingsMap = new HashMap<>();
                                            toppingsMap.put("Доп Ветчина", vetchinaQuantity);
                                            toppingsMap.put("Доп Краб", krabQuantity);
                                            toppingsMap.put("Доп Бек", bekonQuantity);
                                            toppingsMap.put("Доп Краб", krevetkaQuantity);

                                            FirebaseFirestore.getInstance()
                                                    .collection("Users_Cart")
                                                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                    .collection("Корзина")
                                                    .document(pPpizzaModel.getKey())
                                                    .set(cartModel)
                                                    .addOnSuccessListener(aVoid -> {

                                                        FirebaseFirestore.getInstance()
                                                                .collection("Users_Cart")
                                                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                .collection("Корзина")
                                                                .document(pPpizzaModel.getKey())
                                                                .update(dopSizeMap);

                                                        FirebaseFirestore.getInstance()
                                                                .collection("Users_Cart")
                                                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                .collection("Корзина")
                                                                .document(pPpizzaModel.getKey())
                                                                .update(toppingsMap);

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

        }


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


        if (object instanceof DjoModel) {
            djoModel = (DjoModel) object;
        }

        if (djoModel != null) {
            Glide.with(getApplicationContext()).load(djoModel.getItem_image()).into(item_image);
            item_descr.setText(djoModel.getItem_details());
            item_name.setText(djoModel.getItem_name());
        }
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