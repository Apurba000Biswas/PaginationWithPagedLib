package com.zdrop.paginationwithpagedlib;

import java.util.List;

public class ItemAdapter extends BaseAdapter{

    private List<ItemModel> dataSet;

    public void setDataSet(List<ItemModel> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
    }

    @Override
    public Object getDataAtPosition(int position) {
        return dataSet.get(position);
    }

    @Override
    public int getLayoutIdForType(int viewType) {
        return R.layout.item;
    }

    @Override
    public Object getClickListener(int position, int viewType) {
        return null;
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
