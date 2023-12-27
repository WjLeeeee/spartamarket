package com.example.spartamarket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {

    private val loginBtn : Button by lazy{findViewById(R.id.login_btn)}
    private val mypageBtn : Button by lazy{findViewById(R.id.mypage_btn)}


    private lateinit var scrollViewMac: ScrollView
    private lateinit var scrollViewWindow: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scrollViewMac = findViewById(R.id.scrollviewMac)
        scrollViewWindow = findViewById(R.id.scrollviewWindow)

        loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        mypageBtn.setOnClickListener {
            val intent = Intent(this, MypageActivity::class.java)
            startActivity(intent)
        }
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