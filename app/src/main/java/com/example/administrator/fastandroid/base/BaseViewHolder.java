package com.example.administrator.fastandroid.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zt on 16/8/21.
 */
public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public BaseViewHolder( View itemView) {
        super(itemView);

    }

    public abstract void onBindViewHolder(int position);
}
