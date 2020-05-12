package com.leaf.prefix_generator_android.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.leaf.prefix_generator_android.R
import com.leaf.prefix_generator_android.base.BaseFragment

class InformationFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_information, container, false)
    }
}
