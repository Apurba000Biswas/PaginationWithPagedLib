package com.zdrop.paginationwithpagedlib;

import java.util.ArrayList;
import java.util.List;

public class DataSource {


    public static List<ItemModel> getItem(int pageNum){
        List<ItemModel> dataSet = new ArrayList<>();
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));
        dataSet.add(new ItemModel("Tittle Page : " + pageNum, " Sub tittle page : " + pageNum));

        return dataSet;
    }

}
