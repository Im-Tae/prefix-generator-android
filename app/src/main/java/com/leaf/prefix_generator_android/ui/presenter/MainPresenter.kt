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

        var prefixName = ""

        if ((1..10).random() > 8) { // 일반 장소 처리

            var randomLocation1 = locations1[(locations1.indices).random()]

            val randomJob1 = jobs1[(jobs1.indices).random()]
            val randomJob2 = jobs2[(jobs2.indices).random()]

            // 특별한 문장 정의
            if (randomLocation1 == "시내버스") {
                if ((1..10).random() > 3) {
                    randomLocation1 = "${(1..999).random()} 번 $randomLocation1"
                }

            } else if ("학교" in randomLocation1) {
                if ((1..10).random() > 5) {
                    randomLocation1 = "$randomLocation1 ${(1..3).random()} 학년 ${(1..9).random()} 반"
                }
            }

            prefixName = when {
                (1..10).random() <= 4 -> {
                    generateSpecificCase(1, arrayListOf(location, randomLocation1, randomJob1, name))
                }
                else -> {
                    generateSpecificCase(2, arrayListOf(location, randomLocation1, randomJob2, name))
                }
            }

        } else { // 특별한 위치 변수 정의 || 여기서 문제가 발생한다면 특별한 위치 변수를 제대로 정의하지 않은것.

            val randomLocation2 = locations2[(locations2.indices).random()]

            prefixName = if (specificLocationsLocations.contains(randomLocation2)) {
                generateSpecificCase(2, arrayListOf(location, randomLocation2, specificLocationsJobs[specificLocationsLocations.indexOf(randomLocation2)], name))
            } else {
                generateSpecificCase(3, arrayListOf(location, randomLocation2, specificLocationsLocations[specificLocationsLocations.indexOf(randomLocation2)], name))
            }
        }

        return prefixName.replace(" ", "")
    }

    override fun getExampleName(): ArrayList<String> {

        val exampleNameArrayList = arrayListOf<String>()

        val randomExampleLocation = exampleLocations[(exampleLocations.indices).random()]
        val randomExampleName = exampleNames[(exampleNames.indices).random()]
        val randomExamplePrefixName = getPrefixName(randomExampleLocation, randomExampleName)

        exampleNameArrayList.add(randomExampleLocation)
        exampleNameArrayList.add(randomExampleName)
        exampleNameArrayList.add(randomExamplePrefixName)

        return exampleNameArrayList
    }

    private fun generateSpecificCase(caseCode : Int, info : ArrayList<String>): String {

        when(caseCode) {
            1 -> {
                val randomObject = objects[(objects.indices).random()]

                if (info[1] == "산채비빔밥먹는스님앞에서")
                    return "${info[1]} $randomObject 먹는 ${info[3]}"
                if (info[2] == "교제사실을들킨") // 이 부분 조사 추가 해야됨.
                    return "${info[0]} ${info[1]} $randomObject 와 ${info[2]} ${info[3]}"

                return "${info[0]} ${info[1]} $randomObject ${info[2]} ${info[3]}"
            }

            2 -> return "${info[0]} ${info[1]} ${info[2]} ${info[3]}"

            3 -> {
                return if (info[0] == "" || (0..1).random() == 0) {
                    "${info[1]} ${info[2]} ${info[3]}"
                } else {
                    if ((0..1).random() == 0) {
                        "${info[1]} ${info[2]} ${info[0]} 의아들 ${info[3]}"
                    } else {
                        "${info[1]} ${info[2]} ${info[0]} 의딸 ${info[3]}"
                    }
                }
            }
        }

        return "${info[0]} ${info[1]} ${info[2]} ${info[3]}"
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