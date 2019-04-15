package com.android.myquotes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager

class SplashActivity : AppCompatActivity() {

    val timeOut : Long = 1500
    private var delayHandler : Handler? = null

    internal val runnable : Runnable = Runnable {
        if (!isFinishing){
            val intent = Intent(applicationContext, AddQuote::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE)
//        set flags as fullscreen window
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_splash)

        //initializes the handler
        delayHandler = Handler()

        //navigate with delay
        delayHandler!!.postDelayed(runnable, timeOut)

    }

    override fun onDestroy() {

        if (delayHandler != null){
            delayHandler!!.removeCallbacks(runnable)
        }

        super.onDestroy()
    }
}
