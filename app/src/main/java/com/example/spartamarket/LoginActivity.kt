package com.example.spartamarket

import android.content.Intent
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
            val homeIntent = Intent(this, MainActivity::class.java)
            if (ed_Id.text.toString().isNotEmpty() && ed_Pass.text.toString().isNotEmpty()) {
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
