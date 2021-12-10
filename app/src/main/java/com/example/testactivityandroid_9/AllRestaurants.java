package com.example.testactivityandroid_9;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivityandroid_9.adapter.MyPizzaAdapter;
import com.example.testactivityandroid_9.eventbus.MyUpdateCartEvent;
import com.example.testactivityandroid_9.listener.ICartLoadListener;
import com.example.testactivityandroid_9.listener.IPPpizzaLoadListener;
import com.example.testactivityandroid_9.model.CartModel;
import com.example.testactivityandroid_9.model.PPpizzaModel;
import com.example.testactivityandroid_9.utils.SpaceItemDeconstration;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.nex3z.notificationbadge.NotificationBadge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllRestaurants extends AppCompatActivity implements IPPpizzaLoadListener, ICartLoadListener  {

    @BindView(R.id.resview)
    RecyclerView resview;
    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    ImageButton imageButton;
    @BindView(R.id.badge)
    NotificationBadge badge;
    @BindView(R.id.cartButton)
    ImageView cartButton;


    IPPpizzaLoadListener ppizzaLoadListener;
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
        countCartItem();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_restaurants);

        init();
        loadPizzaFromFirebase();
        countCartItem();

        imageButton = (ImageButton)findViewById(R.id.back);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    private void loadPizzaFromFirebase() {
/*        List<PPpizzaModel> ppizzaModels = new ArrayList<>();
        FirebaseFirestore myDB = FirebaseFirestore.getInstance();
*//*        myDB.collection("Items")
            .document("Tfu0EQZz19g6mQUTHXU3")*//*
           *//* .orderBy("item_cost", Query.Direction.ASCENDING)*//*
        myDB.collection("Items")
                .document("hYBO9afiXVTS9vzx73w9")
                .collection("Pizza")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {
                            Log.e("Firestore error", error.getMessage());
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {

                            if (dc.getType() == DocumentChange.Type.ADDED) {

                                ppizzaModels.add(dc.getDocument().toObject(PPpizzaModel.class));
                            }
                        }
                        ppizzaLoadListener.OnPPpizzaloadSuccess(ppizzaModels);
                    }
                });
        */

       /* List<PPpizzaModel> ppizzaModels = new ArrayList<>();
        FirebaseFirestore.getInstance()
                .getReference("Item")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){
                            for (DataSnapshot pizzaSnapshot:snapshot.getChildren())
                            {
                                PPpizzaModel ppizzaModel = pizzaSnapshot.getValue(PPpizzaModel.class);
                                ppizzaModel.setKey(pizzaSnapshot.getKey());
                                ppizzaModels.add(ppizzaModel);
                            }
                            ppizzaLoadListener.OnPPpizzaloadSuccess(ppizzaModels);

                        }
                        else
                            ppizzaLoadListener.OnPPpizzaloadFailed("Что то пишет");
                    }



                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        ppizzaLoadListener.OnPPpizzaloadFailed(databaseError.getMessage());
                    }
                });*/
    }


    private void init() {
        ButterKnife.bind(this);

        ppizzaLoadListener = this;
        cartLoadListener = this;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        resview.setLayoutManager(gridLayoutManager);
        resview.addItemDecoration(new SpaceItemDeconstration());


        cartButton.setOnClickListener(v -> startActivity(new Intent(this,CartActivity.class)));



    }

    @Override
    public void OnPPpizzaloadSuccess(List<PPpizzaModel> pppizzaModeList) {
        MyPizzaAdapter adapter = new MyPizzaAdapter(this,pppizzaModeList,cartLoadListener);
        resview.setAdapter(adapter);
    }

    @Override
    public void OnPPpizzaloadFailed(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void OnCartloadSuccess(List<CartModel> cartModelList) {


        int cartSum = 0;
        for (CartModel cartModel: cartModelList)
            cartSum += cartModel.getQuantity();
        badge.setNumber(cartSum);


    }

    @Override
    public void OnCartloadFailed(String message) {

        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        countCartItem();
    }

    private void countCartItem() {
        List<CartModel> cartModels = new ArrayList<>();
        FirebaseDatabase
                .getInstance().getReference("Cart")
                .child("UNIQUE_USER_ID")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot cartSnapshot:snapshot.getChildren())
                        {
                            CartModel cartModel = cartSnapshot.getValue(CartModel.class);
                            cartModel.setKey(cartSnapshot.getKey());
                            cartModels.add(cartModel);

                        }

                        cartLoadListener.OnCartloadSuccess(cartModels);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        cartLoadListener.OnCartloadFailed(error.getMessage());
                    }
                });
    }
}











