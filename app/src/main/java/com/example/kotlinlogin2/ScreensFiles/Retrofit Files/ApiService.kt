package com.example.kotlinlogin2.ScreensFiles.Retrofit

import retrofit2.Retrofit

object ApiService {
    private val TAG = "--ApiService"

    //http://192.168.0.99:8081/VaaaN/UserDataGetByLoginNamePasswordGet
    //http://192.168.0.68/CrackApi/

    private const val BASE_URL = "http://192.168.0.64/CrackApi/"



   fun loginApiCall() = Retrofit.Builder()
        .baseUrl(BASE_URL)
     //   .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(ApiWorker.gsonConverter)
        .client(ApiWorker.client)
        .build()
        .create(ApiList::class.java)

   //  retrofit.create(Apilist::class.java)
}