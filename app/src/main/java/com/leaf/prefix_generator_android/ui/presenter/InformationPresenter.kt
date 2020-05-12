package com.leaf.prefix_generator_android.ui.presenter

import android.content.Context
import com.leaf.prefix_generator_android.ui.contract.InformationContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class InformationPresenter(override val view: InformationContract.View) : InformationContract.Presenter {

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()
    override lateinit var context: Context

    override fun addDisposable(disposable: Disposable) { compositeDisposable.add(disposable) }

    override fun disposeDisposable() = compositeDisposable.dispose()
}