package com.example.testactivityandroid_9.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testactivityandroid_9.DetailedActivity;
import com.example.testactivityandroid_9.R;
import com.example.testactivityandroid_9.eventbus.MyUpdateCartEvent;
import com.example.testactivityandroid_9.listener.ICartLoadListener;
import com.example.testactivityandroid_9.listener.IRecyclerViewClickListener;
import com.example.testactivityandroid_9.model.AvocadoModel;
import com.example.testactivityandroid_9.model.CartModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class AvocadoAdapter extends RecyclerView.Adapter<AvocadoAdapter.MyPizzaViewHolder> {

    private Context context;
    private List<AvocadoModel> avocadoModelList;
    private ICartLoadListener iCartLoadListener;
    private List<CartModel> cartModelList;
    private  FirebaseFirestore db;
    private static final String TAG = "MyActivity";

    public AvocadoAdapter(Context context, List<AvocadoModel> avocadoModelList, ICartLoadListener iCartLoadListener) {
        this.context = context;
        this.avocadoModelList = avocadoModelList;
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
                .load(avocadoModelList.get(position).getItem_image())
                .into(holder.imagetovar);

        holder.textMoney.setText(new StringBuilder().append(avocadoModelList.get(position).getItem_cost()));
        holder.textName.setText(new StringBuilder().append(avocadoModelList.get(position).getItem_name()));
        holder.descrName.setText(new StringBuilder().append(avocadoModelList.get(position).getItem_details()));
        holder.titleWeight.setText(new StringBuilder().append(avocadoModelList.get(position).getItem_weight()));

        holder.btnAddToCart.setOnClickListener(v -> {
            addToCart(avocadoModelList.get(position));
        });

        holder.setListener((view, adapterPosition) -> {

            Intent intent = new Intent(context, DetailedActivity.class);
            intent.putExtra("details", avocadoModelList.get(position));
            context.startActivity(intent);

        });

    }

    private void addToCart(AvocadoModel avocadoModel) {

        try {
            FirebaseFirestore.getInstance()
                    .collection("Users_Cart")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .collection("Корзина")
/*                    .document("Restaurants")
                    .collection("Djo")*/
                    .document(avocadoModel.getKey())
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


                                FirebaseFirestore.getInstance()
                                        .collection("Users_Cart")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Корзина")
/*                                        .document("Restaurants")
                                        .collection("Djo")*/
                                        .document(avocadoModel.getKey())
                                        .update(updateData)
                                        .addOnSuccessListener(aVoid -> {

                                            iCartLoadListener.OnCartloadFailed("Добавлено");
                                        })
                                        .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));

                            }
                            else // если в корзине нет предмета, то добавить новый
                            {
                                CartModel cartModel = new CartModel();
                                cartModel.setItem_name(avocadoModel.getItem_name());
                                cartModel.setItem_image(avocadoModel.getItem_image());
                                cartModel.setItem_details(avocadoModel.getItem_details());
                                cartModel.setKey(avocadoModel.getKey());
                                cartModel.setItem_cost(avocadoModel.getItem_cost());
                                cartModel.setQuantity(1);
                                cartModel.setTotalPrice(avocadoModel.getItem_cost());
                                cartModel.setId(avocadoModel.getId());

                                FirebaseFirestore.getInstance()
                                        .collection("Users_Cart")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Корзина")
/*                                        .document("Restaurants")
                                        .collection("Djo")*/
                                        .document(avocadoModel.getKey())
                                        .set(cartModel)
                                        .addOnSuccessListener(aVoid -> {
                                            iCartLoadListener.OnCartloadFailed("Добавлено");
                                        })
                                        .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));
                            }
                            EventBus.getDefault().postSticky(new MyUpdateCartEvent());

                        }

                    });
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }

/*        try {
            FirebaseFirestore.getInstance()
                    .collection("Users_Cart")
                    .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .collection("Корзина")
                    .document(djoModel.getKey())
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


                                FirebaseFirestore.getInstance()
                                        .collection("Users_Cart")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Корзина")
                                        .document(djoModel.getKey())
                                        .update(updateData)
                                        .addOnSuccessListener(aVoid -> {

                                            iCartLoadListener.OnCartloadFailed("Добавлено");
                                        })
                                        .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));

                            }
                            else // если в корзине нет предмета, то добавить новый
                            {
                                CartModel cartModel = new CartModel();
                                cartModel.setItem_name(djoModel.getItem_name());
                                cartModel.setItem_image(djoModel.getItem_image());
                                cartModel.setItem_details(djoModel.getItem_details());
                                cartModel.setKey(djoModel.getKey());
                                cartModel.setItem_cost(djoModel.getItem_cost());
                                cartModel.setQuantity(1);
                                cartModel.setTotalPrice(djoModel.getItem_cost());

                                FirebaseFirestore.getInstance()
                                        .collection("Users_Cart")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Корзина")
                                        .document(djoModel.getKey())
                                        .set(cartModel)
                                        .addOnSuccessListener(aVoid -> {
                                            iCartLoadListener.OnCartloadFailed("Добавлено");
                                        })
                                        .addOnFailureListener(e -> iCartLoadListener.OnCartloadFailed(e.getMessage()));
                            }
                            EventBus.getDefault().postSticky(new MyUpdateCartEvent());

                        }

            });
        } catch (Exception e) {
            FirebaseCrashlytics.getInstance().recordException(e);
        }*/

    }

    @Override
    public int getItemCount() {
        return avocadoModelList.size();
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
