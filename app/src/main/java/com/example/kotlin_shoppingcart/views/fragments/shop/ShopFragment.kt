package com.example.kotlin_shoppingcart.views.fragments.shop

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_shoppingcart.R
import com.example.kotlin_shoppingcart.views.adapters.BrandAdapter
import com.example.kotlin_shoppingcart.views.adapters.ProductsAdapter
import com.example.kotlin_shoppingcart.views.fragments.BaseFragment
import com.example.kotlin_shoppingcart.views.models.shopmodel.BrandName
import com.example.kotlin_shoppingcart.views.models.shopmodel.BrandProductItems
import kotlinx.android.synthetic.main.fragment_shop.*

class ShopFragment : BaseFragment() ,SelectBrand{

    var mAdapterBrands: BrandAdapter? = null
    var mLayoutManager: LinearLayoutManager? = null

    var mAdapterProducts: ProductsAdapter? = null
    var mGridLayoutManager: GridLayoutManager? = null


    var mViewModel:ShopViewModel?=null
    var mBrandList=ArrayList<BrandName>()
    var mBrandProductList=ArrayList<BrandProductItems>()
    var mSelectedBrandName=""

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

            mAdapterBrands = BrandAdapter(it,mBrandList,this)
            mLayoutManager = LinearLayoutManager(it,LinearLayoutManager.HORIZONTAL,false)
            recyclerBrands.layoutManager = mLayoutManager
            recyclerBrands.adapter = mAdapterBrands


            mAdapterProducts= ProductsAdapter(it,mBrandProductList)
            mGridLayoutManager= GridLayoutManager(it,2,GridLayoutManager.VERTICAL,false)
            recyclerBrandProducts.layoutManager = mGridLayoutManager
            recyclerBrandProducts.adapter = mAdapterProducts

            init()
            attachObserver()

        }

    }

    fun init(){

        mViewModel=ViewModelProvider(this).get(ShopViewModel::class.java)
        mViewModel?.getBrandList()
    }


    fun attachObserver(){
        mViewModel?.mBrandList?.observe(this, Observer {
            it?.let {
               mBrandList.addAll(it)
                mBrandList.get(0).isSelected=true
                Log.e("size ","is "+it.size)

                mAdapterBrands?.notifyDataSetChanged()

                mViewModel?.getBrandProducts(mBrandList.get(0).brandName)
            }
        })

        mViewModel?.mBrandProductList?.observe(this, Observer {
            it?.let {

                mBrandProductList.clear()
                mBrandProductList.addAll(it)
                mAdapterProducts?.notifyDataSetChanged()
            }
        })
    }

    override fun selectBrand(brand: BrandName) {
        mViewModel?.getBrandProducts(brand.brandName)

    }
}