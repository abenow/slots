package com.template

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private var intent: Intent? = null
    private var textView: TextView? = null
    private var progress = 0
    private var pbHorizontal: ProgressBar? = null

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.load_game)

        textView = findViewById(R.id.textView)
        pbHorizontal = findViewById(R.id.progressBar)

        CoroutineScope(Dispatchers.Main).launch {
            repeat(100) {
                delay(30)
                progress++
                pbHorizontal!!.progress = progress
                textView!!.text = "$progress%"
            }


            intent = Intent(this@MainActivity, StartGame::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        CoroutineScope(Dispatchers.Main).coroutineContext.cancelChildren()
    }
}