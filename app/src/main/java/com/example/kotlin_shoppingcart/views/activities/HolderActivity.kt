package com.example.kotlin_shoppingcart.views.activities

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.fragments.cart.CartFragment
import com.example.kotlin_shoppingcart.views.fragments.shop.ShopFragment
import com.example.kotlin_shoppingcart.views.fragments.wishlist.WishListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_dashboard.*

class HolderActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setListners()
    }

    //Setting listners on bottom navigation view
    fun setListners(){

        //adding shop fragment on first load of the screen
        replaceFragment(ShopFragment(),false)
        bottomNavigationDashboard.setOnNavigationItemSelectedListener(object :BottomNavigationView.OnNavigationItemSelectedListener{
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                Log.e("clicked","item"+item.toString())
                when(item.itemId){
                    R.id.item_shop->{
                        txtHeading.setText("Shop")
                        replaceFragment(ShopFragment(),false)
                        return true}
                    R.id.item_wishlist->{
                        txtHeading.setText("WishList")
                        replaceFragment(WishListFragment(),false)

                        return true}
                    R.id.item_cart->{
                        txtHeading.setText("Cart")
                        replaceFragment(CartFragment(),false)

                        return true}

                }
                return false            }
        })

    }
}