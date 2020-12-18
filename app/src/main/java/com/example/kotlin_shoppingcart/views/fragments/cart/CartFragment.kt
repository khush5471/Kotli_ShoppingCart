package com.example.kotlin_shoppingcart.views.fragments.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.adapters.UserCartAdapter
import com.example.kotlin_shoppingcart.views.apihelper.model.shopcart.DataItem
import com.example.kotlin_shoppingcart.views.apihelper.model.usercart.CartDataResponse
import com.example.kotlin_shoppingcart.views.fragments.BaseFragment
import com.example.kotlin_shoppingcart.views.fragments.shop.CartClickInterface
import com.example.kotlin_shoppingcart.views.utils.Constants
import com.example.kotlin_shoppingcart.views.utils.Utils
import kotlinx.android.synthetic.main.fragment_shop.*

class CartFragment : BaseFragment(), CartClickInterface {

    var mUserCartAdapter: UserCartAdapter? = null
    var mLayoutManager: LinearLayoutManager? = null

    var mCartList = ArrayList<CartDataResponse>()
    var mViewModel: CartViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mUserCartAdapter = context?.let { UserCartAdapter(it, mCartList, this) }
        mLayoutManager = LinearLayoutManager(context)
        recyclerCart.layoutManager = mLayoutManager
        recyclerCart.adapter = mUserCartAdapter

        mViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
//        mViewModel?.getBrandList()
        context?.let {
            Utils.readDataFromPreference(it,Constants.CURRENT_LOGGINED_USER)?.let {
                mViewModel?.getUserCart(it)

            }
        }
        attachObserver()


    }

    override fun getClickedItem(item: DataItem) {
        TODO("Not yet implemented")
    }

    fun attachObserver(){
        mViewModel?.mGetUserCartList?.observe(this, Observer {
            Log.e("eee","eee")
            it?.let {
                mCartList.clear()
                mCartList.addAll(it)
//                it?.let {
//
//                    for (item in it) {
//                        item?.let {
//                            mCartList.add(it)
//                        }
//
//                    }
//
//                }
                mUserCartAdapter?.notifyDataSetChanged()
            }
        })
    }
}