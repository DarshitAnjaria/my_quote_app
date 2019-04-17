package com.android.myquotes.model

import java.sql.Timestamp

data class quotes(val id : Int, val quote : String, val author : String, val time : Timestamp)