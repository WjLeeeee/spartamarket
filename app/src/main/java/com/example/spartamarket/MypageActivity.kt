package com.example.spartamarket

import android.content.Intent
import android.graphics.Color

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat

class MypageActivity : AppCompatActivity() {

    private lateinit var btnLogout: Button
    private lateinit var btnBuy: Button

    //(뷰 Id,클릭했는가)
    private val cardViewIdMap = mutableMapOf<Int, Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)
        //button id 연결
        btnLogout = findViewById(R.id.btn_logout)

        btnLogout.setOnClickListener {
            finish()
        }

        //intent 수신


        //장바구니,주문상품 리스트를 출력하는 부분
        viewCartList()
        viewOrderList()
        setOnCardView()

    }


    private fun viewCartList() {
        //객체에 따른 레이아웃에 CardView 값 추가
        var layoutCart = findViewById<LinearLayout>(R.id.cart_layout)

        //layout_card의 xml 속성값을 copy 하고싶다.
        var layoutCard = findViewById<ConstraintLayout>(R.id.layout_card)
        var cardViewParameter = layoutCard.layoutParams
        var imageViewParameter = layoutCard.getViewById(R.id.iv_card).layoutParams
        var textViewParameter = layoutCard.getViewById(R.id.tv_card).layoutParams
        MainActivity.list.basketList.forEach {
            var cardView = CardView(this)
            var constraintLayout = ConstraintLayout(this)
            val imageView = ImageView(this)
            val textView = TextView(this)


            //속성 정리
            constraintLayout.layoutParams = layoutCard.layoutParams
            cardView.layoutParams = cardViewParameter
            imageView.layoutParams = imageViewParameter
            textView.layoutParams = textViewParameter

            //Product 의 값 넣기
            imageView.setImageResource(it?.imageResId ?: 0)
            textView.setText(it?.name)
            textView.setBackgroundColor(Color.parseColor("#FFFFFF"))

            //View ID 지정
            cardView.id = ViewCompat.generateViewId()
            cardViewIdMap.put(cardView.id, false)
            //View에 추가
            cardView.addView(constraintLayout)
            constraintLayout.addView(imageView)
            constraintLayout.addView(textView)

            layoutCart.addView(cardView)
        }

    }

    private fun viewOrderList() {
        //객체에 따른 레이아웃에 CardView 값 추가
        var layoutCart = findViewById<LinearLayout>(R.id.order_layout)

        //layout_card의 xml 속성값을 copy 하고싶다.
        var layoutCard = findViewById<ConstraintLayout>(R.id.layout_card_horizontal)
        var cardViewParameter = layoutCard.layoutParams
        var imageViewParameter = layoutCard.getViewById(R.id.iv_card).layoutParams
        var textViewParameter = layoutCard.getViewById(R.id.tv_card).layoutParams

        MainActivity.list.buyList.forEach {
            var cardView = CardView(this)
            var constraintLayout = ConstraintLayout(this)
            val imageView = ImageView(this)
            val textView = TextView(this)

            //속성 정리
            constraintLayout.layoutParams = layoutCard.layoutParams
            cardView.layoutParams = cardViewParameter
            imageView.layoutParams = imageViewParameter
            textView.layoutParams = textViewParameter

            imageView.setImageResource(it?.imageResId ?: 0)
            textView.setText(it?.name)
            textView.setBackgroundColor(Color.parseColor("#FFFFFF"))

            cardView.addView(constraintLayout)
            constraintLayout.addView(imageView)
            constraintLayout.addView(textView)

            layoutCart.addView(cardView)
        }
    }

    private fun setOnCardView() {
        cardViewIdMap.forEach { (id, _) ->
            val cardView = findViewById<CardView>(id)
            cardView.setOnClickListener {
                val isClicked = cardViewIdMap[id]
                if (isClicked == true) {
                    cardViewIdMap[id] = false
                    //cardView background color 변경
                    cardView.setBackgroundColor(Color.parseColor("#FFFFFF"))
                } else {
                    cardViewIdMap[id] = true
                    //cardView background color 변경
                    cardView.setBackgroundColor(Color.parseColor("#6750a4"))
                }
            }
        }
    }

}