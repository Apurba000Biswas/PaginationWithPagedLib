package com.zdrop.paginationwithpagedlib;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public abstract Object getDataAtPosition(int position);
    public abstract int getLayoutIdForType(int viewType);
    public abstract Object getClickListener(int position, int viewType);

    // Pagination
    private int nextPage;
    private boolean isLoading;
    private boolean isLastPage;


    public void setRecyclerViewForPagination(RecyclerView recyclerView
            , LinearLayoutManager layoutManager
            , PaginationListener paginationListener
            , int startPage){
        this.nextPage = startPage;
        isLoading = false;
        isLastPage = false;

        recyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager){
            @Override
            protected void loadMoreItems() {
                nextPage++;
                if (paginationListener != null) paginationListener.onLoadData(nextPage);
                isLoading = true;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    protected void notifyAdapter(boolean isLastPage){
        this.isLoading = false;
        this.isLastPage = isLastPage;
        notifyDataSetChanged();
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

    public interface PaginationListener{
        void onLoadData(int nextPage);
    }

    private abstract static class PaginationScrollListener extends RecyclerView.OnScrollListener{
        private final LinearLayoutManager layoutManager;

        public PaginationScrollListener(LinearLayoutManager layoutManager){
            this.layoutManager = layoutManager;
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

            if (!isLoading() && !isLastPage()) {
                if ((visibleItemCount + firstVisibleItemPosition) >=
                        totalItemCount && firstVisibleItemPosition >= 0) {
                    loadMoreItems();
                }
            }
        }


        protected abstract void loadMoreItems();
        public abstract boolean isLastPage();
        public abstract boolean isLoading();

    }

}
