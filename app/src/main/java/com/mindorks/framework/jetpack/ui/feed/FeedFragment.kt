package com.mindorks.framework.jetpack.ui.feed

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.mindorks.framework.jetpack.R
import com.mindorks.framework.jetpack.data.model.api.OpenSourceResponse
import com.mindorks.framework.jetpack.databinding.FragmentFeedBinding
import com.mindorks.framework.jetpack.ui.feed.blog.BlogFragment
import com.mindorks.framework.jetpack.ui.feed.opensource.OpenSourceFragment

/**
 * Created by jyotidubey on 2019-03-22.
 */
class FeedFragment : Fragment(), FeedViewModel.OpenSourceItemClickHandler {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentFeedBinding>(inflater, R.layout.fragment_feed, container, false)
        binding.feeds.adapter = FeedPagerAdapter(childFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.feeds)
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

    inner class FeedPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> BlogFragment()
                1 -> OpenSourceFragment()
                else -> throw IndexOutOfBoundsException("No such page")
            }
        }

        override fun getCount(): Int = 2

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Blogs"
                1 -> "Open Source"
                else -> throw IndexOutOfBoundsException("No such page")
            }
        }
    }

}