package com.android.myquotes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddQuote : AppCompatActivity() {

    lateinit var toolbar: Toolbar
    lateinit var etQuote : EditText
    lateinit var etAuthor : EditText
    lateinit var btnSubmit : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_quote)

        toolbar = findViewById(R.id.addQuoteToolbar)
        setSupportActionBar(toolbar)

        etQuote = findViewById(R.id.etNewQuote)
        etAuthor = findViewById(R.id.etAuthorName)

        btnSubmit = findViewById(R.id.btnSubmit)

        btnSubmit.setOnClickListener {
            saveQuote()
        }
    }

    private fun saveQuote() {

        val quote : String = etQuote.text.toString()
        val author : String = etAuthor.text.toString()

        if (quote.equals("")){
            etQuote.error = "Please enter quote"
            etQuote.requestFocus()
            return
        }
        else{
            etQuote.error = null
        }

        if (author.equals("")){
            etAuthor.error = "Please enter author name"
            etAuthor.requestFocus()
            return
        }
        else{
            etAuthor.error = null
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater : MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.optMenu -> Toast.makeText(applicationContext, "View Quotes", Toast.LENGTH_LONG).show()

        }
        return super.onOptionsItemSelected(item)
    }
}
