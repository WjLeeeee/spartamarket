package com.example.spartamarket

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import java.util.regex.Pattern

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
        val confirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword)

        val signUpBtn = findViewById<Button>(R.id.signUpBtn)
        signUpBtn.setOnClickListener {


            // 입력값 받아오기
            val id = userEmail.text.toString()
            val password = userPassword.text.toString()
            val name = userName.text.toString()
            val phoneNum = userPhoneNum.text.toString()
            val confirmPwd = confirmPassword.text.toString()

            // 빈칸이 있을 경우, 토스트 메세지 출력
            if(id.isEmpty() || password.isEmpty() || name.isEmpty() || phoneNum.isEmpty()) {
                Toast.makeText(this, "빈 칸을 모두 채워주세요.", Toast.LENGTH_SHORT).show()
            }
            // 아이디에 이메일 형식으로 입력하지 않을 경우, 토스트 메세지 출력
            else if(!Patterns.EMAIL_ADDRESS.matcher(id).matches()) {
                Toast.makeText(this, "아이디를 이메일 형식으로 입력해주세요", Toast.LENGTH_SHORT).show()
            }
            // 비밀번호에 특수문자가 없을 경우, 토스트 메세지 출력
            else if(!containsSpecialCharacter(password)) {
                Toast.makeText(this, "비밀번호에 특수문자를 포함하여 주세요", Toast.LENGTH_SHORT).show()
            }
            // 비밀번호가 6자 이하인 경우 토스트 메세지 출력
            else if(password.length < 6) {
                Toast.makeText(this, "비밀번호를 6자 이상으로 입력해주세요", Toast.LENGTH_SHORT).show()
            }

            // 약관동의에 체크하지 않을 경우, 토스트 메세지 출력
            else if(!checkBoxAgree.isChecked) {
                Toast.makeText(this, "약관에 동의해 주세요.", Toast.LENGTH_SHORT).show()
            }

            else if(password != confirmPwd) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            }
            // 조건을 충족할 경우 회원가입 진행
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
    
    // 특수문자 포함 여부를 확인
    private fun containsSpecialCharacter(password: String): Boolean {
        val pattern: Pattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]")
        return pattern.matcher(password).find()
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