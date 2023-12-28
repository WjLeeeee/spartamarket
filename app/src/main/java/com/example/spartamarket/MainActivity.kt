package com.example.spartamarket

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val loginBtn: Button by lazy { findViewById(R.id.login_btn) }
    private val mypageBtn: Button by lazy { findViewById(R.id.mypage_btn) }

    private lateinit var scrollView: ScrollView
    private lateinit var productListInnerLayout: LinearLayout

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

    //기본 카테고리는 mac으로 설정
    private var selectedCategory:String = "mac"
    private lateinit var productList: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //스크롤뷰 받아오기
        scrollView = findViewById(R.id.scrollView)
        //스크롤뷰 안에있는 레이아웃 받아오기
        productListInnerLayout = findViewById(R.id.productListInnerLayout)

        //레이아웃에 데이터 띄우기
        updateProductScrollView(selectedCategory)

        loginBtn.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        mypageBtn.setOnClickListener {
            val intent = Intent(this, MypageActivity::class.java)
            startActivity(intent)
        }
    }

    /**
     * 스크롤뷰에 데이터 띄우기
     */
    private fun updateProductScrollView(category: String) {
        productList = Product.getProductList(category)
        productListInnerLayout.removeAllViews()
        for (product in productList) {
            //product_item형식으로 scrollView에 띄울예정
            val productView = layoutInflater.inflate(R.layout.product_item, null)
            //product_item의 값들 초기화
            val productImage = productView.findViewById<ImageView>(R.id.productImageView)
            val productName = productView.findViewById<TextView>(R.id.productNameTextView)
            productImage.setImageResource(product.imageResId)
            productName.text = product.name

            //상품 클릭시
            productView.setOnClickListener {
                val intent = Intent(this, DetailActivity::class.java)
                intent.putExtra("productImageResId", product.imageResId)
                intent.putExtra("productName", product.name)
                intent.putExtra("productDescription", product.description)
                startActivity(intent)
                overridePendingTransition(R.anim.slide_up, 0) //아래서 위로 올라오는 애니메이션
            }
            productListInnerLayout.addView(productView)
        }
    }

    /**
     * 카테고리 선택시 상품 변경
     */
    fun productChange(view:View){
        when (view.id) {
            R.id.img_mac -> {
                selectedCategory = "mac"
            }
            R.id.img_window -> {
                selectedCategory = "window"
            }
        }
        updateProductScrollView(selectedCategory)
    }
}