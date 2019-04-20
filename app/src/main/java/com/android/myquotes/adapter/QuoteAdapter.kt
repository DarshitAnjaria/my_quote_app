package com.android.myquotes.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.database.Cursor
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.myquotes.DatabaseManager.DatabaseManager
import com.android.myquotes.R
import com.android.myquotes.model.Quotes
import kotlin.coroutines.CoroutineContext

class QuoteAdapter(val quoteList : ArrayList<Quotes>, val context : Context): RecyclerView.Adapter<QuoteAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuote : TextView = itemView.findViewById(R.id.tvShowQuote)
        val tvAuthor : TextView = itemView.findViewById(R.id.tvShowAuthor)
        val btnUpdate : Button = itemView.findViewById(R.id.btnEditQuote)
        val btnDelete : Button = itemView.findViewById(R.id.btnDeleteQuote)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): QuoteAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.itemview, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return quoteList.size
    }

    override fun onBindViewHolder(holder: QuoteAdapter.ViewHolder, position: Int) {
        holder.tvAuthor.text = quoteList[position].author
        holder.tvQuote.text = quoteList[position].quote

        holder.btnUpdate.setOnClickListener {
            val alertDialog = AlertDialog.Builder(context)
            val inflater = LayoutInflater.from(context)
            val v = inflater.inflate(R.layout.activity_add_quote, null)

            val etUpdateQuote : EditText = v.findViewById(R.id.etNewQuote)
            val etUpdateAuthor : EditText = v.findViewById(R.id.etAuthorName)
            val btnUpdate : Button = v.findViewById(R.id.btnSubmit)

            etUpdateQuote.setText(quoteList[position].quote.toString())
            etUpdateAuthor.setText(quoteList[position].author.toString())

            alertDialog.setView(v)

            val dialog = alertDialog.create()
            dialog.show()

            btnUpdate.setOnClickListener {
                val new_quote = etUpdateQuote.text.toString()
                val new_author = etUpdateAuthor.text.toString()

                val db = DatabaseManager(context, null)
                val new_data = Quotes(quoteList[position].id, new_quote, new_author)
                db.updateQuote(new_data)
                Toast.makeText(context, "Quote by " + new_author + " updated", Toast.LENGTH_LONG).show()

                quoteList.clear()
                loadDataAgain()
                holder.tvQuote.text = quoteList[position].quote
                holder.tvAuthor.text = quoteList[position].author
                dialog.dismiss()
            }

        }

        holder.btnDelete.setOnClickListener {

            val db = DatabaseManager(context, null)

            val alertDialog = AlertDialog.Builder(context)

            val layoutInflater = LayoutInflater.from(context)
            val v = layoutInflater.inflate(R.layout.custom_dialog, null)
            alertDialog.setView(v)

            val dialog = alertDialog.create()
            dialog.show()

            val yes : TextView = v.findViewById(R.id.tvYes)
            val no : TextView = v.findViewById(R.id.tvNo)

            yes.setOnClickListener {
                db.deleteQuote(quoteList[position].id)
                Toast.makeText(context, "Quote by " + quoteList[position].author + " deleted", Toast.LENGTH_LONG).show()
                loadDataAgain()
                dialog.dismiss()
            }

            no.setOnClickListener {
                dialog.dismiss()
            }
        }
    }

    private fun loadDataAgain() {
        val db = DatabaseManager(context, null)
        val cursor = db.getQuote()

        if (cursor!!.moveToFirst()){
            quoteList.clear()
            do {
                quoteList.add(Quotes(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                ))

            }while (cursor.moveToNext())

            notifyDataSetChanged()
        }
    }

}