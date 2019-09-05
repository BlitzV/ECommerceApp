package com.app.ecommerceapp.root

import com.app.ecommerceapp.activities.cart.CartProductModule
import com.app.ecommerceapp.activities.cart.CartProductsActivity
import com.app.ecommerceapp.activities.finalizeshop.FinaliceShoppingModule
import com.app.ecommerceapp.activities.finalizeshop.FinalizeShoppingActivity
import com.app.ecommerceapp.activities.home.HomeActivity
import com.app.ecommerceapp.activities.home.HomeModule
import com.app.ecommerceapp.activities.login.LoginActivity
import com.app.ecommerceapp.activities.login.LoginModule
import com.app.ecommerceapp.activities.products.FetchEProductsActivity
import com.app.ecommerceapp.activities.products.FetchEProductsModule
import com.app.ecommerceapp.activities.signup.SignUpActivity
import com.app.ecommerceapp.activities.signup.SignUpModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppBinder {

    @ContributesAndroidInjector(modules = [LoginModule::class])
    internal abstract fun contributeLoginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [HomeModule::class])
    internal abstract fun contributeHomeActivity(): HomeActivity

    @ContributesAndroidInjector(modules = [CartProductModule::class])
    internal abstract fun contributeCartActivity(): CartProductsActivity

    @ContributesAndroidInjector(modules = [FetchEProductsModule::class])
    internal abstract fun contributeFetchEProducts(): FetchEProductsActivity

    @ContributesAndroidInjector(modules = [FinaliceShoppingModule::class])
    internal abstract fun contributeFinaliceShoppingActivity(): FinalizeShoppingActivity

    @ContributesAndroidInjector(modules = [SignUpModule::class])
    internal abstract fun contributeSignUpActivity(): SignUpActivity
}