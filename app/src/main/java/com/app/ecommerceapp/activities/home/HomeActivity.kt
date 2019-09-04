package com.app.ecommerceapp.activities.home

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.app.ecommerceapp.R
import com.app.ecommerceapp.activities.products.FetchEProductsActivity
import com.app.ecommerceapp.helpers.Constants.Companion.BRAND_SELECTED
import javax.inject.Inject

class HomeActivity : AppCompatActivity(), HomeMVP.View {


    @Inject
    lateinit var presenter: HomeMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        brandsListView.setOnItemClickListener { adapterView, view, i, l ->
//
//
//            val tappedBrand = brandsList.get(i)
//            val intent = Intent(this@HomeActivity, FetchEProductsActivity::class.java)
//
//            intent.putExtra(BRAND_SELECTED, tappedBrand)
//            startActivity(intent)
//
//
//        }
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun fetchContext(): Context {
        return this
    }
}
