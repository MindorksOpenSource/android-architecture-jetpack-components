package com.mindorks.framework.jetpack.ui.blog

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.framework.jetpack.R
import com.mindorks.framework.jetpack.data.model.api.BlogResponse
import com.mindorks.framework.jetpack.databinding.FragmentFeedBinding

/**
 * Created by jyotidubey on 2019-03-20.
 */
class BlogFragment : Fragment(), BlogViewModel.BlogItemClickHandler{

    val viemModel = BlogViewModel()

    private var adapter : BlogListAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentFeedBinding>(
            inflater, R.layout.fragment_feed, container, false)
        adapter = BlogListAdapter(this)
        binding.listOpenSource.adapter = adapter
        val layoutManager = LinearLayoutManager(this.activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.listOpenSource.layoutManager =  layoutManager
        viemModel.repoLiveData?.observe(this, Observer {
            adapter?.submitList(it)
        })
        viemModel.progress?.observe(this, Observer {
            adapter?.setNetworkState(it)
        })
        return binding.root
    }

    override fun onBlogItemClicked(blog: BlogResponse.Blog) {
        blog.blogUrl?.let {
            Intent().let {
                it.action = Intent.ACTION_VIEW
                it.addCategory(Intent.CATEGORY_BROWSABLE)
                it.data = Uri.parse(blog.blogUrl)
                activity?.startActivity(it)

            }
        }

    }

}