package com.example.kotlin_shoppingcart.views.fragments.signup

import android.util.Log
import com.example.kotlin_shoppingcart.views.apihelper.ApiHelper
import com.example.kotlin_shoppingcart.views.apihelper.model.login.LoginRequest
import com.example.kotlin_shoppingcart.views.apihelper.model.login.LoginResponse
import com.example.kotlin_shoppingcart.views.apihelper.model.signup.SignupRequests
import com.example.kotlin_shoppingcart.views.apihelper.model.signup.SignupResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object SignupRepository {

    fun signupUserWithCredintials   (request: SignupRequests,
                                 successHandler: (SignupResponse) -> Unit,

                                 connectionError: (Throwable?) -> Unit

    ) {
//        weather?q=montreal&appid=1de6a975068f98520e6710c07429c7bd
        ApiHelper.getService().signupUser(request.email, request.password,request.name).enqueue(object :
            Callback<SignupResponse> {
            override fun onFailure(call: Call<SignupResponse>, t: Throwable) {
                Log.e("Respose Eror is", "okok " + t.toString() + " ")
                connectionError(t)
            }

            override fun onResponse(call: Call<SignupResponse>, response: Response<SignupResponse>) {
                Log.e("Respose is", "okok " + response.body()?.message)
                response.body()?.let {
                    successHandler(it)
                }
            }
        })
    }
}