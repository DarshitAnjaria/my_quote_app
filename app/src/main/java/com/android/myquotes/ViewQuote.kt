package com.android.myquotes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.android.myquotes.DatabaseManager.DatabaseManager
import com.android.myquotes.adapter.QuoteAdapter
import com.android.myquotes.model.Quotes

class ViewQuote : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView
    lateinit var tvNoData : TextView
    lateinit var btnAddNew : Button
    lateinit var quoteList : ArrayList<Quotes>
    lateinit var databaseManager : DatabaseManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_quote)

        databaseManager = DatabaseManager(applicationContext,null)
        quoteList = ArrayList()

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)

        tvNoData = findViewById(R.id.tvNoData)
        btnAddNew = findViewById(R.id.btnAddNew)

        btnAddNew.setOnClickListener {

            intent = Intent(applicationContext, AddQuote::class.java)
            startActivity(intent)
            finish()

        }

        loadData()
        emptyData()
    }

    private fun emptyData() {
        if(databaseManager.getQuote()!!.count > 0){

            recyclerView.visibility = View.VISIBLE
            tvNoData.visibility = View.GONE
            btnAddNew.visibility = View.GONE

        }
        else{

            recyclerView.visibility = View.GONE
            tvNoData.visibility = View.VISIBLE
            btnAddNew.visibility = View.VISIBLE

        }
    }

    private fun loadData() {
        val cursor = databaseManager.getQuote()

        if (cursor!!.moveToFirst()){
            do {
                quoteList.add(Quotes(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                ))

            }while (cursor.moveToNext())

            recyclerView.adapter = QuoteAdapter(quoteList, this)
        }
    }
}
