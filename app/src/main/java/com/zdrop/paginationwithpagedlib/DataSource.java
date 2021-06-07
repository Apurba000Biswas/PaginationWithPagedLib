package com.zdrop.paginationwithpagedlib;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;

public class DataSource {


    public static void getItem(int pageNum, DataResponseListener listener){
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (pageNum == 5) {
                    listener.onResponse(null);
                    return;
                }


                List<ItemModel> dataSet = new ArrayList<>();
                for (int i=0; i<20; i++){
                    dataSet.add(new ItemModel("Tittle Page : " + pageNum + " " + i, " Sub tittle page : " + pageNum));
                }


                listener.onResponse(dataSet);
            }
        },secondsDelayed * 1000);



    }

    public interface DataResponseListener{
        void onResponse(List<ItemModel> data);
    }

}
