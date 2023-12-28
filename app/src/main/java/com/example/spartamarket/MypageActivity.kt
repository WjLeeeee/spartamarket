package com.example.spartamarket

import android.graphics.Color

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.spartamarket.MainActivity.list.basketList
import com.example.spartamarket.MainActivity.list.buyList

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
        btnBuy = findViewById(R.id.btn_buy)

        //button 기능
        btnBuy.setOnClickListener {
            //지워질 list를 저장
            var deleteList = mutableListOf<Product?>()
            cardViewIdMap.filter { it.value }?.forEach { (idx, _) ->
                deleteList.add(basketList[idx])
            }
            while(deleteList.isNotEmpty()){
                buyList.add(deleteList.first())
                basketList.remove(deleteList.first())

                deleteList.removeFirst()
            }
            //UI 업데이트 함수
        }
        btnLogout.setOnClickListener {
            finish()
        }

        //intent 수신


        //장바구니,주문상품 리스트를 출력하는 부분
        viewCartList()
        viewOrderList()
        onClickedCardView()

    }


    private fun viewCartList() {
        //객체에 따른 레이아웃에 CardView 값 추가
        var layoutCart = findViewById<LinearLayout>(R.id.cart_layout)

        //layout_card의 xml 속성값을 copy 하고싶다.
        var layoutCard = findViewById<ConstraintLayout>(R.id.layout_card)
        var cardViewParameter = layoutCard.layoutParams
        var imageViewParameter = layoutCard.getViewById(R.id.iv_card).layoutParams
        var textViewParameter = layoutCard.getViewById(R.id.tv_card).layoutParams
        basketList.forEach {
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
//            cardView.id = ViewCompat.generateViewId()
            cardView.id = basketList.indexOf(it)
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

        buyList.forEach {
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

    private fun onClickedCardView() {
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