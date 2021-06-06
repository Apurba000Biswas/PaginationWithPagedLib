package com.zdrop.paginationwithpagedlib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()
    }

    private fun setRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_data)
        recyclerView.hasFixedSize()

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

//        val pager : Pager<Integer, ItemModel>  = Pager(PagingConfig(20), PagingDataSource(null))
//
////        PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);

        val adapter = ItemAdapter()
        recyclerView.adapter = adapter
    }



}