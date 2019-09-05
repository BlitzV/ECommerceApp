package com.app.ecommerceapp.activities.signup

import com.app.ecommerceapp.root.BasePresenter
import com.app.ecommerceapp.root.BaseView

interface SignUpMVP {

    interface View: BaseView<Presenter>{}

    interface Presenter: BasePresenter<View>{}
}