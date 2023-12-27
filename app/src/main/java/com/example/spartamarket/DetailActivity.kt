package com.example.spartamarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailActivity : AppCompatActivity() {

    private val chooseProductImg :ImageView by lazy { findViewById(R.id.chooseProductImg) }
    private val chooseProductName :TextView by lazy { findViewById(R.id.chooseProductName) }
    private val chooseProductDes :TextView by lazy { findViewById(R.id.chooseProductDes) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val selectedProduct = intent.getSerializableExtra("selectedProduct") as? Product

        if(selectedProduct != null){
            chooseProductImg.setImageResource(selectedProduct.imageResId)
            chooseProductName.text = selectedProduct.name
            chooseProductDes.text = selectedProduct.description
        }
    }
}