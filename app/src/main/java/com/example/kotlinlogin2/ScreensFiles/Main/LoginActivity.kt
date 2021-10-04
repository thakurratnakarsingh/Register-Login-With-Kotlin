package com.example.kotlinlogin2.ScreensFiles.Main

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.RemoteCallbackList
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.example.kotlinlogin2.R
import com.example.kotlinlogin2.ScreensFiles.Models.SigninRequest
import com.example.kotlinlogin2.ScreensFiles.Models.SigninResponse
import com.example.kotlinlogin2.ScreensFiles.Retrofit.ApiService
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class LoginActivity : Activity(), View.OnClickListener {

    private lateinit var ed_email : TextInputEditText
    private lateinit var ed_password : TextInputEditText
    private lateinit var btn_signin : MaterialButton
    private lateinit var txt_sign_up : AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ed_email = findViewById(R.id.ed_email) as TextInputEditText
        ed_password = findViewById(R.id.ed_password) as TextInputEditText
        btn_signin = findViewById(R.id.btn_signin) as MaterialButton
        txt_sign_up = findViewById(R.id.txt_sign_up) as AppCompatTextView

        btn_signin.setOnClickListener(this)
        txt_sign_up.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.txt_sign_up -> {
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            }
            R.id.btn_signin -> {
                if (validation()) {
                    val json = JSONObject()
                    json.put("email", ed_email.text.toString())
                    json.put(" Password", ed_password.text.toString())

                    ApiService.loginApiCall().doLogin(
                        SigninRequest(
                            ed_email.text.toString(),ed_password.text.toString()
                        )
                    ).enqueue(object : retrofit2.Callback<SigninResponse> {
                        override fun onResponse(
                            call: Call<SigninResponse>,
                            response: Response<SigninResponse>
                        ) {

                            Log.d("Response::::", response.body().toString())
                            if (response.body()!!.status){
                                finish()
                                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                                intent.putExtra("id",response.body()!!.data.id)
                                startActivity(intent)
                            }else{
                                Toast.makeText(applicationContext, response.body()!!.message, Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<SigninResponse>, t: Throwable) {
                        }

                    })
                }
            }
        }
    }

    fun validation(): Boolean {
        var value = true

        val password = ed_password.text.toString().trim()
        val name = ed_email.text.toString().trim()

        if (password.isEmpty()) {
            ed_password.error = "Password required"
            ed_password.requestFocus()
            value = false
        }

        if (name.isEmpty()) {
            ed_email.error = "Email required"
            ed_email.requestFocus()
            value = false
        }

        return value;
    }
}
