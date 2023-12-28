package com.example.spartamarket

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class SignupActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE)

        val userEmail = findViewById<EditText>(R.id.editTextUserEmail)
        val userPassword = findViewById<EditText>(R.id.editTextUserPassword)
        val userName = findViewById<EditText>(R.id.editTextUsername)
        val userPhoneNum = findViewById<EditText>(R.id.editTextUserPhoneNum)
        val checkBoxAgree = findViewById<CheckBox>(R.id.checkBoxAgree)

        val signUpBtn = findViewById<Button>(R.id.signUpBtn)
        signUpBtn.setOnClickListener {


            // 입력값 받아오기
            val id = userEmail.text.toString()
            val password = userPassword.text.toString()
            val name = userName.text.toString()
            val phoneNum = userPhoneNum.text.toString()

            // 빈칸이 있을 경우 토스트 메세지 출력
            if(id.isEmpty() || password.isEmpty() || name.isEmpty() || phoneNum.isEmpty()) {
                Toast.makeText(this, "빈 칸을 모두 채워주세요.", Toast.LENGTH_SHORT).show()
            }
            else if(!checkBoxAgree.isChecked) {
                Toast.makeText(this, "약관에 동의해 주세요.", Toast.LENGTH_SHORT).show()
            }
            // 빈칸이 없을 경우 회원가입 진행
            else {
                // SharedPreferences를 사용하여 사용자 데이터 저장
                saveUserData(id, password, name, phoneNum)

                Toast.makeText(this, "스파르타마켓에 정상적으로 회원가입 되었습니다.", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

                finish()
            }

        }

        // 뒤로가기 버튼
        val backButton = findViewById<ImageView>(R.id.backBtn)
        backButton.setOnClickListener {
            finish()
        }

    }

    // 입력받은 자료 저장
    fun saveUserData(id:String, password:String, name:String, phoneNum:String) {
        val editor = sharedPreferences.edit()
        editor.putString("userId", id)
        editor.putString("userPassword", password)
        editor.putString("userName", name)
        editor.putString("userphoneNum", phoneNum)
        editor.apply()
    }
}