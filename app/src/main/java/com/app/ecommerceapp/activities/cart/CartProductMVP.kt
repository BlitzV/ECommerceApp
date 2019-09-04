package com.app.ecommerceapp.activities.cart

import com.app.ecommerceapp.root.BasePresenter
import com.app.ecommerceapp.root.BaseView

interface CartProductMVP {

    interface View: BaseView<Presenter>{

    }

    interface Presenter: BasePresenter<View>{

    }
}