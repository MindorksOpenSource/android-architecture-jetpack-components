package com.mindorks.framework.jetpack.ui.blog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.jetpack.data.model.api.BlogResponse
import com.mindorks.framework.jetpack.databinding.ItemBlogViewBinding
import com.mindorks.framework.jetpack.databinding.ItemNetworkBinding
import com.mindorks.framework.jetpack.utils.NetworkState

/**
 * Created by jyotidubey on 2019-03-20.
 */

private const val TYPE_PROGRESS = 0
private const val TYPE_ITEM = 1

class BlogListAdapter(val handler: BlogViewModel.BlogItemClickHandler) :
    PagedListAdapter<BlogResponse.Blog, RecyclerView.ViewHolder>(BlogResponse.Blog.DIFF_CALLBACK) {

    private var networkState: NetworkState? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is BlogViewHolder) {
            holder.bindTo(getItem(position))
        } else {
            (holder as NetworkStateItemViewHolder).bindTo(networkState)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            val itemBinding = ItemBlogViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            BlogViewHolder(itemBinding, handler)
        } else {
            val headerBinding = ItemNetworkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            NetworkStateItemViewHolder(headerBinding)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            TYPE_PROGRESS
        } else {
            TYPE_ITEM
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState !== NetworkState.LOADED
    }

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val previousExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val newExtraRow = hasExtraRow()
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(itemCount)
            } else {
                notifyItemInserted(itemCount)
            }
        } else if (newExtraRow && previousState !== newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }
    }

    class BlogViewHolder(
        private val binding: ItemBlogViewBinding,
        private val handler: BlogViewModel.BlogItemClickHandler
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(blog: BlogResponse.Blog?) {
            binding.data = blog
            binding.handler = handler
        }
    }

    inner class NetworkStateItemViewHolder(private val binding: ItemNetworkBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(networkState: NetworkState?) {
            binding.progress = networkState
        }
    }
}





