package com.leaf.prefix_generator_android.base

import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

interface BasePresenter<T> {

    val view: T
    val compositeDisposable : CompositeDisposable

    fun addDisposable(disposable: Disposable)

    fun clearDisposable()
}