package com.leaf.prefix_generator_android.ui

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.leaf.prefix_generator_android.R
import com.leaf.prefix_generator_android.base.BaseActivity
import com.leaf.prefix_generator_android.databinding.ActivityMainBinding
import com.leaf.prefix_generator_android.ui.contract.MainContract
import com.leaf.prefix_generator_android.ui.presenter.MainPresenter
import com.leaf.prefix_generator_android.utils.KeyboardUtil

class MainActivity : BaseActivity(), MainContract.View {

    override lateinit var presenter: MainContract.Presenter
    override lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.main = this

        presenter = MainPresenter(this)
    }

    override fun init() {}

    override fun showKeyboard() = KeyboardUtil.showKeyboard(this.currentFocus, this)

    override fun hideKeyboard() = KeyboardUtil.showKeyboard(this.currentFocus, this)

    override fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}
