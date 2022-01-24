package com.example.testactivityandroid_9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivityandroid_9.adapter.MyPizzaAdapter;
import com.example.testactivityandroid_9.eventbus.MyUpdateCartEvent;
import com.example.testactivityandroid_9.listener.ICartLoadListener;
import com.example.testactivityandroid_9.listener.IPPpizzaLoadListener;
import com.example.testactivityandroid_9.listener.IPPpizzaLoadSearchListener;
import com.example.testactivityandroid_9.model.CartModel;
import com.example.testactivityandroid_9.model.PPpizzaModel;
import com.example.testactivityandroid_9.utils.SpaceItemDeconstration;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.nex3z.notificationbadge.NotificationBadge;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DjoActivity extends AppCompatActivity implements IPPpizzaLoadListener, ICartLoadListener  {
    private static final String TAG = "MyActivity";
    @BindView(R.id.resview)
    RecyclerView resview;
/*    @BindView(R.id.searchRecycler)
    RecyclerView searchRecycler;*/
    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    ImageButton imageButton;
    @BindView(R.id.badge)
    NotificationBadge badge;
    @BindView(R.id.cartButton)
    ImageView cartButton;
    @BindView(R.id.searchTextInput)
    EditText searchTextInput;

    IPPpizzaLoadListener ppizzaLoadListener;
    ICartLoadListener cartLoadListener;
    IPPpizzaLoadSearchListener ppizzaLoadSearchListener;

    private MyPizzaAdapter myPizzaAdapter;
    private List<PPpizzaModel> ppizzaModelsList;

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
        setContentView(R.layout.activity_djo);

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





                //new1 РАБОЧИЙ
       /* List<PPpizzaModel> ppizzaModels = new ArrayList<>();
        FirebaseFirestore.getInstance().collection("Items")
                .document("hYBO9afiXVTS9vzx73w9")
                .collection("Pizza")
                .orderBy("item_cost", Query.Direction.ASCENDING)
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
                });*/


        //old
/*        List<PPpizzaModel> ppizzaModels = new ArrayList<>();
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


    @NonNull
    private Task<QuerySnapshot> loadMenuPodkrePizza(List<PPpizzaModel> ppizzaModels, String pizza) {
        return FirebaseFirestore.getInstance()
                .collection("Items3")
                .document("MYHsl3omNB5RYatTPibp")
                .collection(pizza)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                PPpizzaModel ppizzaModel = documentSnapshot.toObject(PPpizzaModel.class);
                                ppizzaModel.setKey(documentSnapshot.getId());
                                ppizzaModels.add(ppizzaModel);
                            }
                            ppizzaLoadListener.OnPPpizzaloadSuccess(ppizzaModels);
                        } else {
                            ppizzaLoadListener.OnPPpizzaloadFailed("Ошибка");
                        }
                    }
                });
    }

    private void loadPizzaFromFirebase() {
        List<PPpizzaModel> ppizzaModels = new ArrayList<>();
        loadMenuPodkrePizza(ppizzaModels, "Seti");
    }
/*    private void loadBurgerFromFirebase() {
        List<PPpizzaModel> ppizzaModels = new ArrayList<>();
        loadMenuPodkrePizza(ppizzaModels, "Burger");
    }

    private void loadRollsFromFirebase() {
        List<PPpizzaModel> ppizzaModels = new ArrayList<>();
        loadMenuPodkrePizza(ppizzaModels, "Rolls");
    }

    private void loadDesertiFromFirebase() {
        List<PPpizzaModel> ppizzaModels = new ArrayList<>();
        loadMenuPodkrePizza(ppizzaModels, "Deserti");
    }

    private void loadNapitkiFromFirebase() {
        List<PPpizzaModel> ppizzaModels = new ArrayList<>();
        loadMenuPodkrePizza(ppizzaModels, "Napitki");
    }*/

    private void init() {
        ButterKnife.bind(this);

        ppizzaLoadListener = this;
        cartLoadListener = this;


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        resview.setLayoutManager(gridLayoutManager);
        resview.addItemDecoration(new SpaceItemDeconstration());

        //search
/*        GridLayoutManager gridLayoutManagerSearch = new GridLayoutManager(this,1);
        searchRecycler.setLayoutManager(gridLayoutManagerSearch);
        searchRecycler.addItemDecoration(new SpaceItemDeconstration());*/


        cartButton.setOnClickListener(v -> startActivity(new Intent(this,CartActivity.class)));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    loadPizzaFromFirebase();
                }
               /* if (tab.getPosition() == 1) {
                    loadBurgerFromFirebase();
                }
                if (tab.getPosition() == 2) {
                    loadRollsFromFirebase();
                }
                if (tab.getPosition() == 3) {
                    loadDesertiFromFirebase();
                }
                if (tab.getPosition() == 4) {
                    loadNapitkiFromFirebase();
                }*/
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

       /* myPizzaAdapter = new MyPizzaAdapter(this, ppizzaModelsList, cartLoadListener);
        searchRecycler.setAdapter(myPizzaAdapter);
        searchRecycler.setHasFixedSize(true);
        searchTextInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                List<PPpizzaModel> ppizzaModels = new ArrayList<>();
                *//*MyPizzaAdapter myPizzaAdapter = new MyPizzaAdapter(this, ppizzaModels, cartLoadListener);*//*
                if (s.toString().isEmpty()) {
                    ppizzaModels.clear();
                    myPizzaAdapter.notifyDataSetChanged();
                    *//*ppizzaLoadListener.OnPPpizzaloadSuccess(ppizzaModels);*//*
                }
                else {
                    searchItems(s.toString());
                }
            }
        });
    }

    private void searchItems(String type) {
        List<PPpizzaModel> ppizzaModels = new ArrayList<>();
        if (!type.isEmpty()) {
            FirebaseFirestore.getInstance()
                    .collection("Items")
                    .document("hYBO9afiXVTS9vzx73w9")
                    .collection("Pizza")
                    .whereEqualTo("item_name", type)
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful() && task.getResult() != null){
                                ppizzaModels.clear();
                                myPizzaAdapter.notifyDataSetChanged();

                                for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                    PPpizzaModel ppizzaModel = documentSnapshot.toObject(PPpizzaModel.class);
                                    ppizzaModels.add(ppizzaModel);
                                    myPizzaAdapter.notifyDataSetChanged();
                                }
                                *//*ppizzaLoadListener.OnPPpizzaloadSuccess(ppizzaModels);*//*
                            } *//*else {
                                ppizzaLoadListener.OnPPpizzaloadFailed("Ошибка");
                            }*//*

                        }
                    });
        }*/
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
    public void IPPpizzaLoadSearchSuccess(List<PPpizzaModel> pppizzaModeList) {
       /* MyPizzaAdapter adapter = new MyPizzaAdapter(this,pppizzaModeList,cartLoadListener);
        searchRecycler.setAdapter(adapter);*/
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
                                cartModel.setKey(documentSnapshot.getId());
                                cartModels.add(cartModel);
                            }
                            cartLoadListener.OnCartloadSuccess(cartModels);
                        }
                    }
                });
    }





}











