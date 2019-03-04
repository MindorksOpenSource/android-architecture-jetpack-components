package com.mindorks.framework.jetpack.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mindorks.framework.jetpack.R
import com.mindorks.framework.jetpack.databinding.FragmentAboutBinding

/**
 * Created by jyotidubey on 2019-03-02.
 */
class AboutFragment : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentAboutBinding>(
            inflater, R.layout.fragment_about, container, false)
        return binding.root
    }
}