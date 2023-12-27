package com.example.spartamarket

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import kotlinx.parcelize.Parcelize

class MainActivity : AppCompatActivity() {

    private val loginBtn: Button by lazy { findViewById(R.id.login_btn) }
    private val mypageBtn: Button by lazy { findViewById(R.id.mypage_btn) }


    private lateinit var scrollViewMac: ScrollView
    private lateinit var scrollViewWindow: ScrollView
    private lateinit var productWindow: LinearLayout
    private lateinit var productMac: LinearLayout

    object list {
        var buyList = mutableListOf<Product?>()
        var basketList = mutableListOf<Product?>()

    }

    //결과를 받는 Activitiy에서 선언
    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                //받은 데이터 처리
                val buyProduct = result.data?.getParcelableExtra<Product>("BuyProduct")
                val basketProduct = result.data?.getParcelableExtra<Product>("BasketProduct")

                if (buyProduct != null) list.buyList.add(buyProduct)
                if (basketProduct != null) list.basketList.add(basketProduct)
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultLauncher

        scrollViewMac = findViewById(R.id.scrollviewMac)
        scrollViewWindow = findViewById(R.id.scrollviewWindow)
        productWindow = findViewById(R.id.productWindow)
        productMac = findViewById(R.id.productMac)




        loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        mypageBtn.setOnClickListener {
            val intent = Intent(this, MypageActivity::class.java)
            startActivity(intent)
        }


    }

    fun productChange(view: View) {
        when (view.id) {
            R.id.img_mac -> {
                scrollViewWindow.isVisible = false
                scrollViewMac.isVisible = true
            }

            R.id.img_window -> {
                scrollViewWindow.isVisible = true
                scrollViewMac.isVisible = false
            }
        }
    }

    fun productWindowClicked(view: View) {
        val index = productWindow.indexOfChild(view)
        val selectedProduct = Product.productWindowList[index]

        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(
                "selectedProduct",
                selectedProduct
            )
        }
        resultLauncher.launch(intent)
    }

    fun productMacClicked(view: View) {
        val index = productMac.indexOfChild(view)
        val selectedProduct = Product.productMacList[index]

        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("selectedProduct", selectedProduct)
        resultLauncher.launch(intent)
    }

}