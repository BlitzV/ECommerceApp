package com.app.ecommerceapp.activities.cart

import dagger.Module
import dagger.Provides

@Module
class CartProductModule {
    @Provides
    fun CartProductMVPPresenter(): CartProductMVP.Presenter {
        return CartProductPresenter()
    }
}