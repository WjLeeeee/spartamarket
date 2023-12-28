package com.example.spartamarket

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btn_LogIn = findViewById<ConstraintLayout>(R.id.constraintLayoutLogInBuottn)
        val btn_SignUp = findViewById<ConstraintLayout>(R.id.constraintLayoutSignUpButton)
        val ed_Id = findViewById<EditText>(R.id.ed_id)
        val ed_Pass = findViewById<EditText>(R.id.ed_pass)
//        회원가입 데이터에 아이디가 있는지 확인하고 없으면 재입력, 있으면 로그인 성공
//        로그인 버튼
        btn_LogIn.setOnClickListener {
//            저장된 회원가입 데이터를 불러오고 비교
            val sharedPreference = getSharedPreferences("user_info", Context.MODE_PRIVATE)
            val savedId = sharedPreference.getString("userId", "")
            val savedPw = sharedPreference.getString("userPassword", "")

            val homeIntent = Intent(this, MainActivity::class.java)
            if (ed_Id.text.toString().trim().isNotEmpty() && ed_Pass.text.toString().trim().isNotEmpty() && ed_Id.text.toString() == savedId && ed_Pass.text.toString() == savedPw) {
                Toast.makeText(this, getString(R.string.toast_login), Toast.LENGTH_SHORT).show()
                startActivity(homeIntent)
            }else {
                Toast.makeText(this, getString(R.string.toast_err), Toast.LENGTH_SHORT).show()
            }



        }
//        회원가입 버튼
        btn_SignUp.setOnClickListener {
            val signUpIntent = Intent(this, SignupActivity::class.java)
            startActivity(signUpIntent)
        }




    }
}
