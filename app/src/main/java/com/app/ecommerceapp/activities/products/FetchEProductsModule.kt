package com.app.ecommerceapp.activities.products

import dagger.Module
import dagger.Provides

@Module
class FetchEProductsModule {

    @Provides
    fun FetchEProductsMVPPresenter(): FetchEProductsMVP.Presenter{
        return FetchEProductsPresenter()
    }
}