package com.android.myquotes.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.android.myquotes.R
import com.android.myquotes.model.Quotes

class quoteAdapter(val quoteList : ArrayList<Quotes>): RecyclerView.Adapter<quoteAdapter.ViewHolder>(){


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvQuote : TextView = itemView.findViewById(R.id.tvShowQuote)
        val tvAuthor : TextView = itemView.findViewById(R.id.tvShowAuthor)
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): quoteAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.itemview, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return quoteList.size
    }

    override fun onBindViewHolder(holder: quoteAdapter.ViewHolder, position: Int) {
        holder.tvAuthor.text = quoteList[position].author
        holder.tvQuote.text = quoteList[position].quote
    }

}