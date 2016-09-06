package com.example.administrator.fastandroid.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zt on 16/8/21.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(v, getAdapterPosition());
            }
        });

    }


    public abstract void onBindViewHolder(int position);

    public abstract void onItemClick(View view, int position);
}
