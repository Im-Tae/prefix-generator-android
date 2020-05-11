package com.leaf.prefix_generator_android.ui.contract

import com.leaf.prefix_generator_android.base.BasePresenter
import com.leaf.prefix_generator_android.base.BaseView

class MainContract {

    interface View : BaseView<Presenter> {
        fun showExample()
    }

    interface Presenter : BasePresenter<View> {
        fun getName()
    }
}