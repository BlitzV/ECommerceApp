package com.app.ecommerceapp.activities.home

import dagger.Module
import dagger.Provides

@Module
class HomeModule {
    @Provides
    fun HomeMVPPresenter(): HomeMVP.Presenter{
        return HomePresenter()
    }
}