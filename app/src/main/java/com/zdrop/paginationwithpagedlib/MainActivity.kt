package com.zdrop.paginationwithpagedlib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), DataSource.DataResponseListener {


    private lateinit var adapter : ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()
        adapter.currentPage = 1
        loadData()
    }

    private fun setRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.rv_data)
        recyclerView.hasFixedSize()

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        adapter = ItemAdapter()


        recyclerView.addOnScrollListener(object : PaginationScrollListener(layoutManager){
            override fun loadMoreItems() {
                adapter.currentPage++
                adapter.isLoading = true
                loadData()
            }

            override fun isLastPage(): Boolean {
                return adapter.isLastPage
            }

            override fun isLoading(): Boolean {
                return adapter.isLoading
            }
        })
        recyclerView.adapter = adapter
    }

    private fun loadData(){
        DataSource.getItem(adapter.currentPage, this)
    }

    override fun onResponse(data: MutableList<ItemModel>?) {
        if (data == null) adapter.isLastPage = true
        adapter.isLoading = false
        adapter.setDataSet(data)
    }


}