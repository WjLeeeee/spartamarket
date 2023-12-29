package com.example.spartamarket

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import com.example.spartamarket.MainActivity.list.basketList
import com.example.spartamarket.MainActivity.list.buyList
import org.w3c.dom.Text

class MypageActivity : AppCompatActivity() {


    private lateinit var btnLogout: Button
    private lateinit var btnBuy: Button
    private lateinit var btnBack : ImageView

    //(뷰 Id,클릭했는가)
    private lateinit var cardViewIdMap : MutableMap<Int, Boolean>
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mypage)

        cardViewIdMap = mutableMapOf()
        //button id 연결
        btnLogout = findViewById(R.id.btn_logout)
        btnBuy = findViewById(R.id.btn_buy)
        btnBack = findViewById(R.id.iv_back)

        viewUserInfo()
        setOnButtonCallBacks()


        //장바구니,주문상품 리스트를 출력하는 부분
        viewCartList()
        viewOrderList()
        onClickedCardView()

    }

    //내부저장된 값을 불려들어 유저의 정보를 입력
    private fun viewUserInfo(){
        sharedPreferences = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val name = findViewById<TextView>(R.id.tv_name)
        val email = findViewById<TextView>(R.id.tv_email)
        val getNameSharedPrefer = sharedPreferences.getString("userName","이름")
        val getEmailSharedPrefer = sharedPreferences.getString("userId","이메일")
        name.setText(getNameSharedPrefer)
        email.setText(getEmailSharedPrefer)
    }
    fun updateUI(){
        setContentView(R.layout.activity_mypage)
        cardViewIdMap = mutableMapOf()
        setOnButtonCallBacks()
        viewCartList()
        viewOrderList()
        onClickedCardView()
    }

    // 저장된 list값을 읽어들어서 장바구니목록을 보여준다
    private fun viewCartList() {
        //객체에 따른 레이아웃에 CardView 값 추가
        var layoutCart = findViewById<LinearLayout>(R.id.cart_layout)

        //layout_card의 xml 속성값을 copy 하고싶다.
        var layoutCard = findViewById<ConstraintLayout>(R.id.layout_card)
        var cardViewParams = layoutCard.layoutParams
        var imageViewParams = layoutCard.getViewById(R.id.iv_card).layoutParams
        var textViewParams = layoutCard.getViewById(R.id.tv_card).layoutParams
        basketList.forEach {
            var cardView = CardView(this)
            var constraintLayout = ConstraintLayout(this)
            val imageView = ImageView(this)
            val textView = TextView(this)


            //속성 정리
            constraintLayout.layoutParams = layoutCard.layoutParams
            cardView.layoutParams = cardViewParams
            imageView.layoutParams = imageViewParams
            textView.layoutParams = textViewParams

            //Product 의 값 넣기
            imageView.setImageResource(it?.imageResId ?: 0)
            textView.setText(it!!.nameResId)
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

    // 저장된 list값을 읽어들어서 주문목록을 보여준다
    private fun viewOrderList() {
        //객체에 따른 레이아웃에 CardView 값 추가
        var layoutCart = findViewById<LinearLayout>(R.id.order_layout)

        //layout_card의 xml 속성값을 copy 하고싶다.
        var layoutCard = findViewById<ConstraintLayout>(R.id.layout_card_horizontal)
        var cardViewParams = layoutCard.layoutParams
        var imageViewParams = layoutCard.getViewById(R.id.iv_card).layoutParams
        var textViewParams = layoutCard.getViewById(R.id.tv_card).layoutParams

        buyList.forEach {
            var cardView = CardView(this)
            var constraintLayout = ConstraintLayout(this)
            val imageView = ImageView(this)
            val textView = TextView(this)

            //속성 정리
            constraintLayout.layoutParams = layoutCard.layoutParams
            cardView.layoutParams = cardViewParams
            imageView.layoutParams = imageViewParams
            textView.layoutParams = textViewParams

            imageView.setImageResource(it?.imageResId ?: 0)
            textView.setText(it!!.nameResId)
            textView.setBackgroundColor(Color.parseColor("#FFFFFF"))

            cardView.addView(constraintLayout)
            constraintLayout.addView(imageView)
            constraintLayout.addView(textView)

            layoutCart.addView(cardView)
        }
    }


    //장바구니 카드뷰 클릭시 이벤트처리
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


    //버튼 클릭시 콜백이벤트 처리
    private fun setOnButtonCallBacks(){

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
            updateUI()
        }

        btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {


            }

            setResult(RESULT_OK, intent)

            if (!isFinishing) finish()
            Toast.makeText(this, "로그아웃이 되었습니다.", Toast.LENGTH_SHORT).show()
            //로그아웃 처리 필요
            finish()
        }

        btnBack.setOnClickListener {
            finish()
        }
    }
}