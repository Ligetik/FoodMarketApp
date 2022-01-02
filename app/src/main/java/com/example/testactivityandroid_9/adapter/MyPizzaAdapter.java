package com.example.testactivityandroid_9.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testactivityandroid_9.R;
import com.example.testactivityandroid_9.eventbus.MyUpdateCartEvent;
import com.example.testactivityandroid_9.listener.ICartLoadListener;
import com.example.testactivityandroid_9.listener.IRecyclerViewClickListener;
import com.example.testactivityandroid_9.model.CartModel;
import com.example.testactivityandroid_9.model.PPpizzaModel;
import com.example.testactivityandroid_9.ui.login.signup.Login_SignupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
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

        holder.setListener((view, adapterPosition) -> {

            addToCart(ppizzaModelList.get(position));

        });

    }

    private void addToCart(PPpizzaModel pPpizzaModel) {

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
