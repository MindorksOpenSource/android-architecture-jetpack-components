package com.mindorks.framework.jetpack.ui.opensource

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
import com.mindorks.framework.jetpack.data.model.api.OpenSourceResponse
import com.mindorks.framework.jetpack.databinding.FragmentFeedBinding

/**
 * Created by jyotidubey on 2019-03-02.
 */
class OpenSourceFragment  : Fragment(), OpenSourceViewModel.OpenSourceItemClickHandler{


    val viemModel = OpenSourceViewModel()
    private var adapter : OpenSourceListAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentFeedBinding>(
            inflater, R.layout.fragment_feed, container, false)
        adapter = OpenSourceListAdapter(this)
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

    override fun onOpenSourceItemClicked(repo: OpenSourceResponse.Repo) {
        repo.projectUrl?.let {
            Intent().let {
                it.action = Intent.ACTION_VIEW
                it.addCategory(Intent.CATEGORY_BROWSABLE)
                it.data = Uri.parse(repo.projectUrl)
                activity?.startActivity(it)

            }
        }

    }

}