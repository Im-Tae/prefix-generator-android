package com.leaf.prefix_generator_android.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.leaf.prefix_generator_android.R
import com.leaf.prefix_generator_android.base.BaseActivity
import com.leaf.prefix_generator_android.databinding.ActivityMainBinding
import com.leaf.prefix_generator_android.ui.contract.MainContract
import com.leaf.prefix_generator_android.ui.presenter.MainPresenter
import com.leaf.prefix_generator_android.utils.KeyboardUtil
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity(), MainContract.View {

    override lateinit var presenter: MainContract.Presenter
    override lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.main = this

        presenter = MainPresenter(this)

        init()
    }

    override fun init() = showExample()

    override fun showExample() {

        presenter.addDisposable(
            Observable.interval(0, 5000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { presenter.getExampleName() }
                .subscribe {
                    example_location.text = it[0]
                    example_name.text = it[1]
                    example_result_name.text = it[2]
                    Log.d("test", "${it[0]} ${it[1]} ${it[2]}")
                }
        )
    }

    override fun onBackPressed() {
        presenter.disposeDisposable()
        super.onBackPressed()
    }

    override fun showKeyboard() = KeyboardUtil.showKeyboard(this.currentFocus, this)

    override fun hideKeyboard() = KeyboardUtil.showKeyboard(this.currentFocus, this)

    override fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    override fun getContext(): Context = this

    override fun buttonOnClick() {
        presenter.addDisposable(
            Observable.just(presenter.getPrefixName(location.text.toString(), name.text.toString()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { result_name.text = it }
        )
    }

    override fun clipBoardOnClick() = presenter.clipBoardText(result_name)
}
