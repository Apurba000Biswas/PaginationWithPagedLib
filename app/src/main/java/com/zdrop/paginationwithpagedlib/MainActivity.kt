package com.zdrop.paginationwithpagedlib

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity()
    , DataSource.DataResponseListener
    , BaseAdapter.PaginationListener{


    private lateinit var adapter : ItemAdapter
    private lateinit var loadingFooter : ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadingFooter = findViewById(R.id.loading_indicator)

        setRecyclerView()
        onLoadData(1)
    }

    private fun setRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_data)
        recyclerView.hasFixedSize()

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = ItemAdapter()
        adapter.setRecyclerViewForPagination(recyclerView, layoutManager, this, 1)
        recyclerView.adapter = adapter
    }

    override fun onResponse(data: MutableList<ItemModel>?) {
        adapter.setDataSet(data)
        loadingFooter.visibility = View.GONE
    }

    override fun onLoadData(nextPage: Int) {
        DataSource.getItem(nextPage, this)
        loadingFooter.visibility = View.VISIBLE
    }


}