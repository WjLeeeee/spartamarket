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
    private lateinit var userEmail: EditText
    private lateinit var confirmPassword: EditText
    private lateinit var errorMessageTextViewPwd: TextView
    private lateinit var errorMessageTextViewId: TextView
    private lateinit var signUpBtn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        sharedPreferences = getSharedPreferences("user_info", MODE_PRIVATE)

        userEmail = findViewById<EditText>(R.id.editTextUserEmail)
        userPassword = findViewById<EditText>(R.id.editTextUserPassword)
        val userName = findViewById<EditText>(R.id.editTextUsername)
        val userPhoneNum = findViewById<EditText>(R.id.editTextUserPhoneNum)
        val checkBoxAgree = findViewById<CheckBox>(R.id.checkBoxAgree)
        confirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword)
        errorMessageTextViewPwd = findViewById<TextView>(R.id.errorMessageTextViewPwd)
        errorMessageTextViewId = findViewById<TextView>(R.id.errorMessageTextViewId)
        signUpBtn = findViewById<Button>(R.id.signUpBtn)


        userEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                checkEmailMatch(userEmail.text.toString())
            }

            override fun afterTextChanged(editable: Editable?) {

            }
        })

        confirmPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                checkPasswordMatch()
            }

            override fun afterTextChanged(editable: Editable?) {

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

            // 약관동의에 체크하지 않을 경우, 토스트 메세지 출력
            else if(!checkBoxAgree.isChecked) {
                Toast.makeText(this, "약관에 동의해 주세요.", Toast.LENGTH_SHORT).show()
            }

            // 조건을 충족할 경우 회원가입 진행
            else {
                // 에러 메세지 텍스트뷰를 숨기기
                val errorMessegeTextView = findViewById<TextView>(R.id.errorMessageTextViewPwd)
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


    // 이메일 형식인지 확인하는 메세지 로직
    private fun checkEmailMatch(email:String) {

        if (isEmailFormatValid(email)) {
            errorMessageTextViewId.visibility = View.GONE
        } else {
            errorMessageTextViewId.visibility = View.VISIBLE
            errorMessageTextViewId.text = "이메일 형식으로 입력하여 주세요."
        }
    }

    // 비빌번호 확인 메세지 로직
    private fun checkPasswordMatch() {
        val password = userPassword.text.toString().trim()
        val confirmPwd = confirmPassword.text.toString().trim()

        //특수문자 포함 여부 확인
        val containsSpecialCharacter = containsSpecialCharacter(password)

        if (password == confirmPwd && containsSpecialCharacter) {
            errorMessageTextViewPwd.visibility = View.GONE
            signUpBtn.isEnabled = true
        } else {
            errorMessageTextViewPwd.visibility = View.VISIBLE

            if(password != confirmPwd) {
                errorMessageTextViewPwd.text = "비밀번호가 일치하지 않습니다."
            } else if(password.length < 6) {
                errorMessageTextViewPwd.text = "비밀번호를 6자 이상으로 입력해주세요."
            } else if(confirmPwd.length < 6) {
                errorMessageTextViewPwd.text = "비밀번호를 6자 이상으로 입력해주세요."
            } else if(!containsSpecialCharacter) {
                errorMessageTextViewPwd.text = "비밀번호에 특수문자를 포함해주세요."
            }


            errorMessageTextViewPwd.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_light))
            signUpBtn.isEnabled = false
        }
    }
    
    // 특수문자 포함 여부를 확인
    private fun containsSpecialCharacter(password: String): Boolean {
        val pattern: Pattern = Pattern.compile("[!@#$%^&*(),.?\":{}|<>]")
        return pattern.matcher(password).find()
    }
// 이메일 확인 로직
    private fun isEmailFormatValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
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