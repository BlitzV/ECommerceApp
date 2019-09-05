package com.app.ecommerceapp.activities.signup

import dagger.Module

@Module
class SignUpModule {

    fun SignUpMVPPResenter(): SignUpMVP.Presenter {
        return SignUpPresenter()
    }
}