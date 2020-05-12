package com.leaf.prefix_generator_android.ui.contract

import com.leaf.prefix_generator_android.base.BasePresenter
import com.leaf.prefix_generator_android.base.BaseView

class InformationContract {
    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter<View>
}