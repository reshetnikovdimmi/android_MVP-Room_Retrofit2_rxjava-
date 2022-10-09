package com.fragment.android_suz.ui.Authorization;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fragment.android_suz.R;
import com.fragment.android_suz.common.UpdateRv;
import com.fragment.android_suz.model.stoks.Storage;

import java.util.ArrayList;

public class DynamicRvAdapter extends RecyclerView.Adapter<DynamicRvAdapter.DynamicRvHolder> {

    Activity activity;
    UpdateRv.callback2 uCl2;
    ArrayList<DynamicRvModel> dynamicRvModel;

    public DynamicRvAdapter(ArrayList<DynamicRvModel> dynamicRvModel, Activity activity, UpdateRv.callback2 uCl2) {
        this.activity = activity;
        this.dynamicRvModel = dynamicRvModel;
        this.uCl2 = uCl2;
    }

    public DynamicRvAdapter(ArrayList<DynamicRvModel> dynamicRvModel, Activity activity) {
        this.activity = activity;
        this.dynamicRvModel = dynamicRvModel;
    }

    public class DynamicRvHolder extends RecyclerView.ViewHolder {

        public TextView Remanis, TMC;

        public DynamicRvHolder(@NonNull View itemView) {
            super(itemView);
            Remanis = itemView.findViewById(R.id.Remanis);
            TMC = itemView.findViewById(R.id.TMC);
        }
    }

    @NonNull
    @Override
    public DynamicRvAdapter.DynamicRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dinamic_rv_layout, parent, false);
        DynamicRvHolder dynamicRvHolder = new DynamicRvHolder(view);
        return dynamicRvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRvAdapter.DynamicRvHolder holder, int position) {
        DynamicRvModel currentItem = dynamicRvModel.get(position);
        holder.TMC.setText(currentItem.getTmc());
        holder.Remanis.setText(currentItem.getRemanis());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (uCl2 != null) {
                    uCl2.callback2(currentItem.getTmc());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dynamicRvModel.size();
    }


}
