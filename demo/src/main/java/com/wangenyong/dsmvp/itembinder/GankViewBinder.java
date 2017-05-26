package com.wangenyong.dsmvp.itembinder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangenyong.dsmvp.R;
import com.wangenyong.dsmvp.entity.Gank;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Created by wangenyong on 2017/5/26.
 */
public class GankViewBinder extends ItemViewBinder<Gank, GankViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View root = inflater.inflate(R.layout.item_gank, parent, false);
        return new ViewHolder(root);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Gank gank) {
        holder.mTextView.setText(gank.getDesc());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.textView_item_gank) TextView mTextView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
