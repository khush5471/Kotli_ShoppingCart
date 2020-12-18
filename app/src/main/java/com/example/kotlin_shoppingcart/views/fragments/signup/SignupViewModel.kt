package com.example.kotlin_shoppingcart.views.fragments.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin_shoppingcart.views.apihelper.model.login.LoginResponse
import com.example.kotlin_shoppingcart.views.apihelper.model.signup.SignupRequests
import com.example.kotlin_shoppingcart.views.apihelper.model.signup.SignupResponse

class SignupViewModel :ViewModel() {

    var mSignupResponse = MutableLiveData<SignupResponse>()
    var mIsLoading = MutableLiveData<Boolean>()

    fun signupUserWithCredintials(request: SignupRequests){
        SignupRepository.signupUserWithCredintials(request,{
            mSignupResponse.value=it
        },{})
    }
}