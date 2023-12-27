package com.example.spartamarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MypageActivity : AppCompatActivity() {

    private lateinit var btnLogout :Button
    private lateinit var btnBuy :Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)
        //button id 연결
        btnLogout = findViewById<Button>(R.id.btn_logout)


        btnLogout.setOnClickListener {
            finish()
        }

        //intent 수신


        //객체에 따른 레이아웃에 CardView 값 추가

    }
}