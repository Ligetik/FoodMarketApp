package com.example.testactivityandroid_9;

import android.mtp.MtpConstants;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivityandroid_9.adapter.MyCartAdapter;
import com.example.testactivityandroid_9.eventbus.MyUpdateCartEvent;
import com.example.testactivityandroid_9.listener.ICartLoadListener;
import com.example.testactivityandroid_9.model.CartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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

    ICartLoadListener cartLoadListener;

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
    }

    private void loadCartFromFribase() {


            //new 2_1
        List<CartModel> cartModels = new ArrayList<>();
        /*MyCartAdapter cartAdapter = new MyCartAdapter (this,cartModels);*/
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

                                    /*String documentId = documentSnapshot.getId();*/

                                    CartModel cartModel = documentSnapshot.toObject(CartModel.class);

                                    /*cartModel.setDocumentId(documentId);*/
                                    cartModel.setKey(documentSnapshot.getId());

                                    cartModels.add(cartModel);
                                    /*cartAdapter.notifyDataSetChanged();*/
                                }
                                cartLoadListener.OnCartloadSuccess(cartModels);
                            }
                        }
                    });
        });


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
    }

    @Override
    public void OnCartloadSuccess(List<CartModel> cartModelList) {
        int sum = 0;
        for (CartModel cartModel : cartModelList)
        {
            sum+=cartModel.getTotalPrice();
        }

        txtTotal.setText(sum + " RUB");
        MyCartAdapter adapter = new MyCartAdapter (this,cartModelList);
        recyclerCart.setAdapter(adapter);
    }

    @Override
    public void OnCartloadFailed(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();
    }
}