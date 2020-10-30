package com.example.kotlin_shoppingcart.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.fragments.splash.SplashFragment

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //FIRST PUSH TEST

        addFragment(SplashFragment(),false)
    }
}