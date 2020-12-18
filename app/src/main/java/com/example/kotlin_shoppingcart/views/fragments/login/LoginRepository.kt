package com.example.kotlin_shoppingcart.views.fragments.login

import android.util.Log
import com.example.kotlin_shoppingcart.views.apihelper.ApiError
import com.example.kotlin_shoppingcart.views.apihelper.ApiHelper
import com.example.kotlin_shoppingcart.views.apihelper.model.login.LoginRequest
import com.example.kotlin_shoppingcart.views.apihelper.model.login.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object LoginRepository {


    fun loginUserWithCredintials(request: LoginRequest,
                                 successHandler: (LoginResponse) -> Unit,

                                 connectionError: (Throwable?) -> Unit

    ) {
//        weather?q=montreal&appid=1de6a975068f98520e6710c07429c7bd
        ApiHelper.getService().loginUser(request.email, request.password).enqueue(object :
            Callback<LoginResponse> {
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("Respose Eror is", "okok " + t.toString() + " ")
                connectionError(t)
            }

            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                Log.e("Respose is", "okok " + response.body()?.message)
                response.body()?.let {
                successHandler(it)
                }
            }
        })
    }
}