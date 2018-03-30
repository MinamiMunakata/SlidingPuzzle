package com.example.minami.movebox

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_start.*

class StartActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        play.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        val intent: Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
