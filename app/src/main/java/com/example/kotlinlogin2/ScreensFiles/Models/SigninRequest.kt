package com.example.kotlinlogin2.ScreensFiles.Models

import com.google.gson.annotations.SerializedName

/*class SigninRequest (@SerializedName("LoginName") var email : String,
                      @SerializedName("Password") var password : String)*/

 class SigninRequest(@SerializedName("email") var email : String,
                    @SerializedName("password") var Password : String)