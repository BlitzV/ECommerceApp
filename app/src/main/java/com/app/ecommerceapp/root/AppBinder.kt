package com.app.ecommerceapp.root

import com.app.ecommerceapp.activities.login.LoginActivity
import com.app.ecommerceapp.activities.login.LoginModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppBinder {

    @ContributesAndroidInjector(modules = [LoginModule::class])
    internal abstract fun contributeLoginActivity(): LoginActivity
}