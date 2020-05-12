package com.leaf.prefix_generator_android.base

import android.content.Context
import androidx.databinding.ViewDataBinding

interface BaseView<T> {

    val presenter : T
    val binding : ViewDataBinding

    fun getContext() : Context

    fun hideKeyboard()

    fun showKeyboard()

    fun showToast(message: String)

    fun init()
}