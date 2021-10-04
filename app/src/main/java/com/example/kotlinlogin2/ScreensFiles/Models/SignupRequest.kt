package com.example.kotlinlogin2.ScreensFiles.Models

import com.google.gson.annotations.SerializedName

class SignupRequest(@SerializedName("email") var email: String,
                    @SerializedName("username") var username: String,
                    @SerializedName("password") var password: String)