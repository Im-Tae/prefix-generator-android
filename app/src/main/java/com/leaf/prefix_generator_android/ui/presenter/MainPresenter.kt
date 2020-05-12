package com.leaf.prefix_generator_android.ui.presenter

import com.leaf.prefix_generator_android.ui.contract.MainContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MainPresenter(override val view: MainContract.View) : MainContract.Presenter {

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getName() {

    }

    override fun getExampleName(locations : Array<String>, names: Array<String>): ArrayList<String> {

        val exampleNameArrayList = arrayListOf<String>()

        exampleNameArrayList.add(locations[(locations.indices).random()])
        exampleNameArrayList.add(names[(names.indices).random()])

        return exampleNameArrayList
    }

    override fun addDisposable(disposable: Disposable) { compositeDisposable.add(disposable) }

    override fun disposeDisposable() = compositeDisposable.dispose()
}