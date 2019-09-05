package com.app.ecommerceapp.activities.products

import com.app.ecommerceapp.root.BasePresenter
import com.app.ecommerceapp.root.BaseView

interface FetchEProductsMVP {

    interface View: BaseView<Presenter>{

    }

    interface Presenter: BasePresenter<View>{

    }
}