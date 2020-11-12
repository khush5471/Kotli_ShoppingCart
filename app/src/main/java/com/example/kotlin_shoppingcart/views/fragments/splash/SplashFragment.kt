package com.example.kotlin_shoppingcart.views.fragments.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.activities.LoginActivity
import com.example.kotlin_shoppingcart.views.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_splash.*

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

        lottie_splash.animate().translationX(1600f).setDuration(2000).setStartDelay(3000)
        txt_welcome.animate().translationY(-500f).setDuration(2000).setStartDelay(2000)
        txt_lets_shop.animate().translationY(500f).setDuration(2000).setStartDelay(2000)

        Handler().postDelayed(object :Runnable{
            override fun run() {

                activity?.finish()
                startActivityWithouAnimation(activity,LoginActivity::class.java,Bundle(),true)
            }
        },4000)
    }
}