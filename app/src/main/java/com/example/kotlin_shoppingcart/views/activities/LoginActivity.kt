package com.example.kotlin_shoppingcart.views.activities

import android.os.Bundle
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.fragments.login.LoginFragment

class LoginActivity :BaseActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(LoginFragment(),false)
    }
}