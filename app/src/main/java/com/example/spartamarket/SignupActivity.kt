package com.example.spartamarket

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.util.regex.Pattern

class SignupActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var userPassword: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var errorMessageTextView: TextView
    private lateinit var signUpBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE)

        val userEmail = findViewById<EditText>(R.id.editTextUserEmail)
        userPassword = findViewById<EditText>(R.id.editTextUserPassword)
        val userName = findViewById<EditText>(R.id.editTextUsername)
        val userPhoneNum = findViewById<EditText>(R.id.editTextUserPhoneNum)
        val checkBoxAgree = findViewById<CheckBox>(R.id.checkBoxAgree)
        confirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword)
        errorMessageTextView = findViewById<TextView>(R.id.errorMessageTextView)
        signUpBtn = findViewById<Button>(R.id.signUpBtn)


        confirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                // Do nothing
            }

            override fun afterTextChanged(editable: Editable?) {
                checkPasswordMatch()
            }
        })



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


            // 조건을 충족할 경우 회원가입 진행
            else {
                // 에러 메세지 텍스트뷰를 숨기기
                val errorMessegeTextView = findViewById<TextView>(R.id.errorMessageTextView)
                errorMessegeTextView.visibility = View.GONE

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

    // 비빌번호 확인 메세지 로직
    private fun checkPasswordMatch() {
        val password = userPassword.text.toString()
        val confirmPwd = confirmPassword.text.toString()

        if (password == confirmPwd) {
            errorMessageTextView.visibility = View.GONE
            signUpBtn.isEnabled = true
        } else {
            errorMessageTextView.visibility = View.VISIBLE
            errorMessageTextView.text = "비밀번호가 일치하지 않습니다."
            errorMessageTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
            signUpBtn.isEnabled = false
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