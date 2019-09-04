package com.app.ecommerceapp.activities.home

import com.app.ecommerceapp.root.BasePresenter
import com.app.ecommerceapp.root.BaseView

interface HomeMVP {
    interface View: BaseView<Presenter>{

    }

    interface Presenter: BasePresenter<View>{

    }
}