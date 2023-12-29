package com.example.spartamarket

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    private val chooseProductImg :ImageView by lazy { findViewById(R.id.chooseProductImg) }
    private val chooseProductName :TextView by lazy { findViewById(R.id.chooseProductName) }
    private val chooseProductDes :TextView by lazy { findViewById(R.id.chooseProductDes) }
    private val cancleBtn : Button by lazy { findViewById(R.id.btn_cancle) }
    private val purchaseBtn : Button by lazy { findViewById(R.id.btn_purchase) }
    private val basketBtn : Button by lazy { findViewById(R.id.btn_basket) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val productNameResId = intent.getIntExtra("productNameResId", 0)
        val productImgResId = intent.getIntExtra("productImageResId", 0)
        val productDescriptionResId = intent.getIntExtra("productDescriptionResId", 0)

        val productName = getString(productNameResId)
        val productDescription = getString(productDescriptionResId)

        chooseProductImg.setImageResource(productImgResId)
        chooseProductName.text = productName
        chooseProductDes.text = productDescription
        val selectedProduct = Product(productNameResId, productImgResId, productDescriptionResId)
        // 장바구니 버튼
        basketBtn.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("BasketProduct",selectedProduct)
            setResult(Activity.RESULT_OK,returnIntent)
            finish()
        }

        // 구매 버튼
        purchaseBtn.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("BuyProduct",selectedProduct)
            setResult(Activity.RESULT_OK,returnIntent)
            finish()
        }


        cancleBtn.setOnClickListener {
            finish()
            overridePendingTransition(0, R.anim.slide_down)
        }

    }
}