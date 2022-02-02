package com.example.testactivityandroid_9.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testactivityandroid_9.R;
import com.example.testactivityandroid_9.model.HistoryOrderModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HistoryOrderAdapter extends RecyclerView.Adapter<HistoryOrderAdapter.HistoryOrderViewHolder> {

    private Context context;
    private List<HistoryOrderModel> historyOrderModelList;

    public HistoryOrderAdapter(Context context, List<HistoryOrderModel> historyOrderModelList) {
        this.context = context;
        this.historyOrderModelList = historyOrderModelList;
    }

    public class HistoryOrderViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.orderItemAddress)
        TextView orderItemAddress;
        @BindView(R.id.orderItemTime)
        TextView orderItemTime;
        @BindView(R.id.orderItemNo)
        TextView orderItemNo;
        @BindView(R.id.orderItemCost)
        TextView orderItemCost;
        @BindView(R.id.orderItemPayMethod)
        TextView orderItemPayMethod;
        @BindView(R.id.orderItemName)
        TextView orderItemName;
        @BindView(R.id.orderItemNumber)
        TextView orderItemNumber;
        @BindView(R.id.orderItemCommentary)
        TextView orderItemCommentary;

        Unbinder unbinder;
        public HistoryOrderViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
        }
    }

    @NonNull
    @Override
    public HistoryOrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryOrderAdapter.HistoryOrderViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.history_order_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryOrderViewHolder holder, int position) {
        holder.orderItemAddress.setText(new StringBuilder().append(historyOrderModelList.get(position).getАдрес_доставки()));
        holder.orderItemTime.setText(new StringBuilder().append(historyOrderModelList.get(position).getДата_и_время()));
        holder.orderItemNo.setText(new StringBuilder().append(historyOrderModelList.get(position).getНомер_заказа()));
        holder.orderItemCost.setText(new StringBuilder().append(historyOrderModelList.get(position).getСумма_заказа()) /*+ " ₽"*/);
        holder.orderItemPayMethod.setText(new StringBuilder().append(historyOrderModelList.get(position).getСпособ_оплаты_курьеру()));
        holder.orderItemName.setText(new StringBuilder().append(historyOrderModelList.get(position).getИмя()));
        holder.orderItemNumber.setText(new StringBuilder().append(historyOrderModelList.get(position).getНомер_телефона()));
        holder.orderItemCommentary.setText(new StringBuilder().append(historyOrderModelList.get(position).getКомментарий()));
    }

    @Override
    public int getItemCount() {
        return historyOrderModelList.size();
    }

}
