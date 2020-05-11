package com.leaf.prefix_generator_android.base

import androidx.databinding.ViewDataBinding

interface BaseView<T> {

    val presenter : T
    val binding : ViewDataBinding

    fun hideKeyboard()

    fun showKeyboard()

    fun showToast(message: String)

    fun init()
}