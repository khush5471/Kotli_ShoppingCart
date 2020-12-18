package com.example.kotlin_shoppingcart.views.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_shoppingcart.views.apihelper.model.login.LoginRequest
import com.example.kotlin_shoppingcart.views.apihelper.model.login.LoginResponse

class LoginViewModel : ViewModel() {
    //    https://3473.000webhostapp.com/images/ic_hoodie.jpg
    var mLoginResponse = MutableLiveData<LoginResponse>()
    var mIsLoading = MutableLiveData<Boolean>()

    fun loginUserWithCredintials(request: LoginRequest) {
        mIsLoading.value = true

        LoginRepository.loginUserWithCredintials(request, {
            mIsLoading.value = false
            mLoginResponse.value = it
        }, { mIsLoading.value = false})
    }
}