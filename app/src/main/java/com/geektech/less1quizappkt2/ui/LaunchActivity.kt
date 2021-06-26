package com.geektech.less1quizappkt2.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.geektech.less1quizappkt2.R
import com.geektech.less1quizappkt2.extensions.toast

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)
        val thread = Thread {
            try {
                Thread.sleep(800)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } catch (e: InterruptedException) {
                toast(e.message.toString())
            }
        }
        thread.start()
    }
}