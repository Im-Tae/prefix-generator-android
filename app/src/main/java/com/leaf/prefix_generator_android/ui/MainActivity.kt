package com.leaf.prefix_generator_android.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
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

    private var jobs1 = arrayOf<String>()
    private var jobs2 = arrayOf<String>()
    private var locations1 = arrayOf<String>()
    private var locations2 = arrayOf<String>()
    private var specificLocationsLocations = arrayOf<String>()
    private var specificLocationsJobs = arrayOf<String>()
    private var objects = arrayOf<String>()
    private var exampleNames = arrayOf<String>()
    private var exampleLocations = arrayOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.main = this

        presenter = MainPresenter(this)

        init()
    }

    override fun init() {

        jobs1 = resources.getStringArray(R.array.jobs1)
        jobs2 = resources.getStringArray(R.array.jobs2)
        locations1 = resources.getStringArray(R.array.locations1)
        locations2 = resources.getStringArray(R.array.locations2)
        specificLocationsLocations = resources.getStringArray(R.array.specificLocationsLocations)
        specificLocationsJobs = resources.getStringArray(R.array.specificLocationsJobs)
        objects = resources.getStringArray(R.array.objects)
        exampleNames = resources.getStringArray(R.array.exampleNames)
        exampleLocations = resources.getStringArray(R.array.exampleLocations)

        showExample()
    }

    override fun showExample() {

        presenter.addDisposable(
            Observable.interval(0, 5000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { presenter.getExampleName(exampleLocations, exampleNames) }
                .subscribe {
                    example_location.text = it[0]
                    example_name.text = it[1]
                    Log.d("test", "${it[0]} ${it[1]}")
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
}
