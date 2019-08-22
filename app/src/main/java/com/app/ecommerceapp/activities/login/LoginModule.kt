package com.app.ecommerceapp.activities.login

import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    fun loginActivityPresenter(): LoginMVP.Presenter{
        return LoginPresenter()
    }
}