package com.zdrop.paginationwithpagedlib;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int LOADING_VIEW_TYPE = 1;
    private static final int ITEM_VIEW_TYPE = 0;


    public abstract Object getDataAtPosition(int position);
    public abstract int getLayoutIdForType(int viewType);
    public abstract Object getClickListener(int position, int viewType);

    private List<?> dataSet;
    private boolean isPaginationActivated;

    private boolean isLoading;
    private boolean isLastPage;
    public Integer currentPage;

    public boolean isLastPage() {
        return isLastPage;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
        currentPage = 0;
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    protected void setBaseDataSet(List<?> dataSet){
        this.dataSet = dataSet;
    }

    public void setPaginationActivated(boolean paginationActivated) {
        isPaginationActivated = paginationActivated;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPaginationActivated){
            if (position == dataSet.size()) return LOADING_VIEW_TYPE;
        }
        return ITEM_VIEW_TYPE;
    }

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
