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

import com.example.testactivityandroid_9.adapter.AvocadoAdapter;
import com.example.testactivityandroid_9.eventbus.MyUpdateCartEvent;
import com.example.testactivityandroid_9.listener.IAvocadoLoadListener;
import com.example.testactivityandroid_9.listener.ICartLoadListener;
import com.example.testactivityandroid_9.listener.IPPpizzaLoadSearchListener;
import com.example.testactivityandroid_9.model.AvocadoModel;
import com.example.testactivityandroid_9.model.CartModel;
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

public class AvocadoActivity extends AppCompatActivity implements IAvocadoLoadListener, ICartLoadListener  {
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

    IAvocadoLoadListener avocadoLoadListener;
    ICartLoadListener cartLoadListener;
    IPPpizzaLoadSearchListener ppizzaLoadSearchListener;

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
        setContentView(R.layout.activity_avocado);

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

    @NonNull
    private Task<QuerySnapshot> loadMenuAvocado(List<AvocadoModel> avocadoModels, String pizza) {
        return FirebaseFirestore.getInstance()
                .collection("Items2")
                .document("g2oIl2f0iOKMrnZm5akC")
                .collection(pizza)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                                AvocadoModel avocadoModel = documentSnapshot.toObject(AvocadoModel.class);
                                avocadoModel.setKey(documentSnapshot.getId());
                                avocadoModels.add(avocadoModel);
                            }
                            avocadoLoadListener.OnAvocadoloadSuccess(avocadoModels);
                        } else {
                            avocadoLoadListener.OnAvocadoloadFailed("Ошибка");
                        }
                    }
                });
    }

    private void loadPizzaFromFirebase() {
        List<AvocadoModel> avocadoModels = new ArrayList<>();
        loadMenuAvocado(avocadoModels, "Pizza");
    }
    private void loadVokFromFirebase() {
        List<AvocadoModel> avocadoModels = new ArrayList<>();
        loadMenuAvocado(avocadoModels, "BOK");
    }

    private void loadSalatiFromFirebase() {
        List<AvocadoModel> avocadoModels = new ArrayList<>();
        loadMenuAvocado(avocadoModels, "Salati");
    }

    private void loadSupiFromFirebase() {
        List<AvocadoModel> avocadoModels = new ArrayList<>();
        loadMenuAvocado(avocadoModels, "Supi");
    }

    private void loadZavertonFromFirebase() {
        List<AvocadoModel> avocadoModels = new ArrayList<>();
        loadMenuAvocado(avocadoModels, "Rolls");
    }

    private void loadSandwichFromFirebase() {
        List<AvocadoModel> avocadoModels = new ArrayList<>();
        loadMenuAvocado(avocadoModels, "Sandwich");
    }

    private void loadKartofelfriFromFirebase() {
        List<AvocadoModel> avocadoModels = new ArrayList<>();
        loadMenuAvocado(avocadoModels, "Kartofelfri");
    }

    private void loadKashaFromFirebase() {
        List<AvocadoModel> avocadoModels = new ArrayList<>();
        loadMenuAvocado(avocadoModels, "Kasha");
    }

    private void loadAvtorskiechaiFromFirebase() {
        List<AvocadoModel> avocadoModels = new ArrayList<>();
        loadMenuAvocado(avocadoModels, "Avtorskiechai");
    }

    private void loadMolochniekokteyliFromFirebase() {
        List<AvocadoModel> avocadoModels = new ArrayList<>();
        loadMenuAvocado(avocadoModels, "Molochniekokteyli");
    }

    private void loadNapitkiFromFirebase() {
        List<AvocadoModel> avocadoModels = new ArrayList<>();
        loadMenuAvocado(avocadoModels, "Napitki");
    }

    private void loadCofeFromFirebase() {
        List<AvocadoModel> avocadoModels = new ArrayList<>();
        loadMenuAvocado(avocadoModels, "Napitki");
    }

    private void loadDesertiFromFirebase() {
        List<AvocadoModel> avocadoModels = new ArrayList<>();
        loadMenuAvocado(avocadoModels, "Napitki");
    }

    private void loadDopolnitelnieingridientiFromFirebase() {
        List<AvocadoModel> avocadoModels = new ArrayList<>();
        loadMenuAvocado(avocadoModels, "Napitki");
    }

    private void init() {
        ButterKnife.bind(this);

        avocadoLoadListener = this;
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
                switch (tab.getPosition()) {
                    case 0:
                        loadPizzaFromFirebase();
                        break;
                    case 1:
                        loadVokFromFirebase();
                        break;
                    case 2:
                        loadSalatiFromFirebase();
                        break;
                    case 3:
                        loadSupiFromFirebase();
                        break;
                    case 4:
                        loadZavertonFromFirebase();
                        break;
                    case 5:
                        loadSandwichFromFirebase();
                        break;
                    case 6:
                        loadKartofelfriFromFirebase();
                        break;
                    case 7:
                        loadKashaFromFirebase();
                        break;
                    case 8:
                        loadAvtorskiechaiFromFirebase();
                        break;
                    case 9:
                        loadMolochniekokteyliFromFirebase();
                        break;
                    case 10:
                        loadNapitkiFromFirebase();
                        break;
                    case 11:
                        loadCofeFromFirebase();
                        break;
                    case 12:
                        loadDesertiFromFirebase();
                        break;
                    case 13:
                        loadDopolnitelnieingridientiFromFirebase();
                        break;
                }
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
    public void OnAvocadoloadSuccess(List<AvocadoModel> avocadoModeList) {
        AvocadoAdapter adapter = new AvocadoAdapter(this,avocadoModeList,cartLoadListener);
        resview.setAdapter(adapter);
    }

    @Override
    public void OnAvocadoloadFailed(String message) {
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











