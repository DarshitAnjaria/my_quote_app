package com.android.myquotes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar

class AddQuote : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_quote)

        toolbar = findViewById(R.id.addQuoteToolbar)
        setSupportActionBar(toolbar)

    }
}
