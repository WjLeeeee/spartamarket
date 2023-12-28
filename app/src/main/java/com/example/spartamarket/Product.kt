package com.example.spartamarket

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    val name: String,
    val imageResId: Int,
    val description: String
): Parcelable {
    companion object {
        val productWindowList = listOf(
            Product(
                "에이수스 Vivobook Go 15",
                R.drawable.asus_vivobook_go15,
                "CPU모델명-라이젠5, SSD-256GB, RAM-16GB, 운영체제-Free DOS"
            ),
            Product(
                "레노버 아이디어패드 슬림1 15IJL7 15.6 ",
                R.drawable.lenovo_slim,
                "CPU모델명-셀러론, SSD-128GB, RAM-4GB, 운영체제-WIN11Home"
            ),
            Product(
                "13 노트북 windows 10",
                R.drawable.notebook_13,
                "CPU-인텔 코어 i5-10210u i7-10510u  RAM-DDR4  SSD-512GB, RAM-DDR4 8GB, 운영체제-윈도우10"
            ),
            Product(
                "삼성전자 2021노트북 플러스2 15.6",
                R.drawable.samsung_2021_plus2,
                "CPU모델명-펜티엄, SSD-NVMe256GB, RAM-8GB, 운영체제-WIN10Pro, 모델명-NT550XDA-K24AW"
            ),
            Product(
                "TOPTON 금속 울트라 게이밍 노트북",
                R.drawable.topton_ultra_gaming,
                "CPU모델명-라이젠, SSD-2TB, RAM-36GB, 운영체제-Free DOS"
            )
        )
        val productMacList = listOf(
            Product(
                "Apple 2020 맥북 에어 13",
                R.drawable.macbook_air13,
                "CPU 모델명-M1, 저장용량-256GB, RAM용량-8GB"
            ),
            Product(
                "Apple 2022 맥북 에어 13 M2",
                R.drawable.macbook_air13_m2,
                "CPU 모델명-M2 8코어, GPU 모델명-GPU 8코어, 저장용량-256GB, RAM용량-8GB"
            ),
            Product(
                "Apple 2022 맥북 프로 13 M2",
                R.drawable.macbook_pro13_m2,
                "CPU 모델명-M2, GPU 모델명-GPU 10코어, 저장용량-256GB, RAM용량-8GB"
            ),
            Product(
                "Apple 2021 맥북 프로 16",
                R.drawable.macbook_pro16,
                "CPU 모델명-M1 Pro10코어, GPU 모델명-GPU 16코어, 저장용량-1024GB, RAM용량-16GB"
            ),
            Product(
                "Apple 2021 맥북 프로 14",
                R.drawable.macbook_pro14,
                "CPU 모델명-M1 Pro8코어, GPU 모델명-GPU 14코어, 저장용량-512GB, RAM용량-16GB"
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
