package com.example.spartamarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MypageActivity : AppCompatActivity() {

    private lateinit var btnLogout :Button
    private lateinit var btnBuy :Button

    private var buyList = mutableListOf<Product?>()
    private var basketList = mutableListOf<Product?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)
        //button id 연결
        btnLogout = findViewById<Button>(R.id.btn_logout)


        btnLogout.setOnClickListener {
            finish()
        }

        //intent 수신
    }
}