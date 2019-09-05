package com.app.ecommerceapp.activities.signup

import dagger.Module
import dagger.Provides

@Module
class SignUpModule {

    @Provides
    fun SignUpMVPPResenter(): SignUpMVP.Presenter {
        return SignUpPresenter()
    }
}