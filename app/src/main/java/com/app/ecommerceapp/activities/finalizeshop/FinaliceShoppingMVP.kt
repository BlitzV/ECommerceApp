package com.app.ecommerceapp.activities.finalizeshop

import com.app.ecommerceapp.root.BasePresenter
import com.app.ecommerceapp.root.BaseView

interface FinaliceShoppingMVP {
    interface View: BaseView<Presenter>{}

    interface Presenter: BasePresenter<View>{}
}