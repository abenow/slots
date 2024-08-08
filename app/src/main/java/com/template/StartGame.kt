package com.template

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class StartGame : AppCompatActivity() {
    private var startButton: ImageButton? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_game)
        startButton = findViewById(R.id.start_button_id)
    }

    fun startButtonOnClick(view: View){
        val intent = Intent(this, Slots::class.java)
        startActivity(intent)
    }
}