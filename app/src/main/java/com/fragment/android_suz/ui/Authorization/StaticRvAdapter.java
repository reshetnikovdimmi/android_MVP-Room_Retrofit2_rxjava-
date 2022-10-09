package com.fragment.android_suz.ui.Authorization;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fragment.android_suz.R;
import com.fragment.android_suz.common.UpdateRv;
import com.fragment.android_suz.model.stoks.Storage;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StatisRvVievHolder> {

    private ArrayList<StaticRvModel> items;
    int row_index = -1;
    private Storage mStorage;
    private UpdateRv.callback updateRv;
    private Activity activity;
    private String shopView;
    boolean chek = true;
    boolean select = true;

    public StaticRvAdapter(ArrayList<StaticRvModel> items, String shopView, Activity activity, UpdateRv.callback updateRv) {
        this.items = items;
        this.activity = activity;
        this.updateRv = updateRv;
        this.shopView = shopView;
    }

    @NonNull
    @Override
    public StatisRvVievHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent,false);
        StatisRvVievHolder statisRvVievHolder = new StatisRvVievHolder(view);
        return statisRvVievHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StatisRvVievHolder holder, @SuppressLint("RecyclerView") int position) {
        mStorage = ((Storage.StorageOwner) activity).obtainStorage();;
        StaticRvModel currentItem;
        currentItem = items.get(position);
        holder.textView.setText(currentItem.getText());
        if (chek){
            updateRv.callback(mStorage.getShopSim(currentItem.getText(),shopView),position);
            chek=false;
        }
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;

                updateRv.callback(mStorage.getShopSim(currentItem.getText(), shopView),position);
                notifyDataSetChanged();
            }
        });
        if (select){
            if (position==0)
                holder.linearLayout.setBackgroundResource(R.drawable.edittextstyle_2);
            select=false;
        }else {
            if (row_index == position){
                holder.linearLayout.setBackgroundResource(R.drawable.edittextstyle_2);
            }
            else {
                holder.linearLayout.setBackgroundResource(R.drawable.edittextstyle);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static  class StatisRvVievHolder extends RecyclerView.ViewHolder{
        TextView textView;
        LinearLayout linearLayout;

        public StatisRvVievHolder(@NonNull View itemView) {
            super((android.view.View) itemView);
            textView = ((android.view.View) itemView).findViewById(R.id.Text);
            linearLayout = ((android.view.View) itemView).findViewById(R.id.LinerLayuot);
        }
    }
}
