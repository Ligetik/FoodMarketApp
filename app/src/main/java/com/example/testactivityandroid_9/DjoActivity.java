package com.example.testactivityandroid_9;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivityandroid_9.adapter.AvocadoAdapter;
import com.example.testactivityandroid_9.adapter.DjoAdapter;
import com.example.testactivityandroid_9.adapter.MyPizzaAdapter;
import com.example.testactivityandroid_9.eventbus.MyUpdateCartEvent;
import com.example.testactivityandroid_9.listener.ICartLoadListener;
import com.example.testactivityandroid_9.listener.IDjoLoadListener;
import com.example.testactivityandroid_9.listener.IPPpizzaLoadSearchListener;
import com.example.testactivityandroid_9.model.AvocadoModel;
import com.example.testactivityandroid_9.model.CartModel;
import com.example.testactivityandroid_9.model.DjoModel;
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

public class DjoActivity extends AppCompatActivity implements IDjoLoadListener, ICartLoadListener  {
    private static final String TAG = "MyActivity";
    @BindView(R.id.resview)
    RecyclerView resview;
    @BindView(R.id.searchRecycler)
    RecyclerView searchRecycler;
    @BindView(R.id.mainLayout)
    RelativeLayout mainLayout;
    ImageButton imageButton;
    @BindView(R.id.badge)
    NotificationBadge badge;
    @BindView(R.id.cartButton)
    ImageView cartButton;
    @BindView(R.id.searchTextInput)
    EditText searchTextInput;

    IDjoLoadListener djoLoadListener;
    ICartLoadListener cartLoadListener;
    IPPpizzaLoadSearchListener ppizzaLoadSearchListener;

    private DjoAdapter djoAdapter;
    private List<DjoModel> djoModelList;

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
        loadFritureFromFirebase();
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
    private Task<QuerySnapshot> loadMenuDjo(List<DjoModel> djoModels, String pizza) {
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
                                DjoModel djoModel = documentSnapshot.toObject(DjoModel.class);
                                djoModel.setKey(documentSnapshot.getId());
                                djoModels.add(djoModel);
                            }
                            djoLoadListener.OnDjoloadSuccess(djoModels);
                        } else {
                            djoLoadListener.OnDjoloadFailed("Ошибка");
                        }
                    }
                });
    }

    private void loadFritureFromFirebase() {
        List<DjoModel> djoModels = new ArrayList<>();
        loadMenuDjo(djoModels, "Friture");
    }
    private void loadPizzaFromFirebase() {
        List<DjoModel> djoModels = new ArrayList<>();
        loadMenuDjo(djoModels, "Pizza");
    }

    private void loadSetiFromFirebase() {
        List<DjoModel> djoModels = new ArrayList<>();
        loadMenuDjo(djoModels, "Seti");
    }

    private void loadDopolnenieFromFirebase() {
        List<DjoModel> djoModels = new ArrayList<>();
        loadMenuDjo(djoModels, "Dopolnenie");
    }


    private void init() {
        ButterKnife.bind(this);

        djoLoadListener = this;
        cartLoadListener = this;


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        resview.setLayoutManager(gridLayoutManager);
        resview.addItemDecoration(new SpaceItemDeconstration());

        //search
        GridLayoutManager gridLayoutManagerSearch = new GridLayoutManager(this,1);
        searchRecycler.setLayoutManager(gridLayoutManagerSearch);
        searchRecycler.addItemDecoration(new SpaceItemDeconstration());


        cartButton.setOnClickListener(v -> startActivity(new Intent(this,CartActivity.class)));

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    loadFritureFromFirebase();
                }
                if (tab.getPosition() == 1) {
                    loadPizzaFromFirebase();
                }
                if (tab.getPosition() == 2) {
                    loadSetiFromFirebase();
                }
                if (tab.getPosition() == 3) {
                    loadDopolnenieFromFirebase();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        djoModelList = new ArrayList<>();
        djoAdapter = new DjoAdapter(this, djoModelList, cartLoadListener);
        searchRecycler.setAdapter(djoAdapter);
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
                if (s.toString().isEmpty()) {
                    djoModelList.clear();
                    djoAdapter.notifyDataSetChanged();
                }
                else {
                    searchItems(s.toString());
                }
            }
        });
    }

    private void searchItems(String type) {
        if (!type.isEmpty()) {
            FirebaseFirestore.getInstance()
                    .collection("Items3")
                    .document("MYHsl3omNB5RYatTPibp")
                    .collection("Pizza")
                    .orderBy("item_name")
                    .whereGreaterThanOrEqualTo("item_name", type)
                    .whereLessThanOrEqualTo("item_name", type + "\uf8ff")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful() && task.getResult() != null){
                        djoModelList.clear();
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                            DjoModel djoModel = documentSnapshot.toObject(DjoModel.class);
                            djoModelList.add(djoModel);
                            djoAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });

            FirebaseFirestore.getInstance()
                    .collection("Items3")
                    .document("MYHsl3omNB5RYatTPibp")
                    .collection("Friture")
                    .orderBy("item_name")
                    .whereGreaterThanOrEqualTo("item_name", type)
                    .whereLessThanOrEqualTo("item_name", type + "\uf8ff")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful() && task.getResult() != null){
                        djoModelList.clear();
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                            DjoModel djoModel = documentSnapshot.toObject(DjoModel.class);
                            djoModelList.add(djoModel);
                            djoAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });

            FirebaseFirestore.getInstance()
                    .collection("Items3")
                    .document("MYHsl3omNB5RYatTPibp")
                    .collection("Seti")
                    .orderBy("item_name")
                    .whereGreaterThanOrEqualTo("item_name", type)
                    .whereLessThanOrEqualTo("item_name", type + "\uf8ff")
                    .whereGreaterThanOrEqualTo("item_name", type)
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful() && task.getResult() != null){
                        djoModelList.clear();
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                            DjoModel djoModel = documentSnapshot.toObject(DjoModel.class);
                            djoModelList.add(djoModel);
                            djoAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });

            FirebaseFirestore.getInstance()
                    .collection("Items3")
                    .document("MYHsl3omNB5RYatTPibp")
                    .collection("Dopolnenie")
                    .whereGreaterThanOrEqualTo("item_name", type)
                    .whereLessThanOrEqualTo("item_name", type + "\uf8ff")
                    .whereGreaterThanOrEqualTo("item_name", type)
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful() && task.getResult() != null){
                        djoModelList.clear();
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                            DjoModel djoModel = documentSnapshot.toObject(DjoModel.class);
                            djoModelList.add(djoModel);
                            djoAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });


        }

    }

    @Override
    public void OnDjoloadSuccess(List<DjoModel> djoModeList) {
        DjoAdapter adapter = new DjoAdapter(this,djoModeList,cartLoadListener);
        resview.setAdapter(adapter);
    }

    @Override
    public void OnDjoloadFailed(String message) {
        Snackbar.make(mainLayout,message,Snackbar.LENGTH_LONG).show();
    }

/*    @Override
    public void IPPpizzaLoadSearchSuccess(List<PPpizzaModel> pppizzaModeList) {
       *//* MyPizzaAdapter adapter = new MyPizzaAdapter(this,pppizzaModeList,cartLoadListener);
        searchRecycler.setAdapter(adapter);*//*
    }*/

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











