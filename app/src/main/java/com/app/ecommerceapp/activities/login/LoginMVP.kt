package com.app.ecommerceapp.activities.login

import com.app.ecommerceapp.root.BasePresenter
import com.app.ecommerceapp.root.BaseView

interface LoginMVP {

    interface View: BaseView<Presenter>{

    }

    interface Presenter: BasePresenter<View>{

    }
}