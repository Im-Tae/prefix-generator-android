package com.leaf.prefix_generator_android.ui.presenter

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.TextView
import com.leaf.prefix_generator_android.R
import com.leaf.prefix_generator_android.ui.contract.MainContract
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class MainPresenter(override val view: MainContract.View) : MainContract.Presenter {

    override val compositeDisposable: CompositeDisposable = CompositeDisposable()
    override val context: Context = view.getContext()

    private var jobs1 = context.resources.getStringArray(R.array.jobs1)
    private var jobs2 = context.resources.getStringArray(R.array.jobs2)
    private var locations1 = context.resources.getStringArray(R.array.locations1)
    private var locations2 = context.resources.getStringArray(R.array.locations2)
    private var specificLocationsLocations = context.resources.getStringArray(R.array.specificLocationsLocations)
    private var specificLocationsJobs = context.resources.getStringArray(R.array.specificLocationsJobs)
    private var objects = context.resources.getStringArray(R.array.objects)
    private var exampleNames = context.resources.getStringArray(R.array.exampleNames)
    private var exampleLocations = context.resources.getStringArray(R.array.exampleLocations)

    override fun getPrefixName(location: String, name: String): String {

        val prefixName = ""

        return prefixName
    }

    override fun getExampleName(): ArrayList<String> {

        val exampleNameArrayList = arrayListOf<String>()

        val randomLocation = exampleLocations[(exampleLocations.indices).random()]
        val randomName = exampleNames[(exampleNames.indices).random()]
        val randomPrefixName = getPrefixName()

        exampleNameArrayList.add(randomLocation)
        exampleNameArrayList.add(randomName)
        exampleNameArrayList.add(randomPrefixName)

        return exampleNameArrayList
    }

    override fun clipBoardText(text : TextView) {

        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("", text.text)

        clipboard.setPrimaryClip(clip)

        if (clip != null && text.text != "생성 버튼을 누르면 여기에 결과가 표시됩니다.") {
            view.showToast("클립보드에 복사 되었습니다.")
        }
    }

    override fun addDisposable(disposable: Disposable) { compositeDisposable.add(disposable) }

    override fun disposeDisposable() = compositeDisposable.dispose()
}