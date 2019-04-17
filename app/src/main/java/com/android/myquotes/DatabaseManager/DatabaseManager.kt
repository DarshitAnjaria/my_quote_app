package com.android.myquotes.DatabaseManager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseManager(val dbVersion : Int, val dbName : String, val tableName : String,
                      val COL_ID : Int, val COL_QUOTE : String, val COL_AUTHOR : String, val COL_DATE : String,
                      context: Context?,
                      factory: SQLiteDatabase.CursorFactory?,
                      version: Int) : SQLiteOpenHelper(context, dbName, factory, version) {

    override fun onCreate(db: SQLiteDatabase?) {
        val quoteTable : String = "CREATE TABLE IF NOT EXISTS " + tableName +
                "(" + COL_ID + " INTEGER NOT NULL CONSTRAINT quoteTable_PK PRIMARY KEY AUTOINCREMENT, " +
                COL_QUOTE + " varchar(1000) NOT NULL, " +
                COL_AUTHOR + " varchar(500) NOT NULL, " +
                COL_DATE + "datetime NOT NULL" + ")"

        db!!.execSQL(quoteTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val quoteTable : String = "DROP TABLE IF EXISTS " + tableName + ";"
        db!!.execSQL(quoteTable)

        onCreate(db)
    }
}