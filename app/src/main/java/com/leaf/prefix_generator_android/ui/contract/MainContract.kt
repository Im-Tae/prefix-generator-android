package com.leaf.prefix_generator_android.ui.contract

import android.widget.TextView
import com.leaf.prefix_generator_android.base.BasePresenter
import com.leaf.prefix_generator_android.base.BaseView

class MainContract {

    interface View : BaseView<Presenter> {
        fun showExample()

        fun buttonOnClick()

        fun clipBoardOnClick()

        fun showFragment()
    }

    interface Presenter : BasePresenter<View> {
        fun getPrefixName(location: String = "", name: String = ""): String

        fun getExampleName() : ArrayList<String>

        fun clipBoardText(text : TextView)
    }
}