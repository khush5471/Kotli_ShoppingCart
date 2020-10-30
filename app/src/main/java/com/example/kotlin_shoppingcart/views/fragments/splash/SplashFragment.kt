package com.example.kotlin_shoppingcart.views.fragments.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.activities.LoginActivity
import com.example.kotlin_shoppingcart.views.fragments.BaseFragment

class SplashFragment :
    BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash,container,false);
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed(object :Runnable{
            override fun run() {

                activity?.finish()

                startActivityWithAnimation(activity,LoginActivity::class.java,Bundle())
            }
        },3000)
    }
}