package com.example.spartamarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        val selectedProduct = intent.getParcelableExtra("selectedProduct") as? Product

        if(selectedProduct != null){
            chooseProductImg.setImageResource(selectedProduct.imageResId)
            chooseProductName.text = selectedProduct.name
            chooseProductDes.text = selectedProduct.description
        }

        cancleBtn.setOnClickListener {
            finish()
        }

    }
}