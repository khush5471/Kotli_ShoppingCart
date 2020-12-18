package com.example.kotlin_shoppingcart.views.fragments.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.activities.HolderActivity
import com.example.kotlin_shoppingcart.views.apihelper.model.login.LoginRequest
import com.example.kotlin_shoppingcart.views.fragments.BaseFragment
import com.example.kotlin_shoppingcart.views.fragments.signup.SignupFragment
import com.example.kotlin_shoppingcart.views.utils.Constants
import com.example.kotlin_shoppingcart.views.utils.Utils
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseFragment() {

    private var mViewModel: LoginViewModel? = null
    private var mLoginedUser: String? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignup.setOnClickListener { clickHandler(it) }
        buttonLogin.setOnClickListener { clickHandler(it) }
        init()
    }

    fun init() {
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        attachObservers()
    }

    private fun clickHandler(view: View) {

        when (view.id) {
            R.id.btnSignup -> {
                Log.e("hello", "world")
                attachFragment(SignupFragment(), true)
            }
            R.id.buttonLogin -> {
                validateLoginFields()
//                startActivityWithAnimation(activity, HolderActivity::class.java, null)
            }
        }
    }

    /*Validates the login fields.
    * */
    private fun validateLoginFields() {
        val email: String = edittext_username.text.toString().trim()
        val password: String = edittext_password.text.toString().trim()
        when {
            !Utils.isNetworkAvailable(context) -> Utils.showSnackBar(
                context,
                getString(R.string.no_internet_message)
            )
            email.isEmpty() -> Utils.showSnackBar(context, "Please enter your email")
            (!Utils.validEmail(email)) -> {
                Utils.showSnackBar(context, "Please enter valid email")
            }
            password.isEmpty() -> Utils.showSnackBar(context, "Please enter your password")

            else -> mViewModel?.loginUserWithCredintials(LoginRequest(password, email))
        }
    }

    /*
    * Attaching observers to the views*/
    private fun attachObservers() {
        mViewModel?.mLoginResponse?.observe(this, Observer {
            it?.let {
                if (it.status.equals("1")) {
                    mLoginedUser = edittext_username.text.toString().trim()
                    context?.let {
                        Utils.saveDataInPreference(
                            it,
                            Constants.CURRENT_LOGGINED_USER,
                            mLoginedUser
                        )
                    }
                    startActivityWithAnimation(activity, HolderActivity::class.java, null)
                } else {
                    Utils.showSnackBar(context, it.message)
                }
            }
        })

        mViewModel?.mIsLoading?.observe(this, Observer {
            if (it) {
                showDialoge()
            } else {
                hideDialoge()
            }
        })
    }
}