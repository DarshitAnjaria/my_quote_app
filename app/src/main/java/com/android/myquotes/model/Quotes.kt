package com.android.myquotes.model

class Quotes{

    var id : Int = 0
    var quote : String? = null
    var author : String? = null

    constructor(id: Int, quote: String?, author: String?) {
        this.id = id
        this.quote = quote
        this.author = author
    }

}