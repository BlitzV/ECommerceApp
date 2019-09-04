package com.app.ecommerceapp.activities.cart

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.app.ecommerceapp.R
import javax.inject.Inject

class CartProductsActivity : AppCompatActivity(), CartProductMVP.View {

    @Inject
    lateinit var presenter: CartProductMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart_products)
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun fetchContext(): Context {
        return this
    }
}
