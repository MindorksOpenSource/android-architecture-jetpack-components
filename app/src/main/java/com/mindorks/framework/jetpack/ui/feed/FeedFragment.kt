package com.mindorks.framework.jetpack.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mindorks.framework.jetpack.R
import com.mindorks.framework.jetpack.databinding.FragmentFeedBinding

/**
 * Created by jyotidubey on 2019-03-02.
 */
class FeedFragment  : Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentFeedBinding>(
            inflater, R.layout.fragment_feed, container, false)
        return binding.root
    }
}