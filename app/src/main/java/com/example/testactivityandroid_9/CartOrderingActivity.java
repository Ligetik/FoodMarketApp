package com.example.testactivityandroid_9;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.testactivityandroid_9.eventbus.MyUpdateCartEvent;
import com.example.testactivityandroid_9.model.BonusModel;
import com.example.testactivityandroid_9.model.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
import java.util.Date;
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
    @BindView(R.id.orderPayment)
    RadioGroup orderPayment;
    @BindView(R.id.radioButtonCash)
    RadioButton radioButtonCash;

    List<CartModel>  cartModelList;
    CartModel cartModel;
    List<Object> list2;
    Map<String, Object>  orderContactInfoMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_ordering);
        ButterKnife.bind(this);

        btnBack.setOnClickListener(v -> finish());

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String OrderNumber = orderNumber.getText().toString().trim();
                final String OrderName = orderName.getText().toString();
                final String OrderAddress = orderAddress.getText().toString();
                final String OrderCommentary = orderCommentary.getText().toString();

                if (TextUtils.isEmpty(OrderName)) {
                    orderName.setError("Не указано Ваше имя");
                    return;
                }
                if (TextUtils.isEmpty(OrderNumber)) {
                    orderNumber.setError("Не указан номер телефона");
                    return;
                }
                if (OrderNumber.length() < 10) {
                    orderNumber.setError("Неккоректный номер телефона");
                    return;
                }
                if (TextUtils.isEmpty(OrderAddress)) {
                    orderAddress.setError("Не указан Ваш адрес доставки");
                    return;
                }

                DateFormat dateFormat = new SimpleDateFormat("dd.MM yyyy г. в HH:mm");
                Date date = new Date();
                String strDate = dateFormat.format(date).toString();

                //Оформление заказов
                OrderContactInfo(OrderNumber, OrderName, OrderAddress, OrderCommentary, strDate);

                //Заказы

                final HashMap<String, Object> cartMap = new HashMap<>();
                final HashMap<String, Object> itemMap = new HashMap<>();

                final HashMap<String, Object> mailmap = new HashMap<>();

                CollectionReference dbRef = FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Заказы");

                dbRef.add(itemMap).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        String idDocument = documentReference.getId();

                        List<CartModel> list = (ArrayList<CartModel>)
                                getIntent().getSerializableExtra("itemList");


                        int totalPrice = 0;

                        if (list != null && list.size() > 0) {
                            for (CartModel model : list) {

                                cartMap.put("Название", model.getItem_name());
                                cartMap.put("Цена", model.getItem_cost());
                                /*cartMap.put("item_image", model.getItem_image());*/
                                cartMap.put("Описание", model.getItem_details());
                                cartMap.put("Количество", model.getQuantity());
                                cartMap.put("Заведение", model.getId());
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
                                        break;
                                    case 2:
                                        FirebaseFirestore.getInstance()
                                                .collection("Users_Cart")
                                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .collection("Заказы")
                                                .document(idDocument)
                                                .update("Avocado", FieldValue.arrayUnion(cartMap));
                                        break;
                                    case 3:
                                        FirebaseFirestore.getInstance()
                                                .collection("Users_Cart")
                                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .collection("Заказы")
                                                .document(idDocument)
                                                .update("Djo", FieldValue.arrayUnion(cartMap));
                                        break;
                                }
                                /*str = TextUtils.join(", ", list);*/
                                DeleteCart(model);
                                /*str = Joiner.on("-").join(list.get(0));*/

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
                        TextUtils.join(", ", list);*/



                        /*list2 = Arrays.asList(list);*/

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
                        for(CartModel model : list) {
                            sb.append(model);
                        }*/


                        //История заказов
                        OrderHistory(totalPrice,
                                OrderName,
                                OrderNumber,
                                OrderAddress,
                                strDate,
                                OrderCommentary);

                        //Отправка на почту
                        SendToEmail(idDocument, list, totalPrice);

/*                        String str = list.toString();
                        str = str.replaceAll("[\\[\\]]", "");*/

/*                        String ss = cartModel.toString();*/



/*                        StringBuilder sb = new StringBuilder();


                        for (int i = 1; i < list.size(); i++) {
                            ArrayList<String> oneRow = new ArrayList();
                            oneRow.add(String.valueOf(list.get(i)));
                            for (int j = 0; j < oneRow.size(); j++) {
                                sb.append(oneRow.get(j));
                            }
                        }*/


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
                    private void DeleteCart(CartModel model) {
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

                        List<CartModel> list = (ArrayList<CartModel>)
                                getIntent().getSerializableExtra("itemList");

                        int totalPrice = 0;

                        if (list != null && list.size() > 0) {
                            for (CartModel model : list) {

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
                        OrderHistory(totalPrice, OrderName, OrderNumber, OrderAddress, strDate, OrderCommentary);*//*


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

                        List<CartModel> list = (ArrayList<CartModel>)
                                getIntent().getSerializableExtra("itemList");

                        int totalPrice = 0;

                        if (list != null && list.size() > 0) {
                            for (CartModel model : list) {

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
                        OrderHistory(totalPrice, OrderName, OrderNumber, OrderAddress, strDate, OrderCommentary);*//*

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

            private void SendToEmail(String idDocument, List<CartModel> list, int totalPrice) {

                String cartToMail = TextUtils.join(", ", list);

                String orderContactInfoMapStr = Joiner.on("<br>").withKeyValueSeparator(" ").join(orderContactInfoMap);

                Map<String,Object> message = new HashMap<>();
                message.put("subject", "Заказ №: " + idDocument);
                message.put("html", orderContactInfoMapStr +
                        "<br><br>Заказанные товары:<br>" /*+ cartModel.getId() == 1 */ +
                        cartToMail);

                Map<String,Object> mail = new HashMap<>();
                mail.put("to", "skyendofmind@gmail.com");
                mail.put("message", message);

                FirebaseFirestore.getInstance()
                        .collection("mail")
                        .add(mail);
            }

            private void UserBonusCounter() {
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

            private void OrderHistory(int totalPrice, String OrderName, String OrderNumber, String OrderAddress, String strDate, String OrderCommentary) {
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
                                    orderHistoryMap.put("Сумма_заказа", getIntent().getStringExtra("totalSum") /*totalPriceHistory*/);
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

            private void OrderContactInfo(String OrderNumber,
                                          String OrderName,
                                          String OrderAddress,
                                          String OrderCommentary,
                                          String strDate) {

                orderContactInfoMap.put("Имя заказчика: ", OrderName);
                orderContactInfoMap.put("Номер телефона: ", "+7" + OrderNumber);
                orderContactInfoMap.put("Адрес: ", OrderAddress);
                orderContactInfoMap.put("Время отправки заявки: ", strDate);
                orderContactInfoMap.put("Комментарий заказчика: ", OrderCommentary);
                orderContactInfoMap.put("Сумма доставка: ", getIntent().getStringExtra("deliverySum"));
                orderContactInfoMap.put("Общая сумма заказа: ", getIntent().getStringExtra("totalSum"));


                if (orderPayment.getCheckedRadioButtonId() == R.id.radioButtonCash){
                    orderContactInfoMap.put("Способ оплаты курьеру", "Наличными курьеру");
                } else {
                    orderContactInfoMap.put("Способ оплаты курьеру", "Картой курьеру");
                }

                FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Оформление заказа")
                        .add(orderContactInfoMap);
            }

        });



    }
}