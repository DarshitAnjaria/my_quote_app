package com.android.myquotes.DatabaseManager

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.android.myquotes.model.Quotes

class DatabaseManager(context: Context?, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, dbName, factory, dbVersion) {


    override fun onCreate(db: SQLiteDatabase) {
        val quoteTable = ("CREATE TABLE IF NOT EXISTS " + tableName +
                "(" + COL_ID + " INTEGER NOT NULL CONSTRAINT quoteTable_PK PRIMARY KEY AUTOINCREMENT," +
                COL_QUOTE + " varchar(1000) NOT NULL," +
                COL_AUTHOR + " varchar(500) NOT NULL" + ");")
        Log.i("DatabaseManager", quoteTable)

        db.execSQL(quoteTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val quoteTable : String = "DROP TABLE IF EXISTS " + tableName + ";"
        db.execSQL(quoteTable)

        onCreate(db)
    }

    fun saveQuote(quote : String, author : String){
        val contentValue = ContentValues()
        contentValue.put(COL_QUOTE, quote)
        contentValue.put(COL_AUTHOR, author)

        val sqliteDatabase = this.writableDatabase
        sqliteDatabase.insertOrThrow(tableName, null, contentValue)
        sqliteDatabase.close()
    }

    fun getQuote(): Cursor?{
        val sqliteDatabase : SQLiteDatabase = readableDatabase
        return sqliteDatabase.rawQuery("SELECT * FROM $tableName", null)
    }

    fun updateQuote(quotes: Quotes) : Boolean{
        val sqliteDatabase : SQLiteDatabase = writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_ID, quotes.id)
        contentValues.put(COL_QUOTE, quotes.quote)
        contentValues.put(COL_AUTHOR, quotes.author)

        val _update = sqliteDatabase.update(tableName, contentValues, COL_ID + "=?", arrayOf(quotes.id.toString())).toLong()
        sqliteDatabase.close()
        return Integer.parseInt("$_update") != -1
    }

    companion object{
        private val dbVersion = 1
        private val dbName = "MyQuote.db"
        val tableName = "quoteTable"
        val COL_ID = "_id"
        val COL_QUOTE = "quote"
        val COL_AUTHOR = "author"
    }
}