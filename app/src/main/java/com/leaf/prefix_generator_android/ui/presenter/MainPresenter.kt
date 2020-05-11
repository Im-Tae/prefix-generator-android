package com.leaf.prefix_generator_android.ui.presenter

import com.leaf.prefix_generator_android.ui.contract.MainContract
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

class MainPresenter(override val view: MainContract.View) : MainContract.Presenter {

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getName() {

    }

    override fun addDisposable(disposable: Disposable) { compositeDisposable.add(disposable) }

    override fun disposeDisposable() = compositeDisposable.dispose()
}