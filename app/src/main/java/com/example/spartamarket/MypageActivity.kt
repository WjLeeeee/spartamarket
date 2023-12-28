package com.example.spartamarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout

class MypageActivity : AppCompatActivity() {

    private lateinit var btnLogout: Button
    private lateinit var btnBuy: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)
        //button id 연결
        btnLogout = findViewById<Button>(R.id.btn_logout)

        btnLogout.setOnClickListener {
            finish()
        }

        //intent 수신


        viewCartList()


    }
    fun viewCartList(){
        //객체에 따른 레이아웃에 CardView 값 추가
        var layoutCart = findViewById<LinearLayout>(R.id.cart_layout)

        //layout_card의 xml 속성값을 copy 하고싶다.
        var layoutCard = findViewById<ConstraintLayout>(R.id.layout_card)

        MainActivity.list.basketList.forEach {
            var cardView = CardView(this)
            var constraintLayout = ConstraintLayout(this)
            val imageView = ImageView(this)
            val textView = TextView(this)

            //속성 정리
            constraintLayout.layoutParams = layoutCard.layoutParams
            imageView.setImageResource(it?.imageResId ?: 0)
            textView.setText(it?.name)

            cardView.addView(constraintLayout)
            constraintLayout.addView(imageView)
            constraintLayout.addView(textView)

            layoutCart.addView(cardView)
        }

    }
}