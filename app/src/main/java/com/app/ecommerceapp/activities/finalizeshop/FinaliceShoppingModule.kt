package com.app.ecommerceapp.activities.finalizeshop

import dagger.Module
import dagger.Provides

@Module
class FinaliceShoppingModule {

    @Provides
    fun FinaliceSHoppingMVPPresenter(): FinaliceShoppingMVP.Presenter{
        return FinalizeShoppingPresenter()
    }
}