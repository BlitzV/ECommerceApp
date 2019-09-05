package com.app.ecommerceapp.activities.finalizeshop

import dagger.Module

@Module
class FinaliceShoppingModule {

    fun FinaliceSHoppingMVPPresenter(): FinaliceShoppingMVP.Presenter{
        return FinalizeShoppingPresenter()
    }
}