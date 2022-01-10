package com.example.testactivityandroid_9.adapter;

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
import com.example.testactivityandroid_9.model.CartModel;
import com.example.testactivityandroid_9.model.PromotionModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.PromotionViewHolder> {

    private Context context;
    private List<PromotionModel> promotionModelList;

    public PromotionAdapter(Context context, List<PromotionModel> promotionModelList) {
        this.context = context;
        this.promotionModelList = promotionModelList;
    }

    public class PromotionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.promotion_img)
        ImageView promotion_img;
        @BindView(R.id.promotion_name)
        TextView promotion_name;

        Unbinder unbinder;
        public PromotionViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
        }
    }

    @NonNull
    @Override
    public PromotionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PromotionAdapter.PromotionViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.promotion_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PromotionViewHolder holder, int position) {
        Glide.with(context)
                .load(promotionModelList.get(position).getItem_image())
                .into(holder.promotion_img);

        holder.promotion_name.setText(new StringBuilder().append(promotionModelList.get(position).getItem_name()));
    }

    @Override
    public int getItemCount() {
        return promotionModelList.size();
    }



}
