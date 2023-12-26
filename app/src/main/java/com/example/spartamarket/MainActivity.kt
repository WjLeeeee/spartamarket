package com.example.spartamarket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private val windowImage : ImageView by lazy{findViewById(R.id.img_window)}
    private val macImage : ImageView by lazy{findViewById(R.id.img_mac)}

    private lateinit var scrollViewMac: ScrollView
    private lateinit var productMac: LinearLayout
    private lateinit var scrollViewWindow: ScrollView
    private lateinit var productWindow: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scrollViewMac = findViewById(R.id.scrollviewMac)
        productMac = findViewById(R.id.productMac)
        scrollViewWindow = findViewById(R.id.scrollviewWindow)
        productWindow = findViewById(R.id.productWindow)

    }

    fun productChange(view: View){
        when(view.id){
            R.id.img_mac-> {
                scrollViewWindow.isVisible=false
                scrollViewMac.isVisible=true
            }
            R.id.img_window -> {
                scrollViewWindow.isVisible=true
                scrollViewMac.isVisible=false
            }
        }
    }

}