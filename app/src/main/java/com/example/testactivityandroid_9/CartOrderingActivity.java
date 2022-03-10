package com.example.testactivityandroid_9;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testactivityandroid_9.eventbus.MyUpdateCartEvent;
import com.example.testactivityandroid_9.model.BonusModel;
import com.example.testactivityandroid_9.model.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.DateValidatorPointForward;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import com.google.common.base.Joiner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.greenrobot.eventbus.EventBus;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
    @BindView(R.id.orderDate)
    TextInputEditText orderDate;
    @BindView(R.id.orderTime)
    TextInputEditText orderTime;
    @BindView(R.id.orderDateLayout)
    TextInputLayout orderDateLayout;
    @BindView(R.id.orderPayment)
    RadioGroup orderPayment;
    @BindView(R.id.radioButtonCash)
    RadioButton radioButtonCash;
    @BindView(R.id.orderDeliverySwitch)
    SwitchMaterial orderDeliverySwitch;
    @BindView(R.id.orderDeliveryTimeDate)
    RelativeLayout orderDeliveryTimeDate;
    @BindView(R.id.orderDeliveryMethod)
    RadioGroup orderDeliveryMethod;
    @BindView(R.id.radioButtonDelivery)
    RadioButton radioButtonDelivery;
    @BindView(R.id.orderDeliveryLayout)
    RelativeLayout orderDeliveryLayout;


    List<CartModel>  cartModelList;
    CartModel cartModel;
    List<Object> list2;
    Map<String, Object>  orderContactInfoMap = new HashMap<>();
    Map<String, Object>  orderContactInfoMapPpizza = new HashMap<>();
    Map<String, Object>  orderContactInfoMapAvocado = new HashMap<>();
    Map<String, Object>  orderContactInfoMapDjo = new HashMap<>();

    int totalPrice = 0, totalPricePpizza = 0, totalPriceAvocado = 0, totalPriceDjo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_ordering);
        ButterKnife.bind(this);

        btnBack.setOnClickListener(v -> finish());

        FirebaseFirestore.getInstance()
                .collection("Users_Cart")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Оформление заказа")
                .document("Контакты")
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            orderNumber.setText(documentSnapshot.getString("Номер телефона")
                                    .substring(2));
                            orderName.setText(documentSnapshot.getString("Имя заказчика"));
                            orderAddress.setText(documentSnapshot.getString("Адрес"));
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CartOrderingActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

        orderDeliveryMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                    if (checkedId == R.id.radioButtonDelivery) {
                        orderDeliveryLayout.setVisibility(View.VISIBLE);
                    } else {
                        orderDeliveryLayout.setVisibility(View.GONE);
                    }
            }
        });

        orderDeliverySwitch.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return event.getActionMasked() == MotionEvent.ACTION_MOVE;
            }
        });

        orderDeliverySwitch.setOnClickListener(v -> {
            if (orderDeliverySwitch.isChecked()) {
                orderDeliveryTimeDate.setVisibility(View.GONE);
            } else {
                orderDeliveryTimeDate.setVisibility(View.VISIBLE);
            }
        });

        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();

        CalendarConstraints.DateValidator dateValidator = DateValidatorPointForward.now();

        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();
        constraintsBuilder.setValidator(dateValidator);
        builder.setCalendarConstraints(constraintsBuilder.build());

        MaterialDatePicker<Long> datePicker = builder.build();

        orderDate.setOnClickListener(v -> {
            datePicker.show(getSupportFragmentManager(), datePicker.toString());
            datePicker.addOnPositiveButtonClickListener((MaterialPickerOnPositiveButtonClickListener) selection -> orderDate.setText(datePicker.getHeaderText()));
        });



        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(hour)
                .setMinute(minute)
                .build();



        orderTime.setOnClickListener(v -> {

            timePicker.show(getSupportFragmentManager(), "fragment_tag");

            timePicker.addOnPositiveButtonClickListener((View.OnClickListener) selection -> {
                int newHour = timePicker.getHour();
                int newMinute = timePicker.getMinute();
                String time = String.format(Locale.getDefault(), "%02d:%02d", newHour, newMinute);
                orderTime.setText(time);
            });
        });



        orderButton();

    }

    private void orderButton() {
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String ORDER_NUMBER = orderNumber.getText().toString().trim();
                final String ORDER_NAME = orderName.getText().toString();
                final String ORDER_ADDRESS = orderAddress.getText().toString();
                final String ORDER_COMMENTARY = orderCommentary.getText().toString();
                final String ORDER_TIME = orderTime.getText().toString();
                final String ORDER_DATE = orderDate.getText().toString();

                if (TextUtils.isEmpty(ORDER_NAME)) {
                    orderName.setError("Не указано Ваше имя");
                    return;
                }
                if (TextUtils.isEmpty(ORDER_NUMBER)) {
                    orderNumber.setError("Не указан номер телефона");
                    return;
                }
                if (ORDER_NUMBER.length() < 10) {
                    orderNumber.setError("Неккоректный номер телефона");
                    return;
                }
                if (TextUtils.isEmpty(ORDER_ADDRESS)) {
                    orderAddress.setError("Не указан Ваш адрес доставки");
                    return;
                }
                if (!orderDeliverySwitch.isChecked()) {
                    if (TextUtils.isEmpty(ORDER_TIME)) {
                        orderTime.setError("Не указано время доставки");
                        return;
                    }
                    if (TextUtils.isEmpty(ORDER_DATE)) {
                        orderDate.setError("Не указана дата доставки");
                        return;
                    }
                }


                //Время заказа
                DateFormat dateFormat = new SimpleDateFormat("dd.MM yyyy г. в HH:mm");
                Date date = new Date();
                String strDate = dateFormat.format(date).toString();





                orderCustomerContacts(ORDER_NUMBER, ORDER_NAME, ORDER_ADDRESS);


                //Заказы

                final HashMap<String, Object> cartMap = new HashMap<>();
                final HashMap<String, Object> itemMap = new HashMap<>();

                final HashMap<String, Object> mailmap = new HashMap<>();

                List<CartModel> listGeneral = (ArrayList<CartModel>) getIntent().getSerializableExtra("itemList");

                List<CartModel> listPpizza = new ArrayList<>();
                List<CartModel> listAvocado = new ArrayList<>();
                List<CartModel> listDjo = new ArrayList<>();

                CollectionReference dbRef = FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Заказы");

                dbRef.add(itemMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String idDocument = documentReference.getId();

                        if (listGeneral != null && listGeneral.size() > 0) {
                            for (CartModel model : listGeneral) {

                                cartMap.put("Название", model.getItem_name());
                                cartMap.put("Цена", model.getItem_cost());
                                /*cartMap.put("item_image", model.getItem_image());*/
                                cartMap.put("Описание", model.getItem_details());
                                cartMap.put("Количество", model.getQuantity());
                                cartMap.put("Заведение", model.getId());
                                cartMap.put("Вариант блюда", model.getВариант_блюда());
                                cartMap.put("Доп ингредиенты", model.getДопы());

                                totalPrice += model.getTotalPrice();

                                switch (model.getId()) {
                                    case 1:
                                        FirebaseFirestore.getInstance()
                                                .collection("Users_Cart")
                                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .collection("Заказы")
                                                .document(idDocument)
                                                .update("PodkrePizza", FieldValue.arrayUnion(cartMap));

                                        totalPricePpizza += model.getTotalPrice();
                                        listPpizza.add(model);
                                        break;
                                    case 2:
                                        FirebaseFirestore.getInstance()
                                                .collection("Users_Cart")
                                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .collection("Заказы")
                                                .document(idDocument)
                                                .update("Avocado", FieldValue.arrayUnion(cartMap));

                                        totalPriceAvocado += model.getTotalPrice();
                                        listAvocado.add(model);
                                        break;
                                    case 3:
                                        FirebaseFirestore.getInstance()
                                                .collection("Users_Cart")
                                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .collection("Заказы")
                                                .document(idDocument)
                                                .update("Djo", FieldValue.arrayUnion(cartMap));

                                        totalPriceDjo += model.getTotalPrice();
                                        listDjo.add(model);
                                        break;
                                }
                                /*str = TextUtils.join(", ", listGeneral);*/
                                deleteCart(model);
                                /*str = Joiner.on("-").join(listGeneral.get(0));*/
                                /*str = model.toString();*/

                                //считалка итог. суммы
                                itemMap.put("totalPrice", totalPrice) ;
                                FirebaseFirestore.getInstance()
                                        .collection("Users_Cart")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Заказы")
                                        .document(idDocument)
                                        .update(itemMap);

                            }

                        }

/*                        cartModel.toString();
                        TextUtils.join(", ", listGeneral);*/



                        /*list2 = Arrays.asList(listGeneral);*/

                        //считалка итог. суммы
                        /*itemMap.put("totalPrice", totalPrice *//*model.getItem_cost()*//*) ;
                        FirebaseFirestore.getInstance()
                                .collection("Users_Cart")
                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .collection("Заказы")
                                .document(idDocument)
                                .update(itemMap);


                        try {
                            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(CartOrderingActivity.this.openFileOutput("config.txt", Context.MODE_APPEND));
                            outputStreamWriter.write(cartMap.toString());
                            outputStreamWriter.close();
                        }
                        catch (IOException e) {

                        }


                        String ret = "";

                        try {
                            InputStream inputStream = CartOrderingActivity.this.openFileInput("config.txt");

                            if ( inputStream != null ) {
                                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                                String receiveString = "";
                                StringBuilder stringBuilder = new StringBuilder();

                                while ( (receiveString = bufferedReader.readLine()) != null ) {
                                    stringBuilder.append("\n").append(receiveString);
                                }

                                inputStream.close();
                                ret = stringBuilder.toString();
                            }
                        }
                        catch (FileNotFoundException e) {

                        } catch (IOException e) {

                        }*/


/*                        StringBuilder sb = new StringBuilder();
                        for(CartModel model : listGeneral) {
                            sb.append(model);
                        }*/

                        //Оформление заказов
                        orderCustomerData(ORDER_NUMBER, ORDER_NAME, ORDER_ADDRESS, ORDER_COMMENTARY,
                                strDate, ORDER_TIME, ORDER_DATE);

                        //История заказов
                        OrderHistory(totalPrice,
                                ORDER_NAME,
                                ORDER_NUMBER,
                                ORDER_ADDRESS,
                                strDate,
                                ORDER_COMMENTARY);

                        //Отправка на почту
                        SendToEmail(idDocument, listGeneral, listPpizza, listAvocado, listDjo,
                                totalPrice);



/*                        String str = listGeneral.toString();
                        str = str.replaceAll("[\\[\\]]", "");*/

/*                        String ss = cartModel.toString();*/



/*                        StringBuilder sb = new StringBuilder();


                        for (int i = 1; i < listGeneral.size(); i++) {
                            ArrayList<String> oneRow = new ArrayList();
                            oneRow.add(String.valueOf(listGeneral.get(i)));
                            for (int j = 0; j < oneRow.size(); j++) {
                                sb.append(oneRow.get(j));
                            }
                        }*/

                        /*listGeneral.isEmpty();*/
                        /*listGeneral.removeAll(listGeneral);*/
                    }
/*                    public void moveFirestoreDocument(DocumentReference fromPath, final DocumentReference toPath) {
                        fromPath.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document != null) {
                                        toPath.set(document.getData())
                                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void aVoid) {
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                    }
                                                });
                                    }
                                });
                            }*/

                    //очистка корзины
                    private void deleteCart(CartModel model) {
                        FirebaseFirestore.getInstance()
                                .collection("Users_Cart")
                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .collection("Корзина")
                                .document(model.getKey())
                                .delete()
                                .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new MyUpdateCartEvent()));
                    }
                });
/*


                CollectionReference dbRef2 = FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Заказы");

                dbRef2.add(itemMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String idDocument = documentReference.getId();

                        List<CartModel> listGeneral = (ArrayList<CartModel>)
                                getIntent().getSerializableExtra("itemList");

                        int totalPrice = 0;

                        if (listGeneral != null && listGeneral.size() > 0) {
                            for (CartModel model : listGeneral) {

                                cartMap.put("Название", model.getItem_name());
                                cartMap.put("item_cost", model.getItem_cost());
                                cartMap.put("item_image", model.getItem_image());
                                cartMap.put("item_details", model.getItem_details());
                                cartMap.put("quantity", model.getQuantity());

                                totalPrice += model.getTotalPrice();

                                FirebaseFirestore.getInstance()
                                        .collection("Users_Cart")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Заказы")
                                        .document(idDocument)
                                        .update("items2", FieldValue.arrayUnion(cartMap));

                                */
/*DeleteCart(model);*//*


                            }
                        }



                       */
/* //считалка итог. суммы
                        itemMap.put("totalPrice", totalPrice*//*
*/
/* model.getItem_cost()*//*
*/
/*) ;
                        FirebaseFirestore.getInstance()
                                .collection("Users_Cart")
                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .collection("Заказы")
                                .document(idDocument)
                                .update(itemMap);

                        //История заказов
                        OrderHistory(totalPrice, ORDER_NAME, ORDER_NUMBER, ORDER_ADDRESS, strDate, ORDER_COMMENTARY);*//*


                    }

                    */
/*//*
/очистка корзины
                    private void DeleteCart(CartModel model) {
                        FirebaseFirestore.getInstance()
                                .collection("Users_Cart")
                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .collection("Корзина")
                                .document(model.getKey())
                                .delete()
                                .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new MyUpdateCartEvent()));
                    }*//*


                });


*/





                /**CollectionReference dbRef = FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Заказы");

                dbRef.add(itemMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String idDocument = documentReference.getId();

                        List<CartModel> listGeneral = (ArrayList<CartModel>)
                                getIntent().getSerializableExtra("itemList");

                        int totalPrice = 0;

                        if (listGeneral != null && listGeneral.size() > 0) {
                            for (CartModel model : listGeneral) {

                                cartMap.put("Название", model.getItem_name());
                                cartMap.put("item_cost", model.getItem_cost());
                                cartMap.put("item_image", model.getItem_image());
                                cartMap.put("item_details", model.getItem_details());
                                cartMap.put("quantity", model.getQuantity());

                                totalPrice += model.getTotalPrice();

                                FirebaseFirestore.getInstance()
                                        .collection("Users_Cart")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Заказы")
                                        .document(idDocument)
                                        .update("items", FieldValue.arrayUnion(cartMap));

                                *//*DeleteCart(model);*//*

                            }
                        }

*//*

                        //считалка итог. суммы
                        itemMap.put("totalPrice", totalPrice model.getItem_cost()) ;
                        FirebaseFirestore.getInstance()
                                .collection("Users_Cart")
                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .collection("Заказы")
                                .document(idDocument)
                                .update(itemMap);

                        //История заказов
                        OrderHistory(totalPrice, ORDER_NAME, ORDER_NUMBER, ORDER_ADDRESS, strDate, ORDER_COMMENTARY);*//*

                    }
*//*
                    //очистка корзины
                    private void DeleteCart(CartModel model) {
                        FirebaseFirestore.getInstance()
                                .collection("Users_Cart")
                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .collection("Корзина")
                                .document(model.getKey())
                                .delete()
                                .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new MyUpdateCartEvent()));
                    }*//*
                });*/




                //Начисление бонусов пользователю
                UserBonusCounter();

                Intent intent = new Intent(getApplicationContext(), OrderPlacedActivity.class);
                startActivity(intent);

            }

            private void orderCustomerContacts(String ORDER_NUMBER, String ORDER_NAME, String ORDER_ADDRESS) {
                Map<String, Object>  orderContactMap = new HashMap<>();
                orderContactMap.put("Имя заказчика", ORDER_NAME);
                orderContactMap.put("Номер телефона", "+7" + ORDER_NUMBER);
                orderContactMap.put("Адрес", ORDER_ADDRESS);

                FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Оформление заказа")
                        .document("Контакты")
                        .set(orderContactMap);
            }

            private void SendToEmail(String idDocument, List<CartModel> listGeneral,
                                     List<CartModel> listPpizza,  List<CartModel> listAvocado,
                                     List<CartModel> listDjo, int totalPrice) {

                String cartToMail = TextUtils.join(", ", listGeneral);

                String orderContactInfoMapStr = Joiner.on("<br>").withKeyValueSeparator(" ").join(orderContactInfoMap);

                Map<String,Object> message = new HashMap<>();
                message.put("subject", "!! Новый заказ в FoodМаркет!");
                message.put("html", orderContactInfoMapStr +
                        "<br><br>Заказанные товары:<br>" + cartToMail +
                        "<br><br>Идентификатор заказа: " + idDocument);

                Map<String,Object> mail = new HashMap<>();
                mail.put("to", "skyendofmind@gmail.com");
                mail.put("message", message);

                FirebaseFirestore.getInstance()
                        .collection("mail")
                        .add(mail);

                CartModel.resetCounter();


                if (listPpizza.isEmpty()) {
                    return;
                }

                String cartToMailPpizza = TextUtils.join(", ", listPpizza);

                String orderContactInfoMapStrPizza = Joiner.on("<br>").withKeyValueSeparator(" ").join(orderContactInfoMapPpizza);

                Map<String,Object> messagePpizza = new HashMap<>();
                messagePpizza.put("subject", "!! Ваш заказ от FoodМаркет! -PodkrePizza");
                messagePpizza.put("html","<b>Добрый день PodkrePizza!</b>" +
                        "<br>Новый заказ от FoodMarket!<br>" + orderContactInfoMapStrPizza +
                        "<br><br>Заказанные товары:<br>" + cartToMailPpizza +
                        "<br><br>Идентификатор заказа: " + idDocument);

                Map<String,Object> mailPpizza = new HashMap<>();
                mailPpizza.put("to", "skyendofmind@gmail.com");
                mailPpizza.put("message", messagePpizza);

                FirebaseFirestore.getInstance()
                        .collection("mail")
                        .add(mailPpizza);

                CartModel.resetCounter();


                if (listAvocado.isEmpty()) {
                    return;
                }

                String cartToMailAvocado= TextUtils.join(", ", listAvocado);

                String orderContactInfoMapStrAvocado = Joiner.on("<br>").withKeyValueSeparator(" ").join(orderContactInfoMapAvocado);

                Map<String,Object> messageAvocado = new HashMap<>();
                messageAvocado.put("subject", "!! Ваш заказ от FoodМаркет! -Avocado");
                messageAvocado.put("html","<b>Добрый день Avocado!</b>" +
                        "<br>Новый заказ от FoodMarket!<br>" + orderContactInfoMapStrAvocado +
                        "<br><br>Заказанные товары:<br>" + cartToMailAvocado +
                        "<br><br>Идентификатор заказа: " + idDocument);

                Map<String,Object> mailAvocado = new HashMap<>();
                mailAvocado.put("to", "skyendofmind@gmail.com");
                mailAvocado.put("message", messageAvocado);

                FirebaseFirestore.getInstance()
                        .collection("mail")
                        .add(mailAvocado);

                CartModel.resetCounter();


                if (listDjo.isEmpty()) {
                    return;
                }

                String cartToMailDjo= TextUtils.join(", ", listDjo);

                String orderContactInfoMapStrDjo = Joiner.on("<br>").withKeyValueSeparator(" ").join(orderContactInfoMapDjo);

                Map<String,Object> messageDjo = new HashMap<>();
                messageDjo.put("subject", "!! Ваш заказ от FoodМаркет! -Джо");
                messageDjo.put("html","<b>Добрый день Джо!</b>" +
                        "<br>Новый заказ от FoodMarket!<br>" + orderContactInfoMapStrDjo +
                        "<br><br>Заказанные товары:<br>" + cartToMailDjo +
                        "<br><br>Идентификатор заказа: " + idDocument);

                Map<String,Object> mailDjo = new HashMap<>();
                mailDjo.put("to", "skyendofmind@gmail.com");
                mailDjo.put("message", messageDjo);

                FirebaseFirestore.getInstance()
                        .collection("mail")
                        .add(mailDjo);

                CartModel.resetCounter();

            }

            private void UserBonusCounter() {
                if (orderDeliveryMethod.getCheckedRadioButtonId() == R.id.radioButtonSelfDelivery) {
                    return;
                } else {
                    FirebaseFirestore.getInstance()
                            .collection("user")
                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    if (documentSnapshot.exists()) {
                                        int bonus = documentSnapshot.getLong("bonus").intValue();
                                        BonusModel bonusModel = documentSnapshot.toObject(BonusModel.class);

                                        int bonuses = Integer.parseInt(getIntent().getStringExtra("bonus"));
                                        String buttonText = getIntent().getStringExtra("btnGetBonus");

                                        if (buttonText.equals("Сбросить")) {
                                            if (bonus != 0) {
                                                bonusModel.setBonus(bonusModel.getBonus() - bonuses);
                                            }
                                        }

                                        bonusModel.setBonus(bonusModel.getBonus() + 15);

                                        Map<String,Object> bonusModelMap = new HashMap<>();
                                        bonusModelMap.put("bonus", bonusModel.getBonus());

                                        FirebaseFirestore.getInstance()
                                                .collection("user")
                                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .update(bonusModelMap)
                                                .addOnSuccessListener(aVoid -> {

                                                });
                                    }
                                }
                            });
                }
            }

            private void OrderHistory(int totalPrice,
                                      String OrderName,
                                      String OrderNumber,
                                      String OrderAddress,
                                      String strDate,
                                      String OrderCommentary) {
                /*int totalPriceHistory = totalPrice;*/

                FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("История заказов")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    int orderNumberCount = 1;
                                    for (DocumentSnapshot document : task.getResult()) {
                                        orderNumberCount++;
                                    }

                                    Map<String, Object>  orderHistoryMap = new HashMap<>();
                                    orderHistoryMap.put("Имя", OrderName);
                                    orderHistoryMap.put("Номер_телефона", "+7" + OrderNumber);
                                    orderHistoryMap.put("Адрес_доставки", OrderAddress);
                                    orderHistoryMap.put("Дата_и_время", strDate);
                                    orderHistoryMap.put("Комментарий", OrderCommentary);
                                    orderHistoryMap.put("Сумма_заказа", getIntent().getIntExtra("totalSum", 0) /*totalPriceHistory*/);
                                    orderHistoryMap.put("Номер_заказа", orderNumberCount);

                                    if (orderPayment.getCheckedRadioButtonId() == R.id.radioButtonCash){
                                        orderHistoryMap.put("Способ_оплаты_курьеру", "Наличными курьеру");
                                    } else {
                                        orderHistoryMap.put("Способ_оплаты_курьеру", "Картой курьеру");
                                    }

                                    FirebaseFirestore.getInstance()
                                            .collection("Users_Cart")
                                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .collection("История заказов")
                                            .add(orderHistoryMap);

                                    /*Toast.makeText(getApplicationContext(), "Заявка отправлена!",Toast.LENGTH_SHORT).show();*/
                                } else {

                                }
                            }
                        });
            }

            private void orderCustomerData(String OrderNumber,
                                           String OrderName,
                                           String OrderAddress,
                                           String OrderCommentary,
                                           String strDate,
                                           String orderTime,
                                           String orderDate) {

                int deliverySum = getIntent().getIntExtra("deliverySum", 0);
                int totalSum = getIntent().getIntExtra("totalSum", 0);

                orderPpizza(OrderNumber, OrderName, OrderAddress, OrderCommentary, strDate);

                orderAvocado(OrderNumber, OrderName, OrderAddress, OrderCommentary, strDate);

                orderDjo(OrderNumber, OrderName, OrderAddress, OrderCommentary, strDate);


                orderContactInfoMap.put("Имя заказчика:", OrderName);
                orderContactInfoMap.put("Номер телефона:", "+7" + OrderNumber);
                orderContactInfoMap.put("Адрес:", OrderAddress);
                orderContactInfoMap.put("Время отправки заявки:", strDate);
                orderContactInfoMap.put("Комментарий заказчика:", OrderCommentary);

                if (orderPayment.getCheckedRadioButtonId() == R.id.radioButtonCash){
                    orderContactInfoMap.put("Способ оплаты:", "Наличными курьеру");
                } else {
                    orderContactInfoMap.put("Способ оплаты:", "Картой курьеру");
                }

                if (orderDeliveryMethod.getCheckedRadioButtonId() == R.id.radioButtonDelivery){
                    orderContactInfoMap.put("Способ доставки:", "Доставка");
                    orderContactInfoMap.put("Сумма доставка:", deliverySum  + " ₽");
                    orderContactInfoMap.put("Общая сумма заказа:", totalSum  + " ₽");
                } else {
                    orderContactInfoMap.put("Способ доставки:", "Самовывоз");
                    orderContactInfoMap.put("Общая сумма заказа:", totalSum - deliverySum  + " ₽");
                }

                if (orderDeliverySwitch.isChecked()) {
                    return;
                } else {
                    orderContactInfoMap.put("Желаемое время доставки:", orderTime);
                    orderContactInfoMap.put("Желаемая дата доставки:", orderDate);
                }

                FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Оформление заказа")
                        .add(orderContactInfoMap);
            }

            private void orderPpizza(String OrderNumber,
                                     String OrderName,
                                     String OrderAddress,
                                     String OrderCommentary,
                                     String strDate) {
                orderContactInfoMapPpizza.put("Имя заказчика:", OrderName);
                orderContactInfoMapPpizza.put("Номер телефона:", "+7" + OrderNumber);
                orderContactInfoMapPpizza.put("Адрес:", OrderAddress);
                orderContactInfoMapPpizza.put("Время отправки заявки:", strDate);
                orderContactInfoMapPpizza.put("Комментарий заказчика:", OrderCommentary);
                orderContactInfoMapPpizza.put("Общая сумма заказа:", totalPricePpizza  + " ₽");

                if (orderDeliverySwitch.isChecked()) {
                    return;
                } else {
                    orderContactInfoMapPpizza.put("Желаемое время доставки:", orderTime);
                    orderContactInfoMapPpizza.put("Желаемая дата доставки:", orderDate);
                }
            }

            private void orderAvocado(String OrderNumber,
                                      String OrderName,
                                      String OrderAddress,
                                      String OrderCommentary,
                                      String strDate) {
                orderContactInfoMapAvocado.put("Имя заказчика:", OrderName);
                orderContactInfoMapAvocado.put("Номер телефона:", "+7" + OrderNumber);
                orderContactInfoMapAvocado.put("Адрес:", OrderAddress);
                orderContactInfoMapAvocado.put("Время отправки заявки:", strDate);
                orderContactInfoMapAvocado.put("Комментарий заказчика:", OrderCommentary);
                orderContactInfoMapAvocado.put("Общая сумма заказа:", totalPriceAvocado  + " ₽");

                if (orderDeliverySwitch.isChecked()) {
                    return;
                } else {
                    orderContactInfoMapAvocado.put("Желаемое время доставки:", orderTime);
                    orderContactInfoMapAvocado.put("Желаемая дата доставки:", orderDate);
                }
            }

            private void orderDjo(String OrderNumber,
                                  String OrderName,
                                  String OrderAddress,
                                  String OrderCommentary,
                                  String strDate) {
                orderContactInfoMapDjo.put("Имя заказчика:", OrderName);
                orderContactInfoMapDjo.put("Номер телефона:", "+7" + OrderNumber);
                orderContactInfoMapDjo.put("Адрес:", OrderAddress);
                orderContactInfoMapDjo.put("Время отправки заявки:", strDate);
                orderContactInfoMapDjo.put("Комментарий заказчика:", OrderCommentary);
                orderContactInfoMapDjo.put("Общая сумма заказа:", totalPriceDjo + " ₽");

                if (orderDeliverySwitch.isChecked()) {
                    return;
                } else {
                    orderContactInfoMapAvocado.put("Желаемое время доставки:", orderTime);
                    orderContactInfoMapAvocado.put("Желаемая дата доставки:", orderDate);
                }
            }

        });
    }


}