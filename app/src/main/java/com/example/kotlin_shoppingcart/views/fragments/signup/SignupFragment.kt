package com.example.kotlin_shoppingcart.views.fragments.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.activities.HolderActivity
import com.example.kotlin_shoppingcart.views.apihelper.model.login.LoginRequest
import com.example.kotlin_shoppingcart.views.apihelper.model.signup.SignupRequests
import com.example.kotlin_shoppingcart.views.fragments.BaseFragment
import com.example.kotlin_shoppingcart.views.fragments.login.LoginViewModel
import com.example.kotlin_shoppingcart.views.utils.Utils
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_signup.*

class SignupFragment : BaseFragment() {

    private var mViewModel: SignupViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_signup,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        buttonRegister.setOnClickListener{validateData()}
    }

    fun init() {
        mViewModel = ViewModelProvider(this).get(SignupViewModel::class.java)
        attachObservers();
    }

    /*
    * Attaching observers to the views*/
    private fun attachObservers() {
        mViewModel?.mSignupResponse?.observe(this, Observer {
            it?.let {
                if (it.status.equals("1")){
                    Utils.showSnackBar(context, it.message)
                    activity?.onBackPressed()
                }else{
                    Utils.showSnackBar(context, it.message)
                }
            }
        })

        mViewModel?.mIsLoading?.observe(this, Observer {
            if (it){
                showDialoge()
            }else{
                hideDialoge()
            }
        })
    }

    private fun validateData(){
        val name: String = edittext_name.text.toString().trim()
        val email: String = edittext_emails.text.toString().trim()
        val password: String = edittext_pass.text.toString().trim()
        val confirmPassword: String = edittext_confirmpass.text.toString().trim()
        when {
            !Utils.isNetworkAvailable(context) -> Utils.showSnackBar(
                context,
                getString(R.string.no_internet_message)
            )
            name.isEmpty() -> Utils.showSnackBar(context, "Please enter your name")
            name.length<2 -> Utils.showSnackBar(context, "Name should be atleast two characters")
            email.isEmpty() -> Utils.showSnackBar(context, "Please enter your email")
            (!Utils.validEmail(email)) -> {
                Utils.showSnackBar(context, "Please enter valid email")
            }
            password.isEmpty() -> Utils.showSnackBar(context, "Please enter your password")
            password.length<=4 -> Utils.showSnackBar(context, "Password should be atleast 5 characters")
            confirmPassword.isEmpty() -> Utils.showSnackBar(context, "Please enter your confirm password")
            !password.equals(confirmPassword) -> Utils.showSnackBar(context, "Password and confirm password donot match")

            else -> mViewModel?.signupUserWithCredintials(SignupRequests(password,name,email))
        }
    }
}