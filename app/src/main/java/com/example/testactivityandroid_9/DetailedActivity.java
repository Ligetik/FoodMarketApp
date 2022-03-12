package com.example.testactivityandroid_9;


import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
import android.widget.LinearLayout.LayoutParams;
import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
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
    @BindView(R.id.item_topping10_plus)
    ImageView item_topping10_plus;
    @BindView(R.id.item_topping10_minus)
    ImageView item_topping10_minus;
    @BindView(R.id.item_topping10_quantity)
    TextView item_topping10_quantity;
    @BindView(R.id.item_topping11_plus)
    ImageView item_topping11_plus;
    @BindView(R.id.item_topping11_minus)
    ImageView item_topping11_minus;
    @BindView(R.id.item_topping11_quantity)
    TextView item_topping11_quantity;
    @BindView(R.id.item_topping12_plus)
    ImageView item_topping12_plus;
    @BindView(R.id.item_topping12_minus)
    ImageView item_topping12_minus;
    @BindView(R.id.item_topping12_quantity)
    TextView item_topping12_quantity;
    @BindView(R.id.item_topping13_plus)
    ImageView item_topping13_plus;
    @BindView(R.id.item_topping13_minus)
    ImageView item_topping13_minus;
    @BindView(R.id.item_topping13_quantity)
    TextView item_topping13_quantity;
    @BindView(R.id.item_topping14_plus)
    ImageView item_topping14_plus;
    @BindView(R.id.item_topping14_minus)
    ImageView item_topping14_minus;
    @BindView(R.id.item_topping14_quantity)
    TextView item_topping14_quantity;
    @BindView(R.id.item_topping15_plus)
    ImageView item_topping15_plus;
    @BindView(R.id.item_topping15_minus)
    ImageView item_topping15_minus;
    @BindView(R.id.item_topping15_quantity)
    TextView item_topping15_quantity;
    @BindView(R.id.item_topping16_plus)
    ImageView item_topping16_plus;
    @BindView(R.id.item_topping16_minus)
    ImageView item_topping16_minus;
    @BindView(R.id.item_topping16_quantity)
    TextView item_topping16_quantity;
    @BindView(R.id.item_topping17_plus)
    ImageView item_topping17_plus;
    @BindView(R.id.item_topping17_minus)
    ImageView item_topping17_minus;
    @BindView(R.id.item_topping17_quantity)
    TextView item_topping17_quantity;
    @BindView(R.id.item_topping18_plus)
    ImageView item_topping18_plus;
    @BindView(R.id.item_topping18_minus)
    ImageView item_topping18_minus;
    @BindView(R.id.item_topping18_quantity)
    TextView item_topping18_quantity;
    @BindView(R.id.item_topping19_plus)
    ImageView item_topping19_plus;
    @BindView(R.id.item_topping19_minus)
    ImageView item_topping19_minus;
    @BindView(R.id.item_topping19_quantity)
    TextView item_topping19_quantity;
    @BindView(R.id.item_topping20_plus)
    ImageView item_topping20_plus;
    @BindView(R.id.item_topping20_minus)
    ImageView item_topping20_minus;
    @BindView(R.id.item_topping20_quantity)
    TextView item_topping20_quantity;
    @BindView(R.id.item_topping21_plus)
    ImageView item_topping21_plus;
    @BindView(R.id.item_topping21_minus)
    ImageView item_topping21_minus;
    @BindView(R.id.item_topping21_quantity)
    TextView item_topping21_quantity;
    @BindView(R.id.btnAddToCart_Detailed)
    Button btnAddToCart_Detailed;
    @BindView(R.id.itemSizeRadioButton)
    RadioGroup itemSizeRadioButton;
    @BindView(R.id.radioButton1)
    RadioButton radioButton1;
    @BindView(R.id.radioButton2)
    RadioButton radioButton2;
    @BindView(R.id.radioButton3)
    RadioButton radioButton3;
    @BindView(R.id.radioButton4)
    RadioButton radioButton4;
    @BindView(R.id.radioButton5)
    RadioButton radioButton5;
    @BindView(R.id.radioButton6)
    RadioButton radioButton6;
    @BindView(R.id.radioButton7)
    RadioButton radioButton7;
    @BindView(R.id.radioButton8)
    RadioButton radioButton8;
    @BindView(R.id.radioButton9)
    RadioButton radioButton9;
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
    @BindView(R.id.item_topping10)
    ConstraintLayout item_topping10;
    @BindView(R.id.item_topping11)
    ConstraintLayout item_topping11;
    @BindView(R.id.item_topping12)
    ConstraintLayout item_topping12;
    @BindView(R.id.item_topping13)
    ConstraintLayout item_topping13;
    @BindView(R.id.item_topping14)
    ConstraintLayout item_topping14;
    @BindView(R.id.item_topping15)
    ConstraintLayout item_topping15;
    @BindView(R.id.item_topping16)
    ConstraintLayout item_topping16;
    @BindView(R.id.item_topping17)
    ConstraintLayout item_topping17;
    @BindView(R.id.item_topping18)
    ConstraintLayout item_topping18;
    @BindView(R.id.item_topping19)
    ConstraintLayout item_topping19;
    @BindView(R.id.item_topping20)
    ConstraintLayout item_topping20;
    @BindView(R.id.item_topping21)
    ConstraintLayout item_topping21;
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
    @BindView(R.id.item_topping10_cost)
    TextView item_topping10_cost;
    @BindView(R.id.item_topping11_cost)
    TextView item_topping11_cost;
    @BindView(R.id.item_topping12_cost)
    TextView item_topping12_cost;
    @BindView(R.id.item_topping13_cost)
    TextView item_topping13_cost;
    @BindView(R.id.item_topping14_cost)
    TextView item_topping14_cost;
    @BindView(R.id.item_topping15_cost)
    TextView item_topping15_cost;
    @BindView(R.id.item_topping16_cost)
    TextView item_topping16_cost;
    @BindView(R.id.item_topping17_cost)
    TextView item_topping17_cost;
    @BindView(R.id.item_topping18_cost)
    TextView item_topping18_cost;
    @BindView(R.id.item_topping19_cost)
    TextView item_topping19_cost;
    @BindView(R.id.item_topping20_cost)
    TextView item_topping20_cost;
    @BindView(R.id.item_topping21_cost)
    TextView item_topping21_cost;
    @BindView(R.id.item_topping1_name)
    TextView item_topping1_name;
    @BindView(R.id.item_topping2_name)
    TextView item_topping2_name;
    @BindView(R.id.item_topping3_name)
    TextView item_topping3_name;
    @BindView(R.id.item_topping4_name)
    TextView item_topping4_name;
    @BindView(R.id.item_topping5_name)
    TextView item_topping5_name;
    @BindView(R.id.item_topping6_name)
    TextView item_topping6_name;
    @BindView(R.id.item_topping7_name)
    TextView item_topping7_name;
    @BindView(R.id.item_topping8_name)
    TextView item_topping8_name;
    @BindView(R.id.item_topping9_name)
    TextView item_topping9_name;
    @BindView(R.id.item_topping10_name)
    TextView item_topping10_name;
    @BindView(R.id.item_topping11_name)
    TextView item_topping11_name;
    @BindView(R.id.item_topping12_name)
    TextView item_topping12_name;
    @BindView(R.id.item_topping13_name)
    TextView item_topping13_name;
    @BindView(R.id.item_topping14_name)
    TextView item_topping14_name;
    @BindView(R.id.item_topping15_name)
    TextView item_topping15_name;
    @BindView(R.id.item_topping16_name)
    TextView item_topping16_name;
    @BindView(R.id.item_topping17_name)
    TextView item_topping17_name;
    @BindView(R.id.item_topping18_name)
    TextView item_topping18_name;
    @BindView(R.id.item_topping19_name)
    TextView item_topping19_name;
    @BindView(R.id.item_topping20_name)
    TextView item_topping20_name;
    @BindView(R.id.item_topping21_name)
    TextView item_topping21_name;
    @BindView(R.id.item_cost)
    TextView item_cost;
    @BindView(R.id.orderSelectSize)
    ConstraintLayout orderSelectSize;

    ICartLoadListener cartLoadListener;

    PPpizzaModel pPpizzaModel = null;
    AvocadoModel avocadoModel = null;
    DjoModel djoModel = null;

    int item_topping1Quantity = 0, item_topping2Quantity = 0, item_topping3Quantity = 0,
            item_topping4Quantity = 0, item_topping5Quantity = 0, item_topping6Quantity = 0,
            item_topping7Quantity = 0, item_topping8Quantity = 0, item_topping9Quantity = 0,
            item_topping10Quantity = 0, item_topping11Quantity = 0, item_topping12Quantity = 0,
            item_topping13Quantity = 0, item_topping14Quantity = 0, item_topping15Quantity = 0,
            item_topping16Quantity = 0, item_topping17Quantity = 0, item_topping18Quantity = 0,
            item_topping19Quantity = 0, item_topping20Quantity = 0, item_topping21Quantity = 0;

    FrameLayout.LayoutParams params = getLayoutParams();

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
                        case R.id.radioButton3:
                            item_cost.setText(pPpizzaModel.getItem_cost_var2() + " ₽");
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
                    item_topping10.setVisibility(View.VISIBLE);
                    item_topping11.setVisibility(View.VISIBLE);
                    item_topping12.setVisibility(View.VISIBLE);
                    item_topping13.setVisibility(View.VISIBLE);
                    item_topping14.setVisibility(View.VISIBLE);
                    item_topping15.setVisibility(View.VISIBLE);
                    item_topping16.setVisibility(View.VISIBLE);
                    item_topping17.setVisibility(View.VISIBLE);
                    item_topping18.setVisibility(View.VISIBLE);
                    item_topping19.setVisibility(View.VISIBLE);
                    item_topping20.setVisibility(View.VISIBLE);
                    item_topping21.setVisibility(View.VISIBLE);

                    if (pPpizzaModel.getItem_name().equals("Фрутти")) {
                        orderSelectSize.setVisibility(View.GONE);
                        item_topping1.setVisibility(View.GONE);
                        item_topping2.setVisibility(View.GONE);
                        item_topping3.setVisibility(View.GONE);
                        item_topping4.setVisibility(View.GONE);
                        item_topping5.setVisibility(View.GONE);
                        item_topping6.setVisibility(View.GONE);
                        item_topping7.setVisibility(View.GONE);
                        item_topping8.setVisibility(View.GONE);
                        item_topping9.setVisibility(View.GONE);
                        item_topping10.setVisibility(View.GONE);
                        item_topping11.setVisibility(View.GONE);
                        item_topping12.setVisibility(View.GONE);
                        item_topping13.setVisibility(View.GONE);
                        item_topping14.setVisibility(View.GONE);
                        item_topping15.setVisibility(View.GONE);
                        item_topping16.setVisibility(View.GONE);
                        item_topping17.setVisibility(View.GONE);
                        item_topping18.setVisibility(View.GONE);
                        item_topping19.setVisibility(View.GONE);
                        item_topping20.setVisibility(View.GONE);
                        item_topping21.setVisibility(View.GONE);
                    }


                    final int VETCHINA_PIZZA_PRICE = 60;
                    item_topping1_cost.setText(VETCHINA_PIZZA_PRICE + " ₽");
                    item_topping1_name.setText("Ветчина");

                    final int KRAB_PIZZA_PRICE = 80;
                    item_topping2_cost.setText(KRAB_PIZZA_PRICE + " ₽");
                    item_topping2_name.setText("Краб");

                    final int BEKON_PIZZA_PRICE = 60;
                    item_topping3_cost.setText(BEKON_PIZZA_PRICE + " ₽");
                    item_topping3_name.setText("Бекон");

                    final int KREVETKA_PIZZA_PRICE = 100;
                    item_topping4_cost.setText(KREVETKA_PIZZA_PRICE + " ₽");
                    item_topping4_name.setText("Креветка");

                    final int KURICA_PIZZA_PRICE = 50;
                    item_topping5_cost.setText(KURICA_PIZZA_PRICE + " ₽");
                    item_topping5_name.setText("Куриное филе");

                    final int OSTRKURICA_PIZZA_PRICE = 50;
                    item_topping6_cost.setText(OSTRKURICA_PIZZA_PRICE + " ₽");
                    item_topping6_name.setText("Маринованная курица (острая)");

                    final int KOLBASKI_PIZZA_PRICE = 60;
                    item_topping7_cost.setText(KOLBASKI_PIZZA_PRICE + " ₽");
                    item_topping7_name.setText("Охотничье колбаски");

                    final int PEPPERONI_PIZZA_PRICE = 50;
                    item_topping8_cost.setText(PEPPERONI_PIZZA_PRICE + " ₽");
                    item_topping8_name.setText("Пепперони");

                    final int PEREC_PIZZA_PRICE = 30;
                    item_topping9_cost.setText(PEREC_PIZZA_PRICE + " ₽");
                    item_topping9_name.setText("Перец острый");

                    final int HOLLAND_PIZZA_PRICE = 40;
                    item_topping10_cost.setText(HOLLAND_PIZZA_PRICE + " ₽");
                    item_topping10_name.setText("Сыр Голландский");

                    final int SLIV_PIZZA_PRICE = 50;
                    item_topping11_cost.setText(SLIV_PIZZA_PRICE + " ₽");
                    item_topping11_name.setText("Сыр сливочный");

                    final int MOCARELLA_PIZZA_PRICE = 60;
                    item_topping12_cost.setText(MOCARELLA_PIZZA_PRICE + " ₽");
                    item_topping12_name.setText("Сыр Моцарелла");

                    final int PARMEZAN_PIZZA_PRICE = 45;
                    item_topping13_cost.setText(PARMEZAN_PIZZA_PRICE + " ₽");
                    item_topping13_name.setText("Сыр Пармезан");

                    final int FARSH_PIZZA_PRICE = 50;
                    item_topping14_cost.setText(FARSH_PIZZA_PRICE + " ₽");
                    item_topping14_name.setText("Фарш");

                    final int FRI_PIZZA_PRICE = 30;
                    item_topping15_cost.setText(FRI_PIZZA_PRICE + " ₽");
                    item_topping15_name.setText("Картофель фри");

                    final int CHERRY_PIZZA_PRICE = 30;
                    item_topping16_cost.setText(CHERRY_PIZZA_PRICE + " ₽");
                    item_topping16_name.setText("Помидоры Черри");

                    final int GRIBI_PIZZA_PRICE = 50;
                    item_topping17_cost.setText(CHERRY_PIZZA_PRICE + " ₽");
                    item_topping17_name.setText("Грибы Шампиньоны");

                    final int GREBESHOK_PIZZA_PRICE = 110;
                    item_topping18_cost.setText(GREBESHOK_PIZZA_PRICE + " ₽");
                    item_topping18_name.setText("Гребешок");

                    final int KALMAR_PIZZA_PRICE = 50;
                    item_topping19_cost.setText(KALMAR_PIZZA_PRICE + " ₽");
                    item_topping19_name.setText("Кальмар");

                    final int ANANASI_PIZZA_PRICE = 30;
                    item_topping20_cost.setText(ANANASI_PIZZA_PRICE + " ₽");
                    item_topping20_name.setText("Ананасы");

                    final int TUNEC_PIZZA_PRICE = 60;
                    item_topping21_cost.setText(TUNEC_PIZZA_PRICE + " ₽");
                    item_topping21_name.setText("Тунец");

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

/*                                                if (documentSnapshot.exists()) //если у пользователя уже есть товар в корзине
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

                                                } else { // если в корзине нет предмета, то добавить новый*/

                                                    int toppingPizza =
                                                        (VETCHINA_PIZZA_PRICE * item_topping1Quantity) +
                                                        (KRAB_PIZZA_PRICE * item_topping2Quantity) +
                                                        (BEKON_PIZZA_PRICE * item_topping3Quantity) +
                                                        (KREVETKA_PIZZA_PRICE * item_topping4Quantity) +
                                                        (KURICA_PIZZA_PRICE * item_topping5Quantity) +
                                                        (OSTRKURICA_PIZZA_PRICE * item_topping6Quantity) +
                                                        (KOLBASKI_PIZZA_PRICE * item_topping7Quantity) +
                                                        (PEPPERONI_PIZZA_PRICE * item_topping8Quantity) +
                                                        (PEREC_PIZZA_PRICE * item_topping9Quantity) +
                                                        (HOLLAND_PIZZA_PRICE * item_topping10Quantity) +
                                                        (SLIV_PIZZA_PRICE * item_topping11Quantity) +
                                                        (MOCARELLA_PIZZA_PRICE * item_topping12Quantity) +
                                                        (PARMEZAN_PIZZA_PRICE * item_topping13Quantity) +
                                                        (FARSH_PIZZA_PRICE * item_topping14Quantity) +
                                                        (FRI_PIZZA_PRICE * item_topping15Quantity) +
                                                        (CHERRY_PIZZA_PRICE * item_topping16Quantity)  +
                                                        (GRIBI_PIZZA_PRICE * item_topping17Quantity) +
                                                        (GREBESHOK_PIZZA_PRICE * item_topping18Quantity) +
                                                        (KALMAR_PIZZA_PRICE * item_topping19Quantity) +
                                                        (ANANASI_PIZZA_PRICE * item_topping20Quantity) +
                                                        (TUNEC_PIZZA_PRICE * item_topping21Quantity);

                                                    Map<String, Object> toppingsMap = new HashMap<>();

                                                    if (item_topping1Quantity > 0 ) {
                                                        toppingsMap.put("Доп Ветчина", item_topping1Quantity);
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
                                                    if (item_topping10Quantity > 0) {
                                                        toppingsMap.put("Доп Сыр Голландский", item_topping10Quantity);
                                                    }
                                                    if (item_topping11Quantity > 0 ) {
                                                        toppingsMap.put("Доп Сыр сливочный", item_topping11Quantity);
                                                    }
                                                    if (item_topping12Quantity > 0) {
                                                        toppingsMap.put("Доп Сыр Моцарелла", item_topping12Quantity);
                                                    }
                                                    if (item_topping13Quantity > 0) {
                                                        toppingsMap.put("Доп Сыр Пармезан", item_topping13Quantity);
                                                    }
                                                    if (item_topping14Quantity > 0) {
                                                        toppingsMap.put("Доп Фарш", item_topping14Quantity);
                                                    }
                                                    if (item_topping15Quantity > 0) {
                                                        toppingsMap.put("Доп Картофель Фри", item_topping15Quantity);
                                                    }
                                                    if (item_topping16Quantity > 0) {
                                                        toppingsMap.put("Доп Помидоры Черри", item_topping16Quantity);
                                                    }
                                                    if (item_topping17Quantity > 0) {
                                                        toppingsMap.put("Доп Грибы Шампиньоны", item_topping17Quantity);
                                                    }
                                                    if (item_topping18Quantity > 0) {
                                                        toppingsMap.put("Доп Гребешок", item_topping18Quantity);
                                                    }
                                                    if (item_topping19Quantity > 0) {
                                                        toppingsMap.put("Доп Кальмар", item_topping19Quantity);
                                                    }
                                                    if (item_topping20Quantity > 0) {
                                                        toppingsMap.put("Доп Ананасы", item_topping20Quantity);
                                                    }
                                                    if (item_topping21Quantity > 0) {
                                                        toppingsMap.put("Доп Тунец", item_topping21Quantity);
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

                                                       /* Map<String, Object> dopSizeMap = new HashMap<>();*/

                                                        if (itemSizeRadioButton.getCheckedRadioButtonId() == R.id.radioButton1) {
                                                            /*dopSizeMap.put("Доп размер", "32 см");*/

                                                            cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                            cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);
                                                            cartModel.setВариант_блюда("32 см");

                                                            FirebaseFirestore.getInstance()
                                                                    .collection("Users_Cart")
                                                                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                    .collection("Корзина")
                                                                    .document(id)
                                                                    .set(cartModel)
                                                                    .addOnSuccessListener(aVoid -> {
                                                                        cartLoadListener.OnCartloadFailed("Добавлено");
                                                                        finish();
                                                                    });

                                                        } else if (itemSizeRadioButton.getCheckedRadioButtonId() == R.id.radioButton2) {
                                                           /* dopSizeMap.put("Вариант_блюда", "40 см");*/

                                                            cartModel.setItem_cost(pPpizzaModel.getItem_cost_var1() + toppingPizza);
                                                            cartModel.setTotalPrice(pPpizzaModel.getItem_cost_var1() + toppingPizza);
                                                            cartModel.setВариант_блюда("40 см");

                                                            FirebaseFirestore.getInstance()
                                                                    .collection("Users_Cart")
                                                                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                    .collection("Корзина")
                                                                    .document(id)
                                                                    .set(cartModel)
                                                                    .addOnSuccessListener(aVoid -> {

                                         /*                               FirebaseFirestore.getInstance()
                                                                                .collection("Users_Cart")
                                                                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                                .collection("Корзина")
                                                                                .document(id)
                                                                                .update(dopSizeMap);
*/
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
                                                        /*}*/
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

                case "Десерты":
                case "Роллы":
                    orderSelectSize.setVisibility(View.GONE);

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

                                                    CartModel cartModel = new CartModel();
                                                    cartModel.setItem_name(pPpizzaModel.getItem_name());
                                                    cartModel.setItem_image(pPpizzaModel.getItem_image());
                                                    cartModel.setItem_details(pPpizzaModel.getItem_details());
                                                    cartModel.setKey(pPpizzaModel.getKey());
                                                    cartModel.setItem_cost(pPpizzaModel.getItem_cost());
                                                    cartModel.setQuantity(1);
                                                    cartModel.setTotalPrice(pPpizzaModel.getItem_cost());
                                                    cartModel.setId(pPpizzaModel.getId());
                                                    cartModel.setItem_category(pPpizzaModel.getItem_category());

                                                    FirebaseFirestore.getInstance()
                                                            .collection("Users_Cart")
                                                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                            .collection("Корзина")
                                                            .document(pPpizzaModel.getKey())
                                                            .set(cartModel)
                                                            .addOnSuccessListener(aVoid -> {

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

                case "Сэндвичи":

                    orderSelectSize.setVisibility(View.GONE);

                    item_topping1.setVisibility(View.VISIBLE);
                    item_topping2.setVisibility(View.VISIBLE);
                    item_topping3.setVisibility(View.VISIBLE);
                    item_topping4.setVisibility(View.VISIBLE);
                    item_topping5.setVisibility(View.VISIBLE);


                    final int KRAB_SANDWICH_PRICE = 40;
                    item_topping1_cost.setText(KRAB_SANDWICH_PRICE + " ₽");
                    item_topping1_name.setText("Краб");

                    final int VETCHINA_SANDWICH_PRICE = 30;
                    item_topping2_cost.setText(VETCHINA_SANDWICH_PRICE + " ₽");
                    item_topping2_name.setText("Ветчина");

                    final int NERKA_SANDWICH_PRICE = 50;
                    item_topping3_cost.setText(NERKA_SANDWICH_PRICE + " ₽");
                    item_topping3_name.setText("Нерка");

                    final int HOHLAND_SANDWICH_PRICE = 20;
                    item_topping4_cost.setText(HOHLAND_SANDWICH_PRICE + " ₽");
                    item_topping4_name.setText("Сыр хохланд");

                    final int BUZHENINA_SANDWICH_PRICE = 30;
                    item_topping5_cost.setText(BUZHENINA_SANDWICH_PRICE + " ₽");
                    item_topping5_name.setText("Буженина");

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

                                                int toppingPizza =
                                                                (KRAB_SANDWICH_PRICE * item_topping1Quantity) +
                                                                (VETCHINA_SANDWICH_PRICE * item_topping2Quantity) +
                                                                (NERKA_SANDWICH_PRICE * item_topping3Quantity) +
                                                                (HOHLAND_SANDWICH_PRICE * item_topping4Quantity) +
                                                                (BUZHENINA_SANDWICH_PRICE * item_topping5Quantity);

                                                Map<String, Object> toppingsMap = new HashMap<>();

                                                if (item_topping1Quantity > 0 ) {
                                                    toppingsMap.put("Доп Краб", item_topping1Quantity);
                                                }
                                                if (item_topping2Quantity > 0) {
                                                    toppingsMap.put("Доп Ветчина", item_topping2Quantity);
                                                }
                                                if (item_topping3Quantity > 0) {
                                                    toppingsMap.put("Доп Нерка", item_topping3Quantity);
                                                }
                                                if (item_topping4Quantity > 0) {
                                                    toppingsMap.put("Доп Сыр хохланд", item_topping4Quantity);
                                                }
                                                if (item_topping5Quantity > 0) {
                                                    toppingsMap.put("Доп Буженина", item_topping5Quantity);
                                                }

                                                CartModel cartModel = new CartModel();
                                                cartModel.setItem_name(pPpizzaModel.getItem_name());
                                                cartModel.setItem_image(pPpizzaModel.getItem_image());
                                                cartModel.setItem_details(pPpizzaModel.getItem_details());
                                                cartModel.setKey(pPpizzaModel.getKey());
                                                cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                cartModel.setQuantity(1);
                                                cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);
                                                cartModel.setId(pPpizzaModel.getId());
                                                cartModel.setДопы(toppingsMap);
                                                cartModel.setItem_category(pPpizzaModel.getItem_category());

                                                cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                            }

                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });

                    break;

                case "Салаты":

                    orderSelectSize.setVisibility(View.GONE);

                    item_topping1.setVisibility(View.VISIBLE);
                    item_topping2.setVisibility(View.VISIBLE);
                    item_topping3.setVisibility(View.VISIBLE);
                    item_topping4.setVisibility(View.VISIBLE);
                    item_topping5.setVisibility(View.VISIBLE);
                    item_topping6.setVisibility(View.VISIBLE);
                    item_topping7.setVisibility(View.VISIBLE);
                    item_topping8.setVisibility(View.VISIBLE);
                    item_topping9.setVisibility(View.VISIBLE);
                    item_topping10.setVisibility(View.VISIBLE);
                    item_topping11.setVisibility(View.VISIBLE);
                    item_topping12.setVisibility(View.VISIBLE);
                    item_topping13.setVisibility(View.VISIBLE);
                    item_topping14.setVisibility(View.VISIBLE);


                    final int VETCHINA_SALAT_PRICE = 30;
                    item_topping1_cost.setText(VETCHINA_SALAT_PRICE + " ₽");
                    item_topping1_name.setText("Ветчина");

                    final int KRAB_SALAT_PRICE = 40;
                    item_topping2_cost.setText(KRAB_SALAT_PRICE + " ₽");
                    item_topping2_name.setText("Краб");

                    final int KREMMETA_SALAT_PRICE = 30;
                    item_topping3_cost.setText(KREMMETA_SALAT_PRICE + " ₽");
                    item_topping3_name.setText("Креммета");

                    final int KUKURUZA_SALAT_PRICE = 20;
                    item_topping4_cost.setText(KUKURUZA_SALAT_PRICE + " ₽");
                    item_topping4_name.setText("Кукуруза");

                    final int FONARIK_SALAT_PRICE = 30;
                    item_topping5_cost.setText(FONARIK_SALAT_PRICE + " ₽");
                    item_topping5_name.setText("Куриный фонарик");

                    final int KURICA_SALAT_PRICE = 30;
                    item_topping6_cost.setText(KURICA_SALAT_PRICE + " ₽");
                    item_topping6_name.setText("Куриное филе");

                    final int NERKA_SALAT_PRICE = 50;
                    item_topping7_cost.setText(NERKA_SALAT_PRICE + " ₽");
                    item_topping7_name.setText("Нерка");

                    final int PARMEZAN_SALAT_PRICE = 45;
                    item_topping8_cost.setText(PARMEZAN_SALAT_PRICE + " ₽");
                    item_topping8_name.setText("Сыр Пармезан");

                    final int TUNEC_SALAT_PRICE = 30;
                    item_topping9_cost.setText(TUNEC_SALAT_PRICE + " ₽");
                    item_topping9_name.setText("Тунец");

                    final int FETAKSA_SALAT_PRICE = 20;
                    item_topping10_cost.setText(FETAKSA_SALAT_PRICE + " ₽");
                    item_topping10_name.setText("Сыр Фетакса");

                    final int CHERRY_SALAT_PRICE = 15;
                    item_topping11_cost.setText(CHERRY_SALAT_PRICE + " ₽");
                    item_topping11_name.setText("Помидоры Черри");

                    final int KOLBASKI_SALAT_PRICE = 40;
                    item_topping12_cost.setText(KOLBASKI_SALAT_PRICE + " ₽");
                    item_topping12_name.setText("Охотничье колбаски");

                    final int KREVETKA_ASALAT_PRICE = 100;
                    item_topping13_cost.setText(KREVETKA_ASALAT_PRICE + " ₽");
                    item_topping13_name.setText("Креветка");

                    final int SUHARIKI_SALAT_PRICE = 10;
                    item_topping14_cost.setText(SUHARIKI_SALAT_PRICE + " ₽");
                    item_topping14_name.setText("Сухарики");

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

                                                int toppingPizza =
                                                        (VETCHINA_SALAT_PRICE * item_topping1Quantity) +
                                                        (KRAB_SALAT_PRICE * item_topping2Quantity) +
                                                        (KREMMETA_SALAT_PRICE * item_topping3Quantity) +
                                                        (KUKURUZA_SALAT_PRICE * item_topping4Quantity) +
                                                        (FONARIK_SALAT_PRICE * item_topping5Quantity) +
                                                        (KURICA_SALAT_PRICE * item_topping6Quantity) +
                                                        (NERKA_SALAT_PRICE * item_topping7Quantity) +
                                                        (PARMEZAN_SALAT_PRICE * item_topping8Quantity) +
                                                        (TUNEC_SALAT_PRICE * item_topping9Quantity) +
                                                        (FETAKSA_SALAT_PRICE * item_topping10Quantity) +
                                                        (CHERRY_SALAT_PRICE * item_topping11Quantity) +
                                                        (KOLBASKI_SALAT_PRICE * item_topping12Quantity) +
                                                        (KREVETKA_ASALAT_PRICE * item_topping13Quantity) +
                                                        (SUHARIKI_SALAT_PRICE * item_topping14Quantity);

                                                Map<String, Object> toppingsMap = new HashMap<>();

                                                if (item_topping1Quantity > 0 ) {
                                                    toppingsMap.put("Доп Ветчина", item_topping1Quantity);
                                                }
                                                if (item_topping2Quantity > 0) {
                                                    toppingsMap.put("Доп Краб", item_topping2Quantity);
                                                }
                                                if (item_topping3Quantity > 0) {
                                                    toppingsMap.put("Доп Креммета", item_topping3Quantity);
                                                }
                                                if (item_topping4Quantity > 0) {
                                                    toppingsMap.put("Доп Кукуруза", item_topping4Quantity);
                                                }
                                                if (item_topping5Quantity > 0) {
                                                    toppingsMap.put("Доп Куриный фонарик", item_topping5Quantity);
                                                }
                                                if (item_topping6Quantity > 0) {
                                                    toppingsMap.put("Доп Куриное филе", item_topping6Quantity);
                                                }
                                                if (item_topping7Quantity > 0) {
                                                    toppingsMap.put("Доп Нерка", item_topping7Quantity);
                                                }
                                                if (item_topping8Quantity > 0) {
                                                    toppingsMap.put("Доп Сыр Пармезан", item_topping8Quantity);
                                                }
                                                if (item_topping9Quantity > 0) {
                                                    toppingsMap.put("Доп Тунец", item_topping9Quantity);
                                                }
                                                if (item_topping10Quantity > 0) {
                                                    toppingsMap.put("Доп Сыр Фетакса", item_topping10Quantity);
                                                }
                                                if (item_topping11Quantity > 0 ) {
                                                    toppingsMap.put("Доп Помидоры Черри", item_topping11Quantity);
                                                }
                                                if (item_topping12Quantity > 0) {
                                                    toppingsMap.put("Доп Охотничье колбаски", item_topping12Quantity);
                                                }
                                                if (item_topping13Quantity > 0) {
                                                    toppingsMap.put("Доп Креветка", item_topping13Quantity);
                                                }
                                                if (item_topping14Quantity > 0) {
                                                    toppingsMap.put("Доп Сухарики", item_topping14Quantity);
                                                }


                                                CartModel cartModel = new CartModel();
                                                cartModel.setItem_name(pPpizzaModel.getItem_name());
                                                cartModel.setItem_image(pPpizzaModel.getItem_image());
                                                cartModel.setItem_details(pPpizzaModel.getItem_details());
                                                cartModel.setKey(pPpizzaModel.getKey());
                                                cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                cartModel.setQuantity(1);
                                                cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);
                                                cartModel.setId(pPpizzaModel.getId());
                                                cartModel.setДопы(toppingsMap);
                                                cartModel.setItem_category(pPpizzaModel.getItem_category());

                                                cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                            }

                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });

                    break;

                case "Супы":

                    orderSelectSize.setVisibility(View.GONE);

                    item_topping1.setVisibility(View.VISIBLE);
                    item_topping2.setVisibility(View.VISIBLE);
                    item_topping3.setVisibility(View.VISIBLE);
                    item_topping4.setVisibility(View.VISIBLE);
                    item_topping5.setVisibility(View.VISIBLE);
                    item_topping6.setVisibility(View.VISIBLE);
                    item_topping7.setVisibility(View.VISIBLE);


                    final int GRIBI_SUP_PRICE = 30;
                    item_topping1_cost.setText(GRIBI_SUP_PRICE + " ₽");
                    item_topping1_name.setText("Ветчина");

                    final int BEKON_SUP_PRICE = 40;
                    item_topping2_cost.setText(BEKON_SUP_PRICE + " ₽");
                    item_topping2_name.setText("Краб");

                    final int KURICA_SUP_PRICE = 30;
                    item_topping3_cost.setText(KURICA_SUP_PRICE + " ₽");
                    item_topping3_name.setText("Куриное филе");

                    final int KOLBASKI_SUP_PRICE = 40;
                    item_topping4_cost.setText(KOLBASKI_SUP_PRICE + " ₽");
                    item_topping4_name.setText("Охотничье колбаски");

                    final int PEPPERONI_SUP_PRICE = 30;
                    item_topping5_cost.setText(PEPPERONI_SUP_PRICE + " ₽");
                    item_topping5_name.setText("Пепперони");

                    final int SUHARIKI_SUP_PRICE = 10;
                    item_topping6_cost.setText(SUHARIKI_SUP_PRICE + " ₽");
                    item_topping6_name.setText("Сухарики");

                    final int PARMEZAN_SUP_PRICE = 45;
                    item_topping7_cost.setText(PARMEZAN_SUP_PRICE + " ₽");
                    item_topping7_name.setText("Сыр Пармезан");


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

                                                int toppingPizza =
                                                        (GRIBI_SUP_PRICE * item_topping1Quantity) +
                                                        (BEKON_SUP_PRICE * item_topping2Quantity) +
                                                        (KURICA_SUP_PRICE * item_topping3Quantity) +
                                                        (KOLBASKI_SUP_PRICE * item_topping4Quantity) +
                                                        (PEPPERONI_SUP_PRICE * item_topping5Quantity) +
                                                        (SUHARIKI_SUP_PRICE * item_topping6Quantity) +
                                                        (PARMEZAN_SUP_PRICE * item_topping7Quantity);

                                                Map<String, Object> toppingsMap = new HashMap<>();

                                                if (item_topping1Quantity > 0) {
                                                    toppingsMap.put("Доп Ветчина", item_topping1Quantity);
                                                }
                                                if (item_topping2Quantity > 0) {
                                                    toppingsMap.put("Доп Краб", item_topping2Quantity);
                                                }
                                                if (item_topping3Quantity > 0) {
                                                    toppingsMap.put("Доп Куриное филе", item_topping3Quantity);
                                                }
                                                if (item_topping4Quantity > 0) {
                                                    toppingsMap.put("Доп Охотничье колбаски", item_topping4Quantity);
                                                }
                                                if (item_topping5Quantity > 0) {
                                                    toppingsMap.put("Доп Пепперони", item_topping5Quantity);
                                                }
                                                if (item_topping6Quantity > 0) {
                                                    toppingsMap.put("Доп Сухарики", item_topping6Quantity);
                                                }
                                                if (item_topping7Quantity > 0) {
                                                    toppingsMap.put("Сыр Пармезан", item_topping7Quantity);
                                                }


                                                CartModel cartModel = new CartModel();
                                                cartModel.setItem_name(pPpizzaModel.getItem_name());
                                                cartModel.setItem_image(pPpizzaModel.getItem_image());
                                                cartModel.setItem_details(pPpizzaModel.getItem_details());
                                                cartModel.setKey(pPpizzaModel.getKey());
                                                cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                cartModel.setQuantity(1);
                                                cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);
                                                cartModel.setId(pPpizzaModel.getId());
                                                cartModel.setДопы(toppingsMap);
                                                cartModel.setItem_category(pPpizzaModel.getItem_category());

                                                cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                        }
                                    });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });

                    break;

                case "Бургеры":

                    final int HOHLAND_BURGER_PRICE = 20;

                    orderSelectSize.setVisibility(View.GONE);

                    item_topping1.setVisibility(View.VISIBLE);
                    item_topping2.setVisibility(View.VISIBLE);
                    item_topping3.setVisibility(View.VISIBLE);
                    item_topping4.setVisibility(View.VISIBLE);
                    item_topping5.setVisibility(View.VISIBLE);
                    item_topping6.setVisibility(View.VISIBLE);
                    item_topping7.setVisibility(View.VISIBLE);
                    item_topping8.setVisibility(View.VISIBLE);
                    item_topping9.setVisibility(View.VISIBLE);

                    if (pPpizzaModel.getItem_name().equals("Булочка с ветчиной") || pPpizzaModel.getItem_name().equals("Булочка с неркой")) {

                        item_topping1.setVisibility(View.VISIBLE);
                        item_topping2.setVisibility(View.VISIBLE);
                        item_topping3.setVisibility(View.VISIBLE);
                        item_topping4.setVisibility(View.GONE);
                        item_topping5.setVisibility(View.GONE);
                        item_topping6.setVisibility(View.GONE);
                        item_topping7.setVisibility(View.GONE);
                        item_topping8.setVisibility(View.GONE);
                        item_topping9.setVisibility(View.GONE);


                        final int NERKA_BURGER_PRICE = 50;
                        item_topping1_cost.setText(NERKA_BURGER_PRICE + " ₽");
                        item_topping1_name.setText("Нерка");

                        final int VETCHINA_BURGER_PRICE = 30;
                        item_topping2_cost.setText(VETCHINA_BURGER_PRICE + " ₽");
                        item_topping2_name.setText("Ветчина");

                        item_topping3_cost.setText(HOHLAND_BURGER_PRICE + " ₽");
                        item_topping3_name.setText("Сыр хохланд");


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

                                                    int toppingPizza =
                                                        (NERKA_BURGER_PRICE * item_topping1Quantity) +
                                                        (VETCHINA_BURGER_PRICE * item_topping2Quantity) +
                                                        (HOHLAND_BURGER_PRICE * item_topping3Quantity);

                                                    Map<String, Object> toppingsMap = new HashMap<>();

                                                    if (item_topping1Quantity > 0) {
                                                        toppingsMap.put("Доп Нерка", item_topping1Quantity);
                                                    }
                                                    if (item_topping2Quantity > 0) {
                                                        toppingsMap.put("Доп Ветчина", item_topping2Quantity);
                                                    }
                                                    if (item_topping3Quantity > 0) {
                                                        toppingsMap.put("Доп Сыр хохланд", item_topping3Quantity);
                                                    }


                                                    CartModel cartModel = new CartModel();
                                                    cartModel.setItem_name(pPpizzaModel.getItem_name());
                                                    cartModel.setItem_image(pPpizzaModel.getItem_image());
                                                    cartModel.setItem_details(pPpizzaModel.getItem_details());
                                                    cartModel.setKey(pPpizzaModel.getKey());
                                                    cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                    cartModel.setQuantity(1);
                                                    cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);
                                                    cartModel.setId(pPpizzaModel.getId());
                                                    cartModel.setДопы(toppingsMap);
                                                    cartModel.setItem_category(pPpizzaModel.getItem_category());

                                                 /*   cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                    cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);
*/
                                                    FirebaseFirestore.getInstance()
                                                            .collection("Users_Cart")
                                                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                            .collection("Корзина")
                                                            .document(id)
                                                            .set(cartModel)
                                                            .addOnSuccessListener(aVoid -> {
                                                                cartLoadListener.OnCartloadFailed("Добавлено");
                                                                finish();
                                                            });

                                                    EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                                }
                                            });
                                } catch (Exception e) {
                                    FirebaseCrashlytics.getInstance().recordException(e);
                                }
                            }
                        });
                    } else {


                        final int GOVYADINA_BURGER_PRICE = 135;
                        item_topping1_cost.setText(GOVYADINA_BURGER_PRICE + " ₽");
                        item_topping1_name.setText("Котлета говядина");

                        final int KURICA_BURGER_PRICE = 90;
                        item_topping2_cost.setText(KURICA_BURGER_PRICE + " ₽");
                        item_topping2_name.setText("Котлета куриная");

                        final int KREVETKA_BURGER_PRICE = 100;
                        item_topping3_cost.setText(KREVETKA_BURGER_PRICE + " ₽");
                        item_topping3_name.setText("Креветка");

                        final int BEKON_BURGER_PRICE = 40;
                        item_topping4_cost.setText(BEKON_BURGER_PRICE + " ₽");
                        item_topping4_name.setText("Бекон");

                        item_topping5_cost.setText(HOHLAND_BURGER_PRICE + " ₽");
                        item_topping5_name.setText("Сыр хохланд");

                        final int SIRNIY_BURGER_PRICE = 20;
                        item_topping6_cost.setText(SIRNIY_BURGER_PRICE + " ₽");
                        item_topping6_name.setText("Соус сырный");

                        final int BARBEKYU_BURGER_PRICE = 20;
                        item_topping7_cost.setText(BARBEKYU_BURGER_PRICE + " ₽");
                        item_topping7_name.setText("Соус барбекю");

                        final int GRIBI_BURGER_PRICE = 30;
                        item_topping8_cost.setText(GRIBI_BURGER_PRICE + " ₽");
                        item_topping8_name.setText("Грибы Шампиньоны");

                        final int YAYCO_BURGER_PRICE = 15;
                        item_topping9_cost.setText(YAYCO_BURGER_PRICE + " ₽");
                        item_topping9_name.setText("Яйцо");

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

                                                    int toppingPizza =
                                                            (GOVYADINA_BURGER_PRICE * item_topping1Quantity) +
                                                                    (KURICA_BURGER_PRICE * item_topping2Quantity) +
                                                                    (KREVETKA_BURGER_PRICE * item_topping3Quantity) +
                                                                    (BEKON_BURGER_PRICE * item_topping4Quantity) +
                                                                    (HOHLAND_BURGER_PRICE * item_topping5Quantity) +
                                                                    (SIRNIY_BURGER_PRICE * item_topping6Quantity) +
                                                                    (BARBEKYU_BURGER_PRICE * item_topping7Quantity) +
                                                                    (GRIBI_BURGER_PRICE * item_topping8Quantity) +
                                                                    (YAYCO_BURGER_PRICE * item_topping9Quantity);

                                                    Map<String, Object> toppingsMap = new HashMap<>();

                                                    if (item_topping1Quantity > 0) {
                                                        toppingsMap.put("Доп Котлета говядина", item_topping1Quantity);
                                                    }
                                                    if (item_topping2Quantity > 0) {
                                                        toppingsMap.put("Доп Котлета куриная", item_topping2Quantity);
                                                    }
                                                    if (item_topping3Quantity > 0) {
                                                        toppingsMap.put("Доп Креветка", item_topping3Quantity);
                                                    }
                                                    if (item_topping4Quantity > 0) {
                                                        toppingsMap.put("Доп Бекон", item_topping4Quantity);
                                                    }
                                                    if (item_topping5Quantity > 0) {
                                                        toppingsMap.put("Доп Сыр хохланд", item_topping5Quantity);
                                                    }
                                                    if (item_topping6Quantity > 0) {
                                                        toppingsMap.put("Доп Соус сырный", item_topping6Quantity);
                                                    }
                                                    if (item_topping7Quantity > 0) {
                                                        toppingsMap.put("Доп Соус барбекю", item_topping7Quantity);
                                                    }
                                                    if (item_topping8Quantity > 0) {
                                                        toppingsMap.put("Доп Грибы Шампиньоны", item_topping8Quantity);
                                                    }
                                                    if (item_topping9Quantity > 0) {
                                                        toppingsMap.put("Соус Яйцо", item_topping9Quantity);
                                                    }


                                                    CartModel cartModel = new CartModel();
                                                    cartModel.setItem_name(pPpizzaModel.getItem_name());
                                                    cartModel.setItem_image(pPpizzaModel.getItem_image());
                                                    cartModel.setItem_details(pPpizzaModel.getItem_details());
                                                    cartModel.setKey(pPpizzaModel.getKey());
                                                    cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                    cartModel.setQuantity(1);
                                                    cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);
                                                    cartModel.setId(pPpizzaModel.getId());
                                                    cartModel.setДопы(toppingsMap);
                                                    cartModel.setItem_category(pPpizzaModel.getItem_category());

                                                    cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                    cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);

                                                    FirebaseFirestore.getInstance()
                                                            .collection("Users_Cart")
                                                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                            .collection("Корзина")
                                                            .document(id)
                                                            .set(cartModel)
                                                            .addOnSuccessListener(aVoid -> {
                                                                cartLoadListener.OnCartloadFailed("Добавлено");
                                                                finish();
                                                            });

                                                    EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                                }
                                            });
                                } catch (Exception e) {
                                    FirebaseCrashlytics.getInstance().recordException(e);
                                }
                            }
                        });
                    }
                    break;

                case "Сеты":

                    orderSelectSize.setVisibility(View.GONE);

                    item_topping1.setVisibility(View.VISIBLE);
                    item_topping2.setVisibility(View.VISIBLE);
                    item_topping3.setVisibility(View.VISIBLE);
                    item_topping4.setVisibility(View.VISIBLE);
                    item_topping5.setVisibility(View.VISIBLE);
                    item_topping6.setVisibility(View.VISIBLE);


                    final int TOMAT_SET_PRICE = 10;
                    item_topping1_cost.setText(TOMAT_SET_PRICE + " ₽");
                    item_topping1_name.setText("Соус томатный");

                    final int SIR_SET_PRICE = 10;
                    item_topping2_cost.setText(SIR_SET_PRICE + " ₽");
                    item_topping2_name.setText("Соус сырный");

                    final int BARBEKYU_SET_PRICE = 10;
                    item_topping3_cost.setText(BARBEKYU_SET_PRICE + " ₽");
                    item_topping3_name.setText("Соус барбекю");

                    final int CHESNOK_SET_PRICE = 10;
                    item_topping4_cost.setText(CHESNOK_SET_PRICE + " ₽");
                    item_topping4_name.setText("Соус чесночный");

                    final int KISLSLAD_SET_PRICE = 10;
                    item_topping5_cost.setText(KISLSLAD_SET_PRICE + " ₽");
                    item_topping5_name.setText("Соус кисло-сладкий");

                    final int GORCHIZA_SET_PRICE = 10;
                    item_topping6_cost.setText(GORCHIZA_SET_PRICE + " ₽");
                    item_topping6_name.setText("Соус горчичный");



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

                                                int toppingPizza =
                                                        (TOMAT_SET_PRICE * item_topping1Quantity) +
                                                        (SIR_SET_PRICE * item_topping2Quantity) +
                                                        (BARBEKYU_SET_PRICE * item_topping3Quantity) +
                                                        (CHESNOK_SET_PRICE * item_topping4Quantity) +
                                                        (KISLSLAD_SET_PRICE * item_topping5Quantity) +
                                                        (GORCHIZA_SET_PRICE * item_topping6Quantity) ;

                                                Map<String, Object> toppingsMap = new HashMap<>();

                                                if (item_topping1Quantity > 0) {
                                                    toppingsMap.put("Доп Соус томатный", item_topping1Quantity);
                                                }
                                                if (item_topping2Quantity > 0) {
                                                    toppingsMap.put("Доп Соус сырный", item_topping2Quantity);
                                                }
                                                if (item_topping3Quantity > 0) {
                                                    toppingsMap.put("Доп Соус барбекю", item_topping3Quantity);
                                                }
                                                if (item_topping4Quantity > 0) {
                                                    toppingsMap.put("Доп Соус чесночный", item_topping4Quantity);
                                                }
                                                if (item_topping5Quantity > 0) {
                                                    toppingsMap.put("Доп Соус кисло-сладкий", item_topping5Quantity);
                                                }
                                                if (item_topping6Quantity > 0) {
                                                    toppingsMap.put("Доп Соус горчичный", item_topping6Quantity);
                                                }


                                                CartModel cartModel = new CartModel();
                                                cartModel.setItem_name(pPpizzaModel.getItem_name());
                                                cartModel.setItem_image(pPpizzaModel.getItem_image());
                                                cartModel.setItem_details(pPpizzaModel.getItem_details());
                                                cartModel.setKey(pPpizzaModel.getKey());
                                                cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                cartModel.setQuantity(1);
                                                cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);
                                                cartModel.setId(pPpizzaModel.getId());
                                                cartModel.setДопы(toppingsMap);
                                                cartModel.setItem_category(pPpizzaModel.getItem_category());

                                                cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                            }
                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });

                    break;

                case "Фритюр":

                    orderSelectSize.setVisibility(View.GONE);

                    item_topping1.setVisibility(View.VISIBLE);
                    item_topping2.setVisibility(View.VISIBLE);
                    item_topping3.setVisibility(View.VISIBLE);
                    item_topping4.setVisibility(View.VISIBLE);
                    item_topping5.setVisibility(View.VISIBLE);
                    item_topping6.setVisibility(View.VISIBLE);

                    final int TOMAT_FRITUR_PRICE = 10;
                    item_topping1_cost.setText(TOMAT_FRITUR_PRICE + " ₽");
                    item_topping1_name.setText("Соус томатный");

                    final int SIRNIY_FRITUR_PRICE = 10;
                    item_topping2_cost.setText(SIRNIY_FRITUR_PRICE + " ₽");
                    item_topping2_name.setText("Соус сырный");

                    final int BARBEKYU_FRITUR_PRICE = 10;
                    item_topping3_cost.setText(BARBEKYU_FRITUR_PRICE + " ₽");
                    item_topping3_name.setText("Соус барбекю");

                    final int CHESNOK_FRITUR_PRICE = 10;
                    item_topping4_cost.setText(CHESNOK_FRITUR_PRICE + " ₽");
                    item_topping4_name.setText("Соус чесночный");

                    final int KISLSLAD_FRITUR_PRICE = 10;
                    item_topping5_cost.setText(KISLSLAD_FRITUR_PRICE + " ₽");
                    item_topping5_name.setText("Соус кисло-сладкий");

                    final int GORCHICA_FRITUR_PRICE = 10;
                    item_topping6_cost.setText(GORCHICA_FRITUR_PRICE + " ₽");
                    item_topping6_name.setText("Соус горчичный");


                    if (pPpizzaModel.getItem_name().equals("Сосиски охотничье на шпажке")) {

                        orderSelectSize.setVisibility(View.VISIBLE);
                        radioButton3.setVisibility(View.VISIBLE);

                        radioButton1.setText("3 шт");
                        radioButton2.setText("6 шт");
                        radioButton3.setText("9 шт");
                    }

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

                                                    int toppingPizza =
                                                            (TOMAT_FRITUR_PRICE * item_topping1Quantity) +
                                                            (SIRNIY_FRITUR_PRICE * item_topping2Quantity) +
                                                            (BARBEKYU_FRITUR_PRICE * item_topping3Quantity) +
                                                            (CHESNOK_FRITUR_PRICE * item_topping4Quantity) +
                                                            (KISLSLAD_FRITUR_PRICE * item_topping5Quantity) +
                                                            (GORCHICA_FRITUR_PRICE * item_topping6Quantity);

                                                    Map<String, Object> toppingsMap = new HashMap<>();

                                                    if (item_topping1Quantity > 0) {
                                                        toppingsMap.put("Доп Соус томатный", item_topping1Quantity);
                                                    }
                                                    if (item_topping2Quantity > 0) {
                                                        toppingsMap.put("Доп Соус сырный", item_topping2Quantity);
                                                    }
                                                    if (item_topping3Quantity > 0) {
                                                        toppingsMap.put("Доп Соус барбекю", item_topping3Quantity);
                                                    }
                                                    if (item_topping4Quantity > 0) {
                                                        toppingsMap.put("Доп Соус чесночный", item_topping4Quantity);
                                                    }
                                                    if (item_topping5Quantity > 0) {
                                                        toppingsMap.put("Доп Соус кисло-сладкий", item_topping5Quantity);
                                                    }
                                                    if (item_topping6Quantity > 0) {
                                                        toppingsMap.put("Доп Соус горчичный", item_topping6Quantity);
                                                    }

                                                    CartModel cartModel = new CartModel();

                                                    cartModel.setItem_name(pPpizzaModel.getItem_name());
                                                    cartModel.setItem_image(pPpizzaModel.getItem_image());
                                                    cartModel.setItem_details(pPpizzaModel.getItem_details());
                                                    cartModel.setKey(pPpizzaModel.getKey());
                                                    cartModel.setQuantity(1);
                                                    cartModel.setId(pPpizzaModel.getId());
                                                    cartModel.setДопы(toppingsMap);
                                                    cartModel.setItem_category(pPpizzaModel.getItem_category());

                                                    /*Map<String, Object> dopSizeMap = new HashMap<>();*/

                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                            cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                            cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);
                                                            cartModel.setВариант_блюда("3 шт");
                                                            break;
                                                        case R.id.radioButton2:
                                                            /*dopSizeMap.put("Доп размер", "6 шт");*/

                                                            cartModel.setItem_cost(pPpizzaModel.getItem_cost_var1() + toppingPizza);
                                                            cartModel.setTotalPrice(pPpizzaModel.getItem_cost_var1() + toppingPizza);
                                                            cartModel.setВариант_блюда("6 шт");
                                                            break;
                                                        case R.id.radioButton3:
                                                            /*dopSizeMap.put("Доп размер", "9 шт");*/

                                                            cartModel.setItem_cost(pPpizzaModel.getItem_cost_var2() + toppingPizza);
                                                            cartModel.setTotalPrice(pPpizzaModel.getItem_cost_var2() + toppingPizza);
                                                            cartModel.setВариант_блюда("9 шт");
                                                            break;
                                                    }

                                                    FirebaseFirestore.getInstance()
                                                            .collection("Users_Cart")
                                                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                            .collection("Корзина")
                                                            .document(id)
                                                            .set(cartModel)
                                                            .addOnSuccessListener(aVoid -> {
/*
                                                                FirebaseFirestore.getInstance()
                                                                        .collection("Users_Cart")
                                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                        .collection("Корзина")
                                                                        .document(id)
                                                                        .update(dopSizeMap);*/

                                                                cartLoadListener.OnCartloadFailed("Добавлено");
                                                                finish();
                                                            });

                                                    EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                                }
                                            });
                                } catch (Exception e) {
                                    FirebaseCrashlytics.getInstance().recordException(e);
                                }
                            }
                        });
                    break;

                case "Напитки":

                    orderSelectSize.setVisibility(View.GONE);

                    if (pPpizzaModel.getItem_name().equals("Молочный шейк \"Дыня\"") ||
                            pPpizzaModel.getItem_name().equals("Банан-клубника") ||
                            pPpizzaModel.getItem_name().equals("Манго-персик") ||
                            pPpizzaModel.getItem_name().equals("Шейк Кокос") ||
                            pPpizzaModel.getItem_name().equals("Молочный") ||
                            pPpizzaModel.getItem_name().equals("Банановый коктейль") ||
                            pPpizzaModel.getItem_name().equals("Шоколадный") ||
                            pPpizzaModel.getItem_name().equals("Орео")) {

                        orderSelectSize.setVisibility(View.VISIBLE);

                        radioButton1.setText("Объем 0,4 л.");
                        radioButton2.setText("Объем 0,5 л.");
                    }
                    if (pPpizzaModel.getItem_name().equals("Американо") ||
                            pPpizzaModel.getItem_name().equals("Эспрессо") ||
                            pPpizzaModel.getItem_name().equals("Латте") ||
                            pPpizzaModel.getItem_name().equals("Кон Миель")) {

                        item_topping1.setVisibility(View.VISIBLE);
                        item_topping2.setVisibility(View.VISIBLE);
                        item_topping3.setVisibility(View.VISIBLE);
                        item_topping5.setVisibility(View.VISIBLE);
                        item_topping6.setVisibility(View.VISIBLE);
                        item_topping7.setVisibility(View.VISIBLE);
                    }
                    if(pPpizzaModel.getItem_name().equals("Капучино")) {

                        orderSelectSize.setVisibility(View.VISIBLE);

                        radioButton1.setText("Объем 250 мл.");
                        radioButton2.setText("Объем 350 мл.");

                        item_topping1.setVisibility(View.VISIBLE);
                        item_topping2.setVisibility(View.VISIBLE);
                        item_topping3.setVisibility(View.VISIBLE);
                        item_topping5.setVisibility(View.VISIBLE);
                        item_topping6.setVisibility(View.VISIBLE);
                        item_topping7.setVisibility(View.VISIBLE);
                    }

                    final int MED_NAPITKI_PRICE = 15;
                    item_topping1_cost.setText(MED_NAPITKI_PRICE + " ₽");
                    item_topping1_name.setText("Мёд");

                    final int SAHAR_NAPITKI_PRICE = 0;
                    item_topping2_cost.setText(SAHAR_NAPITKI_PRICE + " ₽");
                    item_topping2_name.setText("Сахар");

                    final int SIROP_NAPITKI_PRICE = 20;
                    item_topping3_cost.setText(SIROP_NAPITKI_PRICE + " ₽");
                    item_topping3_name.setText("Сироп");

                    final int KORICA_NAPITKI_PRICE = 0;
                    item_topping4_cost.setText(KORICA_NAPITKI_PRICE + " ₽");
                    item_topping4_name.setText("Корица");

                    final int ZEFIR_NAPITKI_PRICE = 20;
                    item_topping5_cost.setText(ZEFIR_NAPITKI_PRICE + " ₽");
                    item_topping5_name.setText("Зефирки");

                    final int SLIVKI_NAPITKI_PRICE = 30;
                    item_topping6_cost.setText(SLIVKI_NAPITKI_PRICE + " ₽");
                    item_topping6_name.setText("Взбитые сливки");

                    final int LIMON_NAPITKI_PRICE = 5;
                    item_topping7_cost.setText(LIMON_NAPITKI_PRICE + " ₽");
                    item_topping7_name.setText("Лимон (1 долька)");

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

                                                int toppingPizza =
                                                        (MED_NAPITKI_PRICE * item_topping1Quantity) +
                                                                (SAHAR_NAPITKI_PRICE * item_topping2Quantity) +
                                                                (SIROP_NAPITKI_PRICE * item_topping3Quantity) +
                                                                (KORICA_NAPITKI_PRICE * item_topping4Quantity) +
                                                                (ZEFIR_NAPITKI_PRICE * item_topping5Quantity) +
                                                                (SLIVKI_NAPITKI_PRICE * item_topping6Quantity) +
                                                                (LIMON_NAPITKI_PRICE * item_topping7Quantity);
                                                Map<String, Object> toppingsMap = new HashMap<>();

                                                if (item_topping1Quantity > 0) {
                                                    toppingsMap.put("Доп Мёд", item_topping1Quantity);
                                                }
                                                if (item_topping2Quantity > 0) {
                                                    toppingsMap.put("Доп Сахар", item_topping2Quantity);
                                                }
                                                if (item_topping3Quantity > 0) {
                                                    toppingsMap.put("Доп Сироп", item_topping3Quantity);
                                                }
                                                if (item_topping4Quantity > 0) {
                                                    toppingsMap.put("Доп Корица", item_topping4Quantity);
                                                }
                                                if (item_topping5Quantity > 0) {
                                                    toppingsMap.put("Доп Зефирки", item_topping5Quantity);
                                                }
                                                if (item_topping6Quantity > 0) {
                                                    toppingsMap.put("Доп Взбитые сливки", item_topping6Quantity);
                                                }
                                                if (item_topping7Quantity > 0) {
                                                    toppingsMap.put("Доп Соус горчичный", item_topping7Quantity);
                                                }

                                                CartModel cartModel = new CartModel();

                                                cartModel.setItem_name(pPpizzaModel.getItem_name());
                                                cartModel.setItem_image(pPpizzaModel.getItem_image());
                                                cartModel.setItem_details(pPpizzaModel.getItem_details());
                                                cartModel.setKey(pPpizzaModel.getKey());
                                                cartModel.setQuantity(1);
                                                cartModel.setId(pPpizzaModel.getId());
                                                cartModel.setДопы(toppingsMap);
                                                cartModel.setItem_category(pPpizzaModel.getItem_category());

                                                Map<String, Object> dopSizeMap = new HashMap<>();

                                                if (pPpizzaModel.getItem_name().equals("Молочный шейк \"Дыня\"") ||
                                                        pPpizzaModel.getItem_name().equals("Банан-клубника") ||
                                                        pPpizzaModel.getItem_name().equals("Манго-персик") ||
                                                        pPpizzaModel.getItem_name().equals("Шейк Кокос") ||
                                                        pPpizzaModel.getItem_name().equals("Молочный") ||
                                                        pPpizzaModel.getItem_name().equals("Банановый коктейль") ||
                                                        pPpizzaModel.getItem_name().equals("Шоколадный") ||
                                                        pPpizzaModel.getItem_name().equals("Орео")) {

                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                            cartModel.setItem_cost(pPpizzaModel.getItem_cost());
                                                            cartModel.setTotalPrice(pPpizzaModel.getItem_cost());
                                                            break;
                                                         case R.id.radioButton2:
                                                            /*dopSizeMap.put("Доп размер", "Объем 0,5 л.");*/

                                                            cartModel.setItem_cost(pPpizzaModel.getItem_cost_var1());
                                                            cartModel.setTotalPrice(pPpizzaModel.getItem_cost_var1());
                                                            cartModel.setВариант_блюда("Объем 0,5 л.");
                                                            break;
                                                    }
                                                }

                                                if (pPpizzaModel.getItem_name().equals("Американо") ||
                                                        pPpizzaModel.getItem_name().equals("Эспрессо") ||
                                                        pPpizzaModel.getItem_name().equals("Латте") ||
                                                        pPpizzaModel.getItem_name().equals("Кон Миель")) {

                                                    cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                    cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);
                                                }

                                                if (pPpizzaModel.getItem_name().equals("Капучино")) {

                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                            cartModel.setItem_cost(pPpizzaModel.getItem_cost() + toppingPizza);
                                                            cartModel.setTotalPrice(pPpizzaModel.getItem_cost() + toppingPizza);
                                                            break;
                                                        case R.id.radioButton2:
                                                            /*dopSizeMap.put("Доп размер", "Объем 350 мл.")*/;

                                                            cartModel.setItem_cost(pPpizzaModel.getItem_cost_var1() + toppingPizza);
                                                            cartModel.setTotalPrice(pPpizzaModel.getItem_cost_var1() + toppingPizza);
                                                            cartModel.setВариант_блюда("Объем 350 мл.");
                                                            break;
                                                    }
                                                }

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {

/*                                                            FirebaseFirestore.getInstance()
                                                                    .collection("Users_Cart")
                                                                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                                    .collection("Корзина")
                                                                    .document(id)
                                                                    .update(dopSizeMap);*/

                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

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
            item_category.setText(avocadoModel.getItem_category());
            item_cost.setText(avocadoModel.getItem_cost() + " ₽");


            itemSizeRadioButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioButton1:
                            item_cost.setText(avocadoModel.getItem_cost() + " ₽");
                            break;
                        case R.id.radioButton2:
                            item_cost.setText(avocadoModel.getItem_cost_var1() + " ₽");
                            break;
                        case R.id.radioButton3:
                            item_cost.setText(avocadoModel.getItem_cost_var2() + " ₽");
                            break;
                        case R.id.radioButton4:
                            item_cost.setText(avocadoModel.getItem_cost_var3() + " ₽");
                            break;
                        case R.id.radioButton5:
                            item_cost.setText(avocadoModel.getItem_cost_var4() + " ₽");
                            break;
                        case R.id.radioButton6:
                            item_cost.setText(avocadoModel.getItem_cost_var5() + " ₽");
                            break;
                        default:
                            break;
                    }
                }
            });

            switch (avocadoModel.getItem_category()) {

                case "Пицца":

                    orderSelectSize.setVisibility(View.GONE);


                    if (avocadoModel.getItem_name().equals("Гавайи")) {

                        orderSelectSize.setVisibility(View.VISIBLE);

                        radioButton1.setText("Ветчина");
                        radioButton2.setText("Курица");
                    }

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

                                                String id = FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document()
                                                        .getId();


                                                CartModel cartModel = new CartModel();

                                                cartModel.setItem_name(avocadoModel.getItem_name());
                                                cartModel.setItem_image(avocadoModel.getItem_image());
                                                cartModel.setItem_details(avocadoModel.getItem_details());
                                                cartModel.setKey(avocadoModel.getKey());
                                                cartModel.setQuantity(1);
                                                cartModel.setId(avocadoModel.getId());
                                                cartModel.setItem_category(avocadoModel.getItem_category());

                                                cartModel.setItem_cost(avocadoModel.getItem_cost());
                                                cartModel.setTotalPrice(avocadoModel.getItem_cost());


                                                if (avocadoModel.getItem_name().equals("Гавайи")) {
                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                            cartModel.setВариант_блюда("Ветчина");
                                                            break;
                                                        case R.id.radioButton2:
                                                            cartModel.setВариант_блюда("Курица");
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                            }
                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });
                    break;

                case "Вок":

                    orderSelectSize.setVisibility(View.GONE);

                    if (avocadoModel.getItem_name().equals("Удон \"Том-ям\" с курицей") ||
                        avocadoModel.getItem_name().equals("Удон \"Том-ям\" с морепродуктами")) {

                        orderSelectSize.setVisibility(View.VISIBLE);

                        radioButton1.setText("Не острый");
                        radioButton2.setText("Перчик чилли");

                    }

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

                                                String id = FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document()
                                                        .getId();


                                                CartModel cartModel = new CartModel();

                                                cartModel.setItem_name(avocadoModel.getItem_name());
                                                cartModel.setItem_image(avocadoModel.getItem_image());
                                                cartModel.setItem_details(avocadoModel.getItem_details());
                                                cartModel.setKey(avocadoModel.getKey());
                                                cartModel.setQuantity(1);
                                                cartModel.setId(avocadoModel.getId());
                                                cartModel.setItem_category(avocadoModel.getItem_category());

                                                cartModel.setItem_cost(avocadoModel.getItem_cost());
                                                cartModel.setTotalPrice(avocadoModel.getItem_cost());


                                                if (avocadoModel.getItem_name().equals("Удон \"Том-ям\" с курицей") ||
                                                        avocadoModel.getItem_name().equals("Удон \"Том-ям\" с морепродуктами")) {
                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                            cartModel.setВариант_блюда("Не острый");
                                                            break;
                                                        case R.id.radioButton2:
                                                            cartModel.setВариант_блюда("Перчик чилли");
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                            }
                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });
                    break;


                case "Салаты":

                    orderSelectSize.setVisibility(View.VISIBLE);
                    radioButton1.setText("Цезарь соус");
                    radioButton2.setText("Соус крем бальзамик");

                    if (avocadoModel.getItem_name().equals("Салат \"Гамадари\" с морепродуктами") ||
                            avocadoModel.getItem_name().equals("Салат \"Гамадари\" с куриным филе")) {

                        orderSelectSize.setVisibility(View.GONE);
                    }

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

                                                String id = FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document()
                                                        .getId();


                                                CartModel cartModel = new CartModel();

                                                cartModel.setItem_name(avocadoModel.getItem_name());
                                                cartModel.setItem_image(avocadoModel.getItem_image());
                                                cartModel.setItem_details(avocadoModel.getItem_details());
                                                cartModel.setKey(avocadoModel.getKey());
                                                cartModel.setQuantity(1);
                                                cartModel.setId(avocadoModel.getId());
                                                cartModel.setItem_category(avocadoModel.getItem_category());

                                                cartModel.setItem_cost(avocadoModel.getItem_cost());
                                                cartModel.setTotalPrice(avocadoModel.getItem_cost());


                                                switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                    case R.id.radioButton1:
                                                        cartModel.setВариант_блюда("Цезарь соус");
                                                        break;
                                                    case R.id.radioButton2:
                                                        cartModel.setВариант_блюда("Соус крем бальзамик");
                                                        break;
                                                    default:
                                                        break;
                                                }

                                                if (avocadoModel.getItem_name().equals("Салат \"Гамадари\" с морепродуктами") ||
                                                        avocadoModel.getItem_name().equals("Салат \"Гамадари\" с куриным филе")) {
                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                        case R.id.radioButton2:
                                                            cartModel.setВариант_блюда(null);
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                            }
                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });
                    break;


                case "Супы":

                    orderSelectSize.setVisibility(View.GONE);

                    if (avocadoModel.getItem_name().equals("Бульон говяжий")) {
                        orderSelectSize.setVisibility(View.VISIBLE);
                        radioButton1.setText("Без лапши");
                        radioButton2.setText("Лапша удон ручной работы");
                    }
                    if (avocadoModel.getItem_name().equals("Суп \"Том ям\"")) {
                        orderSelectSize.setVisibility(View.VISIBLE);
                        radioButton1.setText("Без лапши");
                        radioButton2.setText("Рисовая лапша");
                    }

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

                                                String id = FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document()
                                                        .getId();


                                                CartModel cartModel = new CartModel();

                                                cartModel.setItem_name(avocadoModel.getItem_name());
                                                cartModel.setItem_image(avocadoModel.getItem_image());
                                                cartModel.setItem_details(avocadoModel.getItem_details());
                                                cartModel.setKey(avocadoModel.getKey());
                                                cartModel.setQuantity(1);
                                                cartModel.setId(avocadoModel.getId());
                                                cartModel.setItem_category(avocadoModel.getItem_category());

                                                cartModel.setItem_cost(avocadoModel.getItem_cost());
                                                cartModel.setTotalPrice(avocadoModel.getItem_cost());


                                                if (avocadoModel.getItem_name().equals("Бульон говяжий")) {
                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                            cartModel.setВариант_блюда("Без лапши");
                                                            break;
                                                        case R.id.radioButton2:
                                                            cartModel.setВариант_блюда("Лапша удон ручной работы");
                                                            cartModel.setItem_cost(avocadoModel.getItem_cost_var1());
                                                            cartModel.setTotalPrice(avocadoModel.getItem_cost_var1());
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }
                                                if (avocadoModel.getItem_name().equals("Суп \"Том ям\"")) {
                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                            cartModel.setВариант_блюда("Без лапши");
                                                            break;
                                                        case R.id.radioButton2:
                                                            cartModel.setВариант_блюда("Рисовая лапша");
                                                            cartModel.setItem_cost(avocadoModel.getItem_cost_var1());
                                                            cartModel.setTotalPrice(avocadoModel.getItem_cost_var1());
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                            }
                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });
                    break;


                case "Картофель фри":

                    orderSelectSize.setVisibility(View.VISIBLE);
                    radioButton3.setVisibility(View.VISIBLE);
                    radioButton4.setVisibility(View.VISIBLE);
                    radioButton5.setVisibility(View.VISIBLE);
                    radioButton6.setVisibility(View.VISIBLE);

                    itemSizeRadioButton.setLayoutParams(params);
                    radioButton1.setText("Без соуса");
                    radioButton2.setText("Соус BBQ");
                    radioButton3.setText("Тар-тар соус");
                    radioButton4.setText("Соус горчичный");
                    radioButton5.setText("Цезарь соус");
                    radioButton6.setText("Соус чилли шрирача");

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

                                                String id = FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document()
                                                        .getId();


                                                CartModel cartModel = new CartModel();

                                                cartModel.setItem_name(avocadoModel.getItem_name());
                                                cartModel.setItem_image(avocadoModel.getItem_image());
                                                cartModel.setItem_details(avocadoModel.getItem_details());
                                                cartModel.setKey(avocadoModel.getKey());
                                                cartModel.setQuantity(1);
                                                cartModel.setId(avocadoModel.getId());
                                                cartModel.setItem_category(avocadoModel.getItem_category());

                                                cartModel.setItem_cost(avocadoModel.getItem_cost());
                                                cartModel.setTotalPrice(avocadoModel.getItem_cost());

                                                switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                    case R.id.radioButton1:
                                                        cartModel.setВариант_блюда("Без соуса");
                                                        break;
                                                    case R.id.radioButton2:
                                                        cartModel.setВариант_блюда("Соус BBQ");
                                                        cartModel.setItem_cost(avocadoModel.getItem_cost_var1());
                                                        cartModel.setTotalPrice(avocadoModel.getItem_cost_var1());
                                                        break;
                                                    case R.id.radioButton3:
                                                        cartModel.setВариант_блюда("Тар-тар соус");
                                                        cartModel.setItem_cost(avocadoModel.getItem_cost_var1());
                                                        cartModel.setTotalPrice(avocadoModel.getItem_cost_var1());
                                                        break;
                                                    case R.id.radioButton4:
                                                        cartModel.setВариант_блюда("Соус горчичный");
                                                        cartModel.setItem_cost(avocadoModel.getItem_cost_var1());
                                                        cartModel.setTotalPrice(avocadoModel.getItem_cost_var1());
                                                        break;
                                                    case R.id.radioButton5:
                                                        cartModel.setВариант_блюда("Цезарь соус");
                                                        cartModel.setItem_cost(avocadoModel.getItem_cost_var1());
                                                        cartModel.setTotalPrice(avocadoModel.getItem_cost_var1());
                                                        break;
                                                    case R.id.radioButton6:
                                                        cartModel.setВариант_блюда("Соус чилли шрирача");
                                                        cartModel.setItem_cost(avocadoModel.getItem_cost_var1());
                                                        cartModel.setTotalPrice(avocadoModel.getItem_cost_var1());
                                                        break;
                                                    default:
                                                        break;
                                                }

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                            }
                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });
                    break;


                case "Каша":
                    orderSelectSize.setVisibility(View.GONE);

                    if (avocadoModel.getItem_name().equals("Каша овсяная")) {
                        orderSelectSize.setVisibility(View.VISIBLE);
                        radioButton3.setVisibility(View.VISIBLE);
                        radioButton4.setVisibility(View.VISIBLE);

                        itemSizeRadioButton.setLayoutParams(params);
                        radioButton1.setText("Молоко");
                        radioButton2.setText("Вода");
                        radioButton3.setText("Молоко кокосовое");
                        radioButton4.setText("Молоко миндальное");
                    }

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

                                                String id = FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document()
                                                        .getId();


                                                CartModel cartModel = new CartModel();

                                                cartModel.setItem_name(avocadoModel.getItem_name());
                                                cartModel.setItem_image(avocadoModel.getItem_image());
                                                cartModel.setItem_details(avocadoModel.getItem_details());
                                                cartModel.setKey(avocadoModel.getKey());
                                                cartModel.setQuantity(1);
                                                cartModel.setId(avocadoModel.getId());
                                                cartModel.setItem_category(avocadoModel.getItem_category());

                                                cartModel.setItem_cost(avocadoModel.getItem_cost());
                                                cartModel.setTotalPrice(avocadoModel.getItem_cost());

                                                if (avocadoModel.getItem_name().equals("Каша овсяная")) {
                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                            cartModel.setВариант_блюда("Молоко");
                                                            break;
                                                        case R.id.radioButton2:
                                                            cartModel.setВариант_блюда("Вода");
                                                            cartModel.setItem_cost(avocadoModel.getItem_cost_var1());
                                                            cartModel.setTotalPrice(avocadoModel.getItem_cost_var1());
                                                            break;
                                                        case R.id.radioButton3:
                                                            cartModel.setВариант_блюда("Молоко кокосовое");
                                                            cartModel.setItem_cost(avocadoModel.getItem_cost_var2());
                                                            cartModel.setTotalPrice(avocadoModel.getItem_cost_var2());
                                                            break;
                                                        case R.id.radioButton4:
                                                            cartModel.setВариант_блюда("Молоко миндальное");
                                                            cartModel.setItem_cost(avocadoModel.getItem_cost_var3());
                                                            cartModel.setTotalPrice(avocadoModel.getItem_cost_var3());
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                            }
                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });
                    break;


                case "Авторские чаи":

                    orderSelectSize.setVisibility(View.VISIBLE);
                    radioButton3.setVisibility(View.VISIBLE);
                    radioButton4.setVisibility(View.VISIBLE);

                    itemSizeRadioButton.setLayoutParams(params);

                    radioButton1.setText("Без сахара");
                    radioButton2.setText("Сахарный сироп");
                    radioButton3.setText("Мёд");
                    radioButton4.setText("Сироп (на выбор)");


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

                                                String id = FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document()
                                                        .getId();


                                                CartModel cartModel = new CartModel();

                                                cartModel.setItem_name(avocadoModel.getItem_name());
                                                cartModel.setItem_image(avocadoModel.getItem_image());
                                                cartModel.setItem_details(avocadoModel.getItem_details());
                                                cartModel.setKey(avocadoModel.getKey());
                                                cartModel.setQuantity(1);
                                                cartModel.setId(avocadoModel.getId());
                                                cartModel.setItem_category(avocadoModel.getItem_category());

                                                cartModel.setItem_cost(avocadoModel.getItem_cost());
                                                cartModel.setTotalPrice(avocadoModel.getItem_cost());

                                                switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                    case R.id.radioButton1:
                                                        cartModel.setВариант_блюда("Без сахара");
                                                        break;
                                                    case R.id.radioButton2:
                                                        cartModel.setВариант_блюда("Сахарный сироп");
                                                        cartModel.setItem_cost(avocadoModel.getItem_cost_var1());
                                                        cartModel.setTotalPrice(avocadoModel.getItem_cost_var1());
                                                        break;
                                                    case R.id.radioButton3:
                                                        cartModel.setВариант_блюда("Мёд");
                                                        cartModel.setItem_cost(avocadoModel.getItem_cost_var2());
                                                        cartModel.setTotalPrice(avocadoModel.getItem_cost_var2());
                                                        break;
                                                    case R.id.radioButton4:
                                                        cartModel.setВариант_блюда("Сироп (на выбор)");
                                                        cartModel.setItem_cost(avocadoModel.getItem_cost_var3());
                                                        cartModel.setTotalPrice(avocadoModel.getItem_cost_var3());
                                                        break;
                                                    default:
                                                        break;
                                                }


                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                            }
                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });
                    break;

                case "Кофе":

                    orderSelectSize.setVisibility(View.VISIBLE);
                    radioButton3.setVisibility(View.VISIBLE);

                    radioButton1.setText("Без сахара");
                    radioButton2.setText("Сахарный сироп");
                    radioButton3.setText("Сироп (на выбор)");

                    if (avocadoModel.getItem_name().equals("Дополнительная крепость кофе") ||
                            avocadoModel.getItem_name().equals("Фраппучино")) {
                        orderSelectSize.setVisibility(View.GONE);
                    }

                    if (avocadoModel.getItem_name().equals("Какао с маршмеллоу 0,3")) {
                        radioButton1.setText("Молоко");
                        radioButton2.setText("Молоко кокосовое");
                        radioButton3.setText("Молоко миндальное");
                    }

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

                                                String id = FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document()
                                                        .getId();


                                                CartModel cartModel = new CartModel();

                                                cartModel.setItem_name(avocadoModel.getItem_name());
                                                cartModel.setItem_image(avocadoModel.getItem_image());
                                                cartModel.setItem_details(avocadoModel.getItem_details());
                                                cartModel.setKey(avocadoModel.getKey());
                                                cartModel.setQuantity(1);
                                                cartModel.setId(avocadoModel.getId());
                                                cartModel.setItem_category(avocadoModel.getItem_category());

                                                cartModel.setItem_cost(avocadoModel.getItem_cost());
                                                cartModel.setTotalPrice(avocadoModel.getItem_cost());

                                                switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                    case R.id.radioButton1:
                                                        cartModel.setВариант_блюда("Без сахара");
                                                        break;
                                                    case R.id.radioButton2:
                                                        cartModel.setВариант_блюда("Сахарный сироп");
                                                        cartModel.setItem_cost(avocadoModel.getItem_cost_var1());
                                                        cartModel.setTotalPrice(avocadoModel.getItem_cost_var1());
                                                        break;
                                                    case R.id.radioButton3:
                                                        cartModel.setВариант_блюда("Сироп (на выбор)");
                                                        cartModel.setItem_cost(avocadoModel.getItem_cost_var2());
                                                        cartModel.setTotalPrice(avocadoModel.getItem_cost_var2());
                                                        break;
                                                    default:
                                                        break;
                                                }

                                                if (avocadoModel.getItem_name().equals("Дополнительная крепость кофе") ||
                                                        avocadoModel.getItem_name().equals("Фраппучино")) {
                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                        case R.id.radioButton2:
                                                        case R.id.radioButton3:
                                                            cartModel.setВариант_блюда(null);
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }

                                                if (avocadoModel.getItem_name().equals("Какао с маршмеллоу 0,3")) {
                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                            cartModel.setВариант_блюда("Молоко");
                                                            break;
                                                        case R.id.radioButton2:
                                                            cartModel.setВариант_блюда("Молоко кокосовое");
                                                            cartModel.setItem_cost(avocadoModel.getItem_cost_var1());
                                                            cartModel.setTotalPrice(avocadoModel.getItem_cost_var1());
                                                            break;
                                                        case R.id.radioButton3:
                                                            cartModel.setВариант_блюда("Молоко миндальное");
                                                            cartModel.setItem_cost(avocadoModel.getItem_cost_var2());
                                                            cartModel.setTotalPrice(avocadoModel.getItem_cost_var2());
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                            }
                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });
                    break;


                case "Дополнение":

                    orderSelectSize.setVisibility(View.GONE);


                    if (avocadoModel.getItem_name().equals("Молоко растительное 30 мл.")) {
                        orderSelectSize.setVisibility(View.VISIBLE);
                        radioButton1.setText("Молоко кокос");
                        radioButton2.setText("Молоко миндаль");
                    }


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

                                                String id = FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document()
                                                        .getId();


                                                CartModel cartModel = new CartModel();

                                                cartModel.setItem_name(avocadoModel.getItem_name());
                                                cartModel.setItem_image(avocadoModel.getItem_image());
                                                cartModel.setItem_details(avocadoModel.getItem_details());
                                                cartModel.setKey(avocadoModel.getKey());
                                                cartModel.setQuantity(1);
                                                cartModel.setId(avocadoModel.getId());
                                                cartModel.setItem_category(avocadoModel.getItem_category());

                                                cartModel.setItem_cost(avocadoModel.getItem_cost());
                                                cartModel.setTotalPrice(avocadoModel.getItem_cost());

                                                if (avocadoModel.getItem_name().equals("Молоко растительное 30 мл.")) {
                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                            cartModel.setВариант_блюда("Молоко кокос");
                                                            break;
                                                        case R.id.radioButton2:
                                                            cartModel.setВариант_блюда("Молоко миндаль");
                                                            cartModel.setItem_cost(avocadoModel.getItem_cost_var1());
                                                            cartModel.setTotalPrice(avocadoModel.getItem_cost_var1());
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

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
                case "Сэндвичи":
                case "Молочные коктейли":
                case "Десерты":
                case "Напитки":

                    orderSelectSize.setVisibility(View.GONE);

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

                                                String id = FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document()
                                                        .getId();


                                                CartModel cartModel = new CartModel();

                                                cartModel.setItem_name(avocadoModel.getItem_name());
                                                cartModel.setItem_image(avocadoModel.getItem_image());
                                                cartModel.setItem_details(avocadoModel.getItem_details());
                                                cartModel.setKey(avocadoModel.getKey());
                                                cartModel.setQuantity(1);
                                                cartModel.setId(avocadoModel.getId());
                                                cartModel.setItem_category(avocadoModel.getItem_category());

                                                cartModel.setItem_cost(avocadoModel.getItem_cost());
                                                cartModel.setTotalPrice(avocadoModel.getItem_cost());

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

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


    private void loadDjo(Object object) {
        if (object instanceof DjoModel) {
            djoModel = (DjoModel) object;
        }

        if (djoModel != null) {
            Glide.with(getApplicationContext()).load(djoModel.getItem_image()).into(item_image);
            item_descr.setText(djoModel.getItem_details());
            item_name.setText(djoModel.getItem_name());
            item_category.setText(djoModel.getItem_category());
            item_cost.setText(djoModel.getItem_cost() + " ₽");

            itemSizeRadioButton.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radioButton1:
                            item_cost.setText(djoModel.getItem_cost() + " ₽");
                            break;
                        case R.id.radioButton2:
                            item_cost.setText(djoModel.getItem_cost_var1() + " ₽");
                            break;
                        case R.id.radioButton3:
                            item_cost.setText(djoModel.getItem_cost_var2() + " ₽");
                            break;
                        case R.id.radioButton4:
                            item_cost.setText(djoModel.getItem_cost_var3() + " ₽");
                            break;
                        case R.id.radioButton5:
                            item_cost.setText(djoModel.getItem_cost_var4() + " ₽");
                            break;
                        case R.id.radioButton6:
                            item_cost.setText(djoModel.getItem_cost_var5() + " ₽");
                            break;
                        default:
                            break;
                    }
                }
            });

            switch (djoModel.getItem_category()) {

                case "Пицца":
                case "Сеты":

                    orderSelectSize.setVisibility(View.GONE);

                    btnAddToCart_Detailed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {

                                FirebaseFirestore.getInstance()
                                        .collection("Users_Cart")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Корзина")
                                        .document(djoModel.getKey())
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


                                                CartModel cartModel = new CartModel();

                                                cartModel.setItem_name(djoModel.getItem_name());
                                                cartModel.setItem_image(djoModel.getItem_image());
                                                cartModel.setItem_details(djoModel.getItem_details());
                                                cartModel.setKey(djoModel.getKey());
                                                cartModel.setQuantity(1);
                                                cartModel.setId(djoModel.getId());
                                                cartModel.setItem_category(djoModel.getItem_category());

                                                cartModel.setItem_cost(djoModel.getItem_cost());
                                                cartModel.setTotalPrice(djoModel.getItem_cost());

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                            }
                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });
                    break;


                case "Фритюр":

                    orderSelectSize.setVisibility(View.VISIBLE);

                    radioButton1.setText("12 шт.");
                    radioButton2.setText("6 шт.");


                    if (djoModel.getItem_name().equals("Фирменные куриные крылышки") ||
                            djoModel.getItem_name().equals("Фирменные куриные крылышки в соусе Сладкий Чили") ||
                            djoModel.getItem_name().equals("Фирменные куриные крылышки с сыром")) {
                        radioButton3.setVisibility(View.VISIBLE);

                        radioButton1.setText("1 кг");
                        radioButton2.setText("0,5 кг");
                        radioButton3.setText("0,25 кг");
                    }
                    if (djoModel.getItem_name().equals("Дамплинги Острые") ||
                            djoModel.getItem_name().equals("Дамплинги С морепродуктами")) {
                        radioButton1.setText("6 шт.");
                        radioButton2.setText("12 шт.");
                    }
                    if (djoModel.getItem_name().equals("Наггетсы")) {
                        radioButton3.setVisibility(View.VISIBLE);

                        radioButton1.setText("12 шт.");
                        radioButton2.setText("6 шт.");
                        radioButton3.setText("30 шт.");
                    }
                    if (djoModel.getItem_name().equals("Картофель фри Соломка") ||
                            djoModel.getItem_name().equals("Чебуреки") ||
                            djoModel.getItem_name().equals("Картофель фри Дольки") ||
                            djoModel.getItem_name().equals("Пышные Сырники") ||
                            djoModel.getItem_name().equals("Пельмешки Фри")) {
                        orderSelectSize.setVisibility(View.GONE);
                    }

                    btnAddToCart_Detailed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {

                                FirebaseFirestore.getInstance()
                                        .collection("Users_Cart")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Корзина")
                                        .document(djoModel.getKey())
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


                                                CartModel cartModel = new CartModel();

                                                cartModel.setItem_name(djoModel.getItem_name());
                                                cartModel.setItem_image(djoModel.getItem_image());
                                                cartModel.setItem_details(djoModel.getItem_details());
                                                cartModel.setKey(djoModel.getKey());
                                                cartModel.setQuantity(1);
                                                cartModel.setId(djoModel.getId());
                                                cartModel.setItem_category(djoModel.getItem_category());

                                                cartModel.setItem_cost(djoModel.getItem_cost());
                                                cartModel.setTotalPrice(djoModel.getItem_cost());

                                                switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                    case R.id.radioButton1:
                                                        cartModel.setВариант_блюда("12 шт.");
                                                        break;
                                                    case R.id.radioButton2:
                                                        cartModel.setВариант_блюда("6 шт.");
                                                        cartModel.setItem_cost(djoModel.getItem_cost_var1());
                                                        cartModel.setTotalPrice(djoModel.getItem_cost_var1());
                                                        break;
                                                    default:
                                                        break;
                                                }

                                                if (djoModel.getItem_name().equals("Дамплинги Острые") ||
                                                        djoModel.getItem_name().equals("Дамплинги С морепродуктами")) {
                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                            cartModel.setВариант_блюда("6 шт.");
                                                            break;
                                                        case R.id.radioButton2:
                                                            cartModel.setВариант_блюда("12 шт.");
                                                            cartModel.setItem_cost(djoModel.getItem_cost_var1());
                                                            cartModel.setTotalPrice(djoModel.getItem_cost_var1());
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }

                                                if (djoModel.getItem_name().equals("Фирменные куриные крылышки") ||
                                                        djoModel.getItem_name().equals("Фирменные куриные крылышки в соусе Сладкий Чили") ||
                                                        djoModel.getItem_name().equals("Фирменные куриные крылышки")) {
                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                            cartModel.setВариант_блюда("1 кг");
                                                            break;
                                                        case R.id.radioButton2:
                                                            cartModel.setВариант_блюда("0,5 кг");
                                                            cartModel.setItem_cost(djoModel.getItem_cost_var1());
                                                            cartModel.setTotalPrice(djoModel.getItem_cost_var1());
                                                            break;
                                                        case R.id.radioButton3:
                                                            cartModel.setВариант_блюда("0,25 кг");
                                                            cartModel.setItem_cost(djoModel.getItem_cost_var2());
                                                            cartModel.setTotalPrice(djoModel.getItem_cost_var2());
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }

                                                if (djoModel.getItem_name().equals("Наггетсы")) {
                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                            cartModel.setВариант_блюда("12 шт.");
                                                            break;
                                                        case R.id.radioButton2:
                                                            cartModel.setВариант_блюда("6 шт.");
                                                            cartModel.setItem_cost(djoModel.getItem_cost_var1());
                                                            cartModel.setTotalPrice(djoModel.getItem_cost_var1());
                                                            break;
                                                        case R.id.radioButton3:
                                                            cartModel.setВариант_блюда("30 шт.");
                                                            cartModel.setItem_cost(djoModel.getItem_cost_var2());
                                                            cartModel.setTotalPrice(djoModel.getItem_cost_var2());
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }

                                                if (djoModel.getItem_name().equals("Картофель фри Соломка") ||
                                                        djoModel.getItem_name().equals("Чебуреки") ||
                                                        djoModel.getItem_name().equals("Картофель фри Дольки") ||
                                                        djoModel.getItem_name().equals("Пышные Сырники") ||
                                                        djoModel.getItem_name().equals("Пельмешки Фри")) {
                                                    cartModel.setВариант_блюда(null);
                                                }

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

                                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                                            }
                                        });
                            } catch (Exception e) {
                                FirebaseCrashlytics.getInstance().recordException(e);
                            }
                        }
                    });
                    break;

                case "Дополнение":

                    orderSelectSize.setVisibility(View.GONE);

                    if (djoModel.getItem_name().equals("Дополнительные соусы")) {
                        orderSelectSize.setVisibility(View.VISIBLE);

                        radioButton3.setVisibility(View.VISIBLE);
                        radioButton4.setVisibility(View.VISIBLE);
                        radioButton5.setVisibility(View.VISIBLE);
                        radioButton6.setVisibility(View.VISIBLE);
                        radioButton7.setVisibility(View.VISIBLE);
                        radioButton8.setVisibility(View.VISIBLE);
                        radioButton9.setVisibility(View.VISIBLE);

                        itemSizeRadioButton.setLayoutParams(params);

                        radioButton1.setText("Томатный");
                        radioButton2.setText("Барбекью");
                        radioButton3.setText("Сырный");
                        radioButton4.setText("Терияки");
                        radioButton5.setText("Сальса");
                        radioButton6.setText("Чесночный");
                        radioButton7.setText("Кисло - сладкий");
                        radioButton8.setText("Карри");
                        radioButton9.setText("Горчичный");
                    }

                    btnAddToCart_Detailed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {

                                FirebaseFirestore.getInstance()
                                        .collection("Users_Cart")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Корзина")
                                        .document(djoModel.getKey())
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


                                                CartModel cartModel = new CartModel();

                                                cartModel.setItem_name(djoModel.getItem_name());
                                                cartModel.setItem_image(djoModel.getItem_image());
                                                cartModel.setItem_details(djoModel.getItem_details());
                                                cartModel.setKey(djoModel.getKey());
                                                cartModel.setQuantity(1);
                                                cartModel.setId(djoModel.getId());
                                                cartModel.setItem_category(djoModel.getItem_category());

                                                cartModel.setItem_cost(djoModel.getItem_cost());
                                                cartModel.setTotalPrice(djoModel.getItem_cost());


                                                if (djoModel.getItem_name().equals("Дополнительные соусы")) {
                                                    switch (itemSizeRadioButton.getCheckedRadioButtonId()) {
                                                        case R.id.radioButton1:
                                                            cartModel.setВариант_блюда("Томатный");
                                                            break;
                                                        case R.id.radioButton2:
                                                            cartModel.setВариант_блюда("Барбекью");
                                                            cartModel.setItem_cost(djoModel.getItem_cost_var1());
                                                            cartModel.setTotalPrice(djoModel.getItem_cost_var1());
                                                            break;
                                                        case R.id.radioButton3:
                                                            cartModel.setВариант_блюда("Сырный");
                                                            cartModel.setItem_cost(djoModel.getItem_cost_var2());
                                                            cartModel.setTotalPrice(djoModel.getItem_cost_var2());
                                                            break;
                                                        case R.id.radioButton4:
                                                            cartModel.setВариант_блюда("Терияки");
                                                            cartModel.setItem_cost(djoModel.getItem_cost_var3());
                                                            cartModel.setTotalPrice(djoModel.getItem_cost_var3());
                                                            break;
                                                        case R.id.radioButton5:
                                                            cartModel.setВариант_блюда("Сальса");
                                                            cartModel.setItem_cost(djoModel.getItem_cost_var4());
                                                            cartModel.setTotalPrice(djoModel.getItem_cost_var4());
                                                            break;
                                                        case R.id.radioButton6:
                                                            cartModel.setВариант_блюда("Чесночный");
                                                            cartModel.setItem_cost(djoModel.getItem_cost_var5());
                                                            cartModel.setTotalPrice(djoModel.getItem_cost_var5());
                                                            break;
                                                        case R.id.radioButton7:
                                                            cartModel.setВариант_блюда("Кисло-сладкий");
                                                            cartModel.setItem_cost(djoModel.getItem_cost_var6());
                                                            cartModel.setTotalPrice(djoModel.getItem_cost_var6());
                                                            break;
                                                        case R.id.radioButton8:
                                                            cartModel.setВариант_блюда("Карри");
                                                            cartModel.setItem_cost(djoModel.getItem_cost_var7());
                                                            cartModel.setTotalPrice(djoModel.getItem_cost_var7());
                                                            break;
                                                        case R.id.radioButton9:
                                                            cartModel.setВариант_блюда("Горчичный");
                                                            cartModel.setItem_cost(djoModel.getItem_cost_var8());
                                                            cartModel.setTotalPrice(djoModel.getItem_cost_var8());
                                                            break;
                                                        default:
                                                            break;
                                                    }
                                                }

                                                FirebaseFirestore.getInstance()
                                                        .collection("Users_Cart")
                                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                        .collection("Корзина")
                                                        .document(id)
                                                        .set(cartModel)
                                                        .addOnSuccessListener(aVoid -> {
                                                            cartLoadListener.OnCartloadFailed("Добавлено");
                                                            finish();
                                                        });

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





    private void toppingsCountPpizza() {
        item_topping1_plus.setOnClickListener(v ->{

            if (item_topping1Quantity >= 0) {
                item_topping1Quantity++;
                item_topping1_quantity.setText(String.valueOf(item_topping1Quantity));
            }
        });

        item_topping1_minus.setOnClickListener(v ->{

            if (item_topping1Quantity > 0) {
                item_topping1Quantity--;
                item_topping1_quantity.setText(String.valueOf(item_topping1Quantity));
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


        item_topping10_plus.setOnClickListener(v ->{

            if (item_topping10Quantity >= 0) {
                item_topping10Quantity++;
                item_topping10_quantity.setText(String.valueOf(item_topping10Quantity));
            }
        });

        item_topping10_minus.setOnClickListener(v ->{

            if (item_topping10Quantity > 0) {
                item_topping10Quantity--;
                item_topping10_quantity.setText(String.valueOf(item_topping10Quantity));
            }
        });


        item_topping11_plus.setOnClickListener(v ->{

            if (item_topping11Quantity >= 0) {
                item_topping11Quantity++;
                item_topping11_quantity.setText(String.valueOf(item_topping11Quantity));
            }
        });

        item_topping11_minus.setOnClickListener(v ->{

            if (item_topping11Quantity > 0) {
                item_topping11Quantity--;
                item_topping11_quantity.setText(String.valueOf(item_topping11Quantity));
            }
        });


        item_topping12_plus.setOnClickListener(v ->{

            if (item_topping12Quantity >= 0) {
                item_topping12Quantity++;
                item_topping12_quantity.setText(String.valueOf(item_topping12Quantity));
            }
        });

        item_topping12_minus.setOnClickListener(v ->{

            if (item_topping12Quantity > 0) {
                item_topping12Quantity--;
                item_topping12_quantity.setText(String.valueOf(item_topping12Quantity));
            }
        });


        item_topping13_plus.setOnClickListener(v ->{

            if (item_topping13Quantity >= 0) {
                item_topping13Quantity++;
                item_topping13_quantity.setText(String.valueOf(item_topping13Quantity));
            }
        });

        item_topping13_minus.setOnClickListener(v ->{

            if (item_topping13Quantity > 0) {
                item_topping13Quantity--;
                item_topping13_quantity.setText(String.valueOf(item_topping13Quantity));
            }
        });


        item_topping14_plus.setOnClickListener(v ->{

            if (item_topping14Quantity >= 0) {
                item_topping14Quantity++;
                item_topping14_quantity.setText(String.valueOf(item_topping14Quantity));
            }
        });

        item_topping14_minus.setOnClickListener(v ->{

            if (item_topping14Quantity > 0) {
                item_topping14Quantity--;
                item_topping14_quantity.setText(String.valueOf(item_topping14Quantity));
            }
        });


        item_topping15_plus.setOnClickListener(v ->{

            if (item_topping15Quantity >= 0) {
                item_topping15Quantity++;
                item_topping15_quantity.setText(String.valueOf(item_topping15Quantity));
            }
        });

        item_topping15_minus.setOnClickListener(v ->{

            if (item_topping15Quantity > 0) {
                item_topping15Quantity--;
                item_topping15_quantity.setText(String.valueOf(item_topping15Quantity));
            }
        });


        item_topping16_plus.setOnClickListener(v ->{

            if (item_topping16Quantity >= 0) {
                item_topping16Quantity++;
                item_topping16_quantity.setText(String.valueOf(item_topping16Quantity));
            }
        });

        item_topping16_minus.setOnClickListener(v ->{

            if (item_topping16Quantity > 0) {
                item_topping16Quantity--;
                item_topping16_quantity.setText(String.valueOf(item_topping16Quantity));
            }
        });


        item_topping17_plus.setOnClickListener(v ->{

            if (item_topping17Quantity >= 0) {
                item_topping17Quantity++;
                item_topping17_quantity.setText(String.valueOf(item_topping17Quantity));
            }
        });

        item_topping17_minus.setOnClickListener(v ->{

            if (item_topping17Quantity > 0) {
                item_topping17Quantity--;
                item_topping17_quantity.setText(String.valueOf(item_topping17Quantity));
            }
        });


        item_topping18_plus.setOnClickListener(v ->{

            if (item_topping18Quantity >= 0) {
                item_topping18Quantity++;
                item_topping18_quantity.setText(String.valueOf(item_topping18Quantity));
            }
        });

        item_topping18_minus.setOnClickListener(v ->{

            if (item_topping18Quantity > 0) {
                item_topping18Quantity--;
                item_topping18_quantity.setText(String.valueOf(item_topping18Quantity));
            }
        });


        item_topping19_plus.setOnClickListener(v ->{

            if (item_topping19Quantity >= 0) {
                item_topping19Quantity++;
                item_topping19_quantity.setText(String.valueOf(item_topping19Quantity));
            }
        });

        item_topping19_minus.setOnClickListener(v ->{

            if (item_topping19Quantity > 0) {
                item_topping19Quantity--;
                item_topping19_quantity.setText(String.valueOf(item_topping19Quantity));
            }
        });


        item_topping20_plus.setOnClickListener(v ->{

            if (item_topping20Quantity >= 0) {
                item_topping20Quantity++;
                item_topping20_quantity.setText(String.valueOf(item_topping20Quantity));
            }
        });

        item_topping20_minus.setOnClickListener(v ->{

            if (item_topping20Quantity > 0) {
                item_topping20Quantity--;
                item_topping20_quantity.setText(String.valueOf(item_topping20Quantity));
            }
        });


        item_topping21_plus.setOnClickListener(v ->{

            if (item_topping21Quantity >= 0) {
                item_topping21Quantity++;
                item_topping21_quantity.setText(String.valueOf(item_topping21Quantity));
            }
        });

        item_topping21_minus.setOnClickListener(v ->{

            if (item_topping21Quantity > 0) {
                item_topping21Quantity--;
                item_topping21_quantity.setText(String.valueOf(item_topping21Quantity));
            }
        });
    }

    @NonNull
    private FrameLayout.LayoutParams getLayoutParams() {
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.START;
        return params;
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