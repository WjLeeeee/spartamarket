package com.example.spartamarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MypageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)
        val btnLogout = findViewById<Button>(R.id.btn_logout)


        btnLogout.setOnClickListener {
            finish()
        }
    }
}