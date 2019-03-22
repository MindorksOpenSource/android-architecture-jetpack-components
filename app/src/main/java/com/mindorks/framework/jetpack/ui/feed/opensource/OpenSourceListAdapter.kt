package com.mindorks.framework.jetpack.ui.feed.opensource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mindorks.framework.jetpack.data.model.api.OpenSourceResponse
import com.mindorks.framework.jetpack.databinding.ItemNetworkBinding
import com.mindorks.framework.jetpack.databinding.ItemOpenSourceViewBinding
import com.mindorks.framework.jetpack.utils.NetworkState


/**
 * Created by jyotidubey on 2019-03-07.
 */
private const val TYPE_PROGRESS = 0
private const val TYPE_ITEM = 1

class OpenSourceListAdapter(val handler: OpenSourceViewModel.OpenSourceItemClickHandler) :
    PagedListAdapter<OpenSourceResponse.Repo, RecyclerView.ViewHolder>(OpenSourceResponse.Repo.DIFF_CALLBACK) {

    private var networkState: NetworkState? = null

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is OpenSourceViewHolder) {
            holder.bindTo(getItem(position))
        } else {
            (holder as NetworkStateItemViewHolder).bindTo(networkState)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_ITEM) {
            val itemBinding = ItemOpenSourceViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            OpenSourceViewHolder(itemBinding, handler)
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

    class OpenSourceViewHolder(
        private val binding: ItemOpenSourceViewBinding,
        private val handler: OpenSourceViewModel.OpenSourceItemClickHandler
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindTo(repo: OpenSourceResponse.Repo?) {
            binding.data = repo
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