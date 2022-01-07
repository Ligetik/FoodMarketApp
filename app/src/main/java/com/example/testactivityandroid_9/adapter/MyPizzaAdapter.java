package com.example.testactivityandroid_9.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testactivityandroid_9.DetailedActivity;
import com.example.testactivityandroid_9.R;
import com.example.testactivityandroid_9.eventbus.MyUpdateCartEvent;
import com.example.testactivityandroid_9.listener.ICartLoadListener;
import com.example.testactivityandroid_9.listener.IRecyclerViewClickListener;
import com.example.testactivityandroid_9.model.CartModel;
import com.example.testactivityandroid_9.model.PPpizzaModel;
import com.example.testactivityandroid_9.ui.login.signup.Login_SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MyPizzaAdapter extends RecyclerView.Adapter<MyPizzaAdapter.MyPizzaViewHolder> {

    private Context context;
    private List<PPpizzaModel> ppizzaModelList;
    private ICartLoadListener iCartLoadListener;
    private List<CartModel> cartModelList;
    private  FirebaseFirestore db;
    private static final String TAG = "MyActivity";

    public MyPizzaAdapter(Context context, List<PPpizzaModel> ppizzaModelList, ICartLoadListener iCartLoadListener) {
        this.context = context;
        this.ppizzaModelList = ppizzaModelList;
        this.iCartLoadListener = iCartLoadListener;

    }

    @NonNull
    @Override
    public MyPizzaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyPizzaViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.tovar_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyPizzaViewHolder holder, int position) {
        Glide.with(context)
                .load(ppizzaModelList.get(position).getItem_image())
                .into(holder.imagetovar);

        holder.textMoney.setText(new StringBuilder().append(ppizzaModelList.get(position).getItem_cost()));
        holder.textName.setText(new StringBuilder().append(ppizzaModelList.get(position).getItem_name()));
        holder.descrName.setText(new StringBuilder().append(ppizzaModelList.get(position).getItem_details()));
        holder.titleWeight.setText(new StringBuilder().append(ppizzaModelList.get(position).getItem_weight()));

        holder.btnAddToCart.setOnClickListener(v -> {
            addToCart(ppizzaModelList.get(position));
        });

/*        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedActivity.class);
                intent.putExtra("details", ppizzaModelList.get(position));
                context.startActivity(intent);
            }
        });*/

        holder.setListener((view, adapterPosition) -> {

            Intent intent = new Intent(context, DetailedActivity.class);
            intent.putExtra("details", ppizzaModelList.get(position));
            context.startActivity(intent);

        });

    }

    private void addToCart(PPpizzaModel pPpizzaModel) {
/*        try {
            FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
                FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Корзина")
                        .document(pPpizzaModel.getKey())
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                DocumentSnapshot document = task.getResult();
                                Map<String, Object> dummyHashMap = document.getData();

                                for (Object x: dummyHashMap.values()) {
                                    for (Object value: ((Map<String, Object>)x).values()){

                                        dummyHashMap.put("item_name", pPpizzaModel.getItem_name());
                                        dummyHashMap.put("item_cost", pPpizzaModel.getItem_cost());
                                        dummyHashMap.put("item_image", pPpizzaModel.getItem_image());
                                        dummyHashMap.put("item_details", pPpizzaModel.getItem_details());
                                        dummyHashMap.put("quantity", 1);
                                    }
                                }

                                final HashMap<String, Object> cartMap = new HashMap<>();
                                cartMap.put("totalPrice", pPpizzaModel.getItem_cost());
                                cartMap.put("details",Arrays.asList(dummyHashMap));

                               *//* FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {*//*
                                    FirebaseFirestore.getInstance()
                                            .collection("Users_Cart")
                                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .collection("Корзина")
                                            .add(cartMap)
                                            .addOnSuccessListener(aVoid -> {
                                                iCartLoadListener.OnCartloadFailed("Добавлено");
                                            })
                                            .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));
                                *//*});*//*

                                *//*}*//*
                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                            }
                        });

                               *//* if (documentSnapshot.exists()) //если у пользователя уже есть товар в корзине
                                { // Обновляет количество и общую цену
                                    CartModel cartModel = documentSnapshot.toObject(CartModel.class);

                                    cartModel.setQuantity(cartModel.getQuantity()+1);
                                    Map<String,Object> updateData = new HashMap<>();
                                    updateData.put("quantity", cartModel.getQuantity());
                                    updateData.put("totalPrice", cartModel.getQuantity() * cartModel.getItem_cost());

                                    FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
                                        FirebaseFirestore.getInstance()
                                                .collection("Users_Cart")
                                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .collection("Корзина")
                                                .document(pPpizzaModel.getKey())
                                                .update(updateData)
                                                .addOnSuccessListener(aVoid -> {

                                                    iCartLoadListener.OnCartloadFailed("Добавлено");
                                                })
                                                .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));
                                    });
                                }*//*
                                *//*else // если в корзине нет предмета, то добавить новый
                                {*//*

                                     *//* final HashMap<String, Object> cartMap = new HashMap<>();*//*
*//*                                    cartMap.put("item_name", pPpizzaModel.getItem_name());
                                    cartMap.put("item_cost", pPpizzaModel.getItem_cost());
                                    cartMap.put("item_image", pPpizzaModel.getItem_image());
                                    cartMap.put("item_details", pPpizzaModel.getItem_details());
                                    cartMap.put("key", pPpizzaModel.getKey());
                                    cartMap.put("totalPrice", pPpizzaModel.getItem_cost());
                                    cartMap.put("quantity", 1);
                                    cartMap.put("details", pPpizzaModel.getDetails());*//*

                                *//*final HashMap<String, Object> details = new HashMap<>();*//*

*//*                                String[] array = {"One", "Two", "Three"};
                                String[] array2 = {"One", "Two", "Three"};*//*
*//*
                                String[] names = {"Petya", "Masha", "Vasya"};
                                List<String> list = Arrays.asList(names);
                                names[0] = "John";

                                List<String> newList = new ArrayList<>(list);*//*

*//*                                Map<String,Object> dummyHashMap = HashMap<String,Object>() {{

                                }};*//*


            });
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }*/



                    //говной

       /* try {
            FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
                FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Корзина")
                        .document(pPpizzaModel.getKey())
                        .get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
*//*                             if (documentSnapshot.exists()) //если у пользователя уже есть товар в корзине
                                { // Обновляет количество и общую цену
                                    CartModel cartModel = documentSnapshot.toObject(CartModel.class);

                                    cartModel.setQuantity(cartModel.getQuantity()+1);
                                    Map<String,Object> updateData = new HashMap<>();
                                    updateData.put("quantity", cartModel.getQuantity());
                                    updateData.put("totalPrice", cartModel.getQuantity() * cartModel.getItem_cost());

                                    FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
                                        FirebaseFirestore.getInstance()
                                                .collection("Users_Cart")
                                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .collection("Корзина")
                                                .document(pPpizzaModel.getKey())
                                                .update(updateData)
                                                .addOnSuccessListener(aVoid -> {

                                                    iCartLoadListener.OnCartloadFailed("Добавлено");
                                                })
                                                .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));
                                    });
                                }*//*

*//*                             else // если в корзине нет предмета, то добавить новый
                                {*//*


*//*final HashMap<String, Object> cartMap = new HashMap<>();

                                    cartMap.put("item_name", pPpizzaModel.getItem_name());
                                    cartMap.put("item_cost", pPpizzaModel.getItem_cost());
                                    cartMap.put("item_image", pPpizzaModel.getItem_image());
                                    cartMap.put("item_details", pPpizzaModel.getItem_details());
                                    cartMap.put("key", pPpizzaModel.getKey());
                                    cartMap.put("totalPrice", pPpizzaModel.getItem_cost());
                                    cartMap.put("quantity", 1);
                                    cartMap.put("details", pPpizzaModel.getDetails());*//*


*//*            final HashMap<String, Object> details = new HashMap<>();


                                String[] array = {"One", "Two", "Three"};
                                String[] array2 = {"One", "Two", "Three"};

                                String[] names = {"Petya", "Masha", "Vasya"};
                                List<String> list = Arrays.asList(names);
                                names[0] = "John";

                                List<String> newList = new ArrayList<>(list);*//*




                                Map<String,Object> dummyHashMap = new HashMap<String,Object>() {{

                                }};

                                for (Map.Entry entry: dummyHashMap.entrySet()) {

*//*                                    dummyHashMap.put("item_name", pPpizzaModel.getItem_name());
                                    dummyHashMap.put("item_cost", pPpizzaModel.getItem_cost());
                                    dummyHashMap.put("item_image", pPpizzaModel.getItem_image());
                                    dummyHashMap.put("item_details", pPpizzaModel.getItem_details());
                                    dummyHashMap.put("quantity", 1);*//*

                                }

*//*                                DocumentSnapshot document = documentSnapshot;
                                Map<String, Object> dummyHashMap = document.getData();*//*

*//*                                for (Object x: dummyHashMap.values()) {
                                    for (Object value: ((Map<String, Object>)x).values()){
                                        dummyHashMap.get(value);
                                    }
                                }*//*

                                final HashMap<String, Object> cartMap = new HashMap<>();
                                cartMap.put("totalPrice", pPpizzaModel.getItem_cost());
                                cartMap.put("details",Arrays.asList(dummyHashMap));

                                FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
                                    FirebaseFirestore.getInstance()
                                            .collection("Users_Cart")
                                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .collection("Корзина")
                                            .add(cartMap)
                                            .addOnSuccessListener(aVoid -> {
                                                iCartLoadListener.OnCartloadFailed("Добавлено");
                                            })
                                            .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));
                                });

                *//*}*//*

                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());

                            }
                        });
            });
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }*/








                    //new5_2    РАБОТАЕТ

        try {
            FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
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

                                    FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
                                        FirebaseFirestore.getInstance()
                                                .collection("Users_Cart")
                                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .collection("Корзина")
                                                .document(pPpizzaModel.getKey())
                                                .update(updateData)
                                                .addOnSuccessListener(aVoid -> {

                                                    iCartLoadListener.OnCartloadFailed("Добавлено");
                                                })
                                                .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));
                                    });
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

                                    FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
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
                                    });
                                }
                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());

                            }
                        });
            });
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }


                //new 5_1 рабочий, но сломана считалка кол-ва
       /* try {
            FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
                FirebaseFirestore.getInstance()
                        .collection("Users_Cart")
                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                        .collection("Корзина")
                        .document(pPpizzaModel.getKey())
                        .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                            @Override
                            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                if (value.exists()) //если у пользователя уже есть товар в корзине
                                { // Обновляет количество и общую цену
                                    CartModel cartModel = value.toObject(CartModel.class);

                                    *//*cartModel.setQuantity(cartModel.getQuantity()+1);*//*
                                    Map<String,Object> updateData = new HashMap<>();
                                    updateData.put("quantity", cartModel.getQuantity());
                                    updateData.put("totalPrice", cartModel.getQuantity() * cartModel.getItem_cost());

                                    FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
                                        FirebaseFirestore.getInstance()
                                                .collection("Users_Cart")
                                                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .collection("Корзина")
                                                .document(pPpizzaModel.getKey())
                                                .update(updateData)
                                                .addOnSuccessListener(aVoid -> {

                                                    iCartLoadListener.OnCartloadFailed("Добавлено");
                                                })
                                                .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));
                                    });
                                }
                                else // если в корзине нет предмета, то добавить новый
                                {
                                    CartModel cartModel = new CartModel();
                                    cartModel.setItem_name(pPpizzaModel.getItem_name());
                                    cartModel.setItem_image(pPpizzaModel.getItem_image());
                                    cartModel.setKey(pPpizzaModel.getKey());
                                    cartModel.setItem_cost(pPpizzaModel.getItem_cost());
                                    cartModel.setQuantity(1);
                                    cartModel.setTotalPrice(pPpizzaModel.getItem_cost());

                                    FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
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
                                    });
                                }
                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                            }
                        });
            });
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }*/



        //new5 Добавлен key работает +-
       /* try {
            CartModel cartModel = new CartModel();
            cartModel.setItem_name(pPpizzaModel.getItem_name());
            cartModel.setItem_image(pPpizzaModel.getItem_image());
            cartModel.setKey(pPpizzaModel.getKey());
            cartModel.setItem_cost(pPpizzaModel.getItem_cost());
            cartModel.setQuantity(1);
            cartModel.setTotalPrice(pPpizzaModel.getItem_cost());


            FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
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
            });
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }*/

        //new 4
/*        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
            FirebaseFirestore.getInstance()
                    .collection("Users_Cart")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .collection("Корзина")
                    .document(pPpizzaModel.getKey())
                    .addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                            CartModel cartModel = new CartModel();
                            cartModel.setItem_name(pPpizzaModel.getItem_name());
                            cartModel.setItem_image(pPpizzaModel.getItem_image());
                            cartModel.setKey(pPpizzaModel.getKey());
                            cartModel.setItem_cost(pPpizzaModel.getItem_cost());
                            cartModel.setQuantity(1);
                            cartModel.setTotalPrice(pPpizzaModel.getItem_cost());

                            FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
                                FirebaseFirestore.getInstance().collection("Users_Cart")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Корзина")
                                        .document(pPpizzaModel.getKey())
                                        .set(cartModel)
                                        *//*.addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                                iCartLoadListener.OnCartloadFailed("Добавлено");
                                            }
                                        });*//*
                                        .addOnSuccessListener(aVoid -> {
                                            iCartLoadListener.OnCartloadFailed("Добавлено");
                                        })
                                        .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));
                                EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                            });
                        }
                    });
        });*/


//new3 +- рабочий
       /* CartModel cartModel = new CartModel();
        cartModel.setItem_name(pPpizzaModel.getItem_name());
        cartModel.setItem_image(pPpizzaModel.getItem_image());
        cartModel.setKey(pPpizzaModel.getKey());
        cartModel.setItem_cost(pPpizzaModel.getItem_cost());
        cartModel.setQuantity(1);
        cartModel.setTotalPrice(pPpizzaModel.getItem_cost());


        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
            FirebaseFirestore.getInstance().collection("Users_Cart")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .collection("Корзина")
                    .add(cartModel)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    iCartLoadListener.OnCartloadFailed("Добавлено");
                }
            });
            EventBus.getDefault().postSticky(new MyUpdateCartEvent());
        });*/



        //new2

        /*FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
            FirebaseFirestore.getInstance()
                    .collection("Users_Cart")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .collection("Корзина")
                    .document(pPpizzaModel.getKey())
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    CartModel cartModel = new CartModel();
                    cartModel.setItem_name(pPpizzaModel.getItem_name());
                    cartModel.setItem_image(pPpizzaModel.getItem_image());
                    cartModel.setKey(pPpizzaModel.getKey());
                    cartModel.setItem_cost(pPpizzaModel.getItem_cost());
                    cartModel.setQuantity(1);
                    cartModel.setTotalPrice(pPpizzaModel.getItem_cost());

                    FirebaseFirestore.getInstance()
                            .collection("Users_Cart")
                            .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .collection("Корзина")
                            .add(cartModel)
                            .addOnSuccessListener(aVoid -> {
                                iCartLoadListener.OnCartloadFailed("Добавлено");
                            })
                            .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));

                    EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                }

            });
        });*/

            //new РАБОЧИЙ
/*
        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("item_name", pPpizzaModel.getItem_name());
        cartMap.put("item_cost", pPpizzaModel.getItem_cost());
        cartMap.put("item_image", pPpizzaModel.getItem_image());

        FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
            FirebaseFirestore.getInstance().collection("Users_Cart")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .collection("Корзина")
                    .add(cartMap)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    iCartLoadListener.OnCartloadFailed("Добавлено");
                }
            });
            EventBus.getDefault().postSticky(new MyUpdateCartEvent());
        });
*/



            //old
       /* DatabaseReference userCart = FirebaseDatabase
                .getInstance()
                .getReference("Cart")
                .child("UNIQUE_USER_ID"); // В другом проекте вы добавите сюда идентификатор пользователя


        userCart.child(pPpizzaModel.getKey())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        if (snapshot.exists()) //если у пользователя уже есть товар в корзине
                        {
                            // Просто обновите количество и общую цену

                            CartModel cartModel = snapshot.getValue(CartModel.class);
                            *//*cartModel.setQuantity(cartModel.getQuantity()+1);*//*
                            Map<String,Object> updateData = new HashMap<>();
                            *//*updateData.put("quantity",cartModel.getQuantity());*//*
                           *//* updateData.put("totalPrice",cartModel.getQuantity()*Integer.parseInt(cartModel.getPrice()));*//*

                            userCart.child(pPpizzaModel.getKey())
                                    .updateChildren(updateData)
                                    .addOnSuccessListener(aVoid -> {
                                        iCartLoadListener.OnCartloadFailed("Добавлено");
                                    })
                            .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));

                        }
                        else // если в корзине нет придмета, добавить новый
                        {

                                CartModel cartModel = new CartModel();
                                cartModel.setItem_name(pPpizzaModel.getItem_name());
                                cartModel.setItem_image(pPpizzaModel.getItem_image());
                                cartModel.setKey(pPpizzaModel.getKey());
                                *//*cartModel.setPrice(pPpizzaModel.getItem_name());*//*
*//*                                cartModel.setQuantity(1);
                                cartModel.setTotalPrice(Integer.parseInt(pPpizzaModel.getItem_name()));*//*

                                userCart.child(pPpizzaModel.getKey())
                                        .setValue(cartModel)
                                        .addOnSuccessListener(aVoid -> {
                                            iCartLoadListener.OnCartloadFailed("Добавлено");
                                        })
                                        .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));
                        }
                        EventBus.getDefault().postSticky(new MyUpdateCartEvent());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                        iCartLoadListener.OnCartloadFailed(error.getMessage());

                    }
                });*/

    }

    @Override
    public int getItemCount() {
        return ppizzaModelList.size();
    }

    public class MyPizzaViewHolder  extends  RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imagetovar)
        ImageView imagetovar;
        @BindView(R.id.titleMoney)
        TextView textMoney;
        @BindView(R.id.titleName)
        TextView textName;
        @BindView(R.id.descrName)
        TextView descrName;
        @BindView(R.id.titleWeight)
        TextView titleWeight;
        @BindView(R.id.btnAddToCart)
        ImageView btnAddToCart;

        IRecyclerViewClickListener listener;

        public void setListener(IRecyclerViewClickListener listener) {
            this.listener = listener;
        }

        private Unbinder unbinder;
        public MyPizzaViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.OnRecyclerClick(v,getAdapterPosition());
        }
    }
}
