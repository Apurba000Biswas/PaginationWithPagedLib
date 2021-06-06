package com.zdrop.paginationwithpagedlib;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public abstract Object getDataAtPosition(int position);
    public abstract int getLayoutIdForType(int viewType);
    public abstract Object getClickListener(int position, int viewType);

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, getLayoutIdForType(viewType)
                , parent, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((BaseViewHolder)holder).bindView(getDataAtPosition(position), getClickListener(position, getItemViewType(position)));
    }

    private static class BaseViewHolder extends RecyclerView.ViewHolder{

        private final ViewDataBinding binding;
        public BaseViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindView(Object object, Object clickListener){
            binding.setVariable(BR.item, object);
            binding.setVariable(BR.clickListener, clickListener);
            binding.executePendingBindings();
        }
    }
}