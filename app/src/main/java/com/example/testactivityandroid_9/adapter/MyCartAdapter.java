package com.example.testactivityandroid_9.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.testactivityandroid_9.R;
import com.example.testactivityandroid_9.eventbus.MyUpdateCartEvent;
import com.example.testactivityandroid_9.model.CartModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MyCartAdapter extends RecyclerView.Adapter<MyCartAdapter.MyCartViewHolder> {

    private Context context;
    private List<CartModel> cartModelList;

    public MyCartAdapter(Context context, List<CartModel> cartModelList) {
        this.context = context;
        this.cartModelList = cartModelList;
    }

    @NonNull
    @Override
    public MyCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCartViewHolder(LayoutInflater.from(context)
        .inflate(R.layout.tovar_item_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCartViewHolder holder, int position) {
        Glide.with(context)
                .load(cartModelList.get(position).getItem_image())
                .into(holder.imageView);

        holder.txtPrice.setText(new StringBuilder().append(cartModelList.get(position).getItem_cost()) + " ₽");
        holder.txtName.setText(new StringBuilder().append(cartModelList.get(position).getItem_name()));
        holder.txtQuantity.setText(new StringBuilder().append(cartModelList.get(position).getQuantity()));
        holder.descrNameCart.setText(new StringBuilder().append(cartModelList.get(position).getItem_details()));

        //Event
        holder.btnMinus.setOnClickListener(v -> {
            minusCartItem(holder,cartModelList.get(position));
        });
        holder.btnPlus.setOnClickListener(v -> {
           plusCartItem(holder,cartModelList.get(position));
        });



        //new1  РАБОЧИЙ

        /*holder.btnDelete.setOnClickListener(new View.OnClickListener() {*/

            /*@Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setTitle("Удалить предмет")
                        .setMessage("Вы действительно хотите удалить?")
                        .setNegativeButton("Нет", (dialog1, which) -> dialog1.dismiss())
                        .setPositiveButton("Да", (dialog12, which) -> {

                            *//*notifyItemRemoved(position);*//*

                            FirebaseAuth.getInstance().signInAnonymously().addOnCompleteListener((Task<AuthResult> task) -> {
                                FirebaseFirestore.getInstance()
                                        .collection("Users_Cart")
                                        .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .collection("Корзина")
                                        *//*.document(cartModelList.get(position).getDocumentId())*//*
                                        .document(cartModelList.get(position).getKey())
                                        .delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    cartModelList.remove(cartModelList.get(position));
                                                    notifyDataSetChanged();
                                                    *//*Toast.makeText(context, "", Toast.LENGTH_SHORT).show();*//*
                                                }
                                                else {
                                                    Toast.makeText(context, "Ошибка " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }

                                            }
                                        });
                            });
                            dialog12.dismiss();
                        }).create();
                dialog.show();



            }

        });
*/

        //NEW2 РАБОЧИЙ
        holder.btnDelete.setOnClickListener(v -> {
            AlertDialog dialog = new AlertDialog.Builder(context)
                    .setTitle("Удалить предмет")
                    .setMessage("Вы действительно хотите удалить?")
                    .setNegativeButton("Нет", (dialog1, which) -> dialog1.dismiss())
                    .setPositiveButton("Да", (dialog12, which) -> {

                        notifyItemRemoved(position);
                        notifyDataSetChanged();
                        deleteFromFirebase(cartModelList.get(position));
                        dialog12.dismiss();
                    }).create();
            dialog.show();
        });



    }

    private void deleteFromFirebase(CartModel cartModel) {
/*        DocumentReference db = FirebaseFirestore.getInstance()
                .collection("Users_Cart")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Корзина")
                .document("Restaurants");

        switch (cartModel.getId()) {
            case 1:
                db
                    .collection("PodkrePizza")
                    .document(cartModel.getKey())
                    .delete()
                    .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new MyUpdateCartEvent()));
                break;
            case 3:
                db
                    .collection("Djo")
                    .document(cartModel.getKey())
                    .delete()
                    .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new MyUpdateCartEvent()));
                break;
        }*/



        FirebaseFirestore.getInstance()
                .collection("Users_Cart")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Корзина")
                .document(cartModel.getKey())
                .delete()
                .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new MyUpdateCartEvent()));

    }

    private void plusCartItem(MyCartViewHolder holder, CartModel cartModel) {
        cartModel.setQuantity(cartModel.getQuantity()+1);
        cartModel.setTotalPrice(cartModel.getQuantity()*/*Float.parseFloat*/(cartModel.getItem_cost()));

        holder.txtQuantity.setText(new StringBuilder().append(cartModel.getQuantity()));
        updateFirebase(cartModel);
    }

    private void minusCartItem(MyCartViewHolder holder, CartModel cartModel) {
        if(cartModel.getQuantity() > 1)
        {
            cartModel.setQuantity(cartModel.getQuantity()-1);
            cartModel.setTotalPrice(cartModel.getQuantity()*/*Float.parseFloat*/(cartModel.getItem_cost()));

            //Обновление
            holder.txtQuantity.setText(new StringBuilder().append(cartModel.getQuantity()));
            updateFirebase(cartModel);
        }
    }

    private void updateFirebase(CartModel cartModel) {

/*        DocumentReference db = FirebaseFirestore.getInstance()
                .collection("Users_Cart")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Корзина")
                .document("Restaurants");

        switch (cartModel.getId()) {
            case 1:
                db
                        .collection("PodkrePizza")
                        .document(cartModel.getKey())
                        .set(cartModel)
                        .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new MyUpdateCartEvent()));
                break;
            case 3:
                db
                        .collection("Djo")
                        .document(cartModel.getKey())
                        .set(cartModel)
                        .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new MyUpdateCartEvent()));
                break;
        }*/


        FirebaseFirestore.getInstance()
                .collection("Users_Cart")
                .document(FirebaseAuth.getInstance().getCurrentUser().getUid())
                .collection("Корзина")
                .document(cartModel.getKey())
                .set(cartModel)
                .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new MyUpdateCartEvent()));


                //old
        /*FirebaseDatabase.getInstance()
                .getReference("Cart")
                .child("UNIQUE_USER_ID")
                .child(cartModel.getKey())
                .setValue(cartModel)
                .addOnSuccessListener(aVoid -> EventBus.getDefault().postSticky(new MyUpdateCartEvent()));*/

    }

    @Override
    public int getItemCount() {
        return cartModelList.size();
    }

    public class MyCartViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.btnMinus)
        ImageView btnMinus;
        @BindView(R.id.btnPlus)
        ImageView btnPlus;
        @BindView(R.id.btnDelete)
        ImageView btnDelete;
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.txtPrice)
        TextView txtPrice;
        @BindView(R.id.txtQuantity)
        TextView txtQuantity;
        @BindView(R.id.descrNameCart)
        TextView descrNameCart;

        Unbinder unbinder;
        public MyCartViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
        }
    }
}
