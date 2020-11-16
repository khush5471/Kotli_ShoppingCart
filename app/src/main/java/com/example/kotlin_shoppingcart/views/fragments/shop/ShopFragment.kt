package com.example.kotlin_shoppingcart.views.fragments.shop

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.adapters.BrandAdapter
import com.example.kotlin_shoppingcart.views.adapters.ProductsAdapter
import com.example.kotlin_shoppingcart.views.fragments.BaseFragment
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopFragment : BaseFragment() {

    var mAdapterBrands: BrandAdapter? = null
    var mLayoutManager: LinearLayoutManager? = null

    var mAdapterProducts: ProductsAdapter? = null
    var mGridLayoutManager: GridLayoutManager? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_shop,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        context?.let {

            mAdapterBrands = BrandAdapter(it)
            mLayoutManager = LinearLayoutManager(it,LinearLayoutManager.HORIZONTAL,false)
            recyclerBrands.layoutManager = mLayoutManager
            recyclerBrands.adapter = mAdapterBrands


            mAdapterProducts= ProductsAdapter(it)
            mGridLayoutManager= GridLayoutManager(it,2,GridLayoutManager.VERTICAL,false)
            recyclerBrandProducts.layoutManager = mGridLayoutManager
            recyclerBrandProducts.adapter = mAdapterProducts


        }

    }
}