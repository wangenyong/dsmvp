package com.wangenyong.dsmvp.itembinder;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangenyong.dsmvp.R;
import com.wangenyong.dsmvp.databinding.ItemGankBinding;
import com.wangenyong.dsmvp.entity.Gank;

import me.drakeet.multitype.ItemViewBinder;

/**
 * @author wangenyong
 * @date 2017/5/26
 */
public class GankViewBinder extends ItemViewBinder<Gank, GankViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gank, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull Gank gank) {
        holder.bind(gank);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemGankBinding mBinding;

        ViewHolder(View itemView) {
            super(itemView);
            mBinding = DataBindingUtil.bind(itemView);
        }

        public void bind(@NonNull Gank gank) {
            mBinding.setItem(gank);
        }

        public ItemGankBinding getBinding() {
            return mBinding;
        }
    }
}
