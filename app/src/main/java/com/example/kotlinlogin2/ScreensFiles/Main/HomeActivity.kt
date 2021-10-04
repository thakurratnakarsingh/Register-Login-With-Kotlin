package com.example.kotlinlogin2.ScreensFiles.Main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.kotlinlogin2.R
import com.example.kotlinlogin2.ScreensFiles.Models.UserResponse
import com.example.kotlinlogin2.ScreensFiles.Retrofit.ApiService
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class HomeActivity : AppCompatActivity() {

    var userId: String = "";

    private lateinit var txt_name: TextView
    private lateinit var txt_email: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setTitle("Home")

        val intent = getIntent()
        userId = intent.getIntExtra("id", 0).toString()

        txt_name = findViewById(R.id.txt_name) as TextView
        txt_email = findViewById(R.id.txt_email) as TextView

        getUser()

    }

    private fun getUser() {
        ApiService.loginApiCall().getUser(userId).enqueue(object : retrofit2.Callback<UserResponse> {
            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                Log.d("Response User ::::", response.body().toString())
                if (response.body()!!.status){
                    txt_name.setText(response.body()!!.data.username)
                    txt_email.setText(response.body()!!.data.email)
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                            Log.d("error::::",t?.message)
            }

        })
    }
}