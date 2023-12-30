package com.example.spartamarket

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val nameResId: Int,
    val imageResId: Int,
    val descriptionResId: Int
): Parcelable {
    companion object {
        val productWindowList = listOf(
            Product(
                R.string.window1,
                R.drawable.asus_vivobook_go15,
                R.string.window1_des
            ),
            Product(
                R.string.window2,
                R.drawable.lenovo_slim,
                R.string.window2_des
            ),
            Product(
                R.string.window3,
                R.drawable.notebook_13,
                R.string.window3_des
            ),
            Product(
                R.string.window4,
                R.drawable.samsung_2021_plus2,
                R.string.window4_des
            ),
            Product(
                R.string.window5,
                R.drawable.topton_ultra_gaming,
                R.string.window5_des
            )
        )
        val productMacList = listOf(
            Product(
                R.string.mac1,
                R.drawable.macbook_air13,
                R.string.mac1_des
            ),
            Product(
                R.string.mac2,
                R.drawable.macbook_air13_m2,
                R.string.mac2_des
            ),
            Product(
                R.string.mac3,
                R.drawable.macbook_pro13_m2,
                R.string.mac3_des
            ),
            Product(
                R.string.mac4,
                R.drawable.macbook_pro16,
                R.string.mac4_des
            ),
            Product(
                R.string.mac5,
                R.drawable.macbook_pro14,
                R.string.mac5_des
            )
        )
        fun getProductList(category: String):List<Product>{
            return when(category){
                "mac" -> productMacList
                "window" -> productWindowList
                else -> emptyList()
            }
        }
    }
}