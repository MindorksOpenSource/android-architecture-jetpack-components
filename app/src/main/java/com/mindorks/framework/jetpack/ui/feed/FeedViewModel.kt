package com.mindorks.framework.jetpack.ui.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.mindorks.framework.jetpack.data.datasource.OpenSourceDSFactory
import com.mindorks.framework.jetpack.data.model.api.OpenSourceResponse
import com.mindorks.framework.jetpack.utils.NetworkState
import java.util.concurrent.Executors

/**
 * Created by jyotidubey on 2019-03-07.
 */
class FeedViewModel : ViewModel(){

    var repoLiveData: LiveData<PagedList<OpenSourceResponse.Repo>>? = null
    var progress: LiveData<NetworkState>? = null

    init {
       val executor = Executors.newFixedThreadPool(5)
        val dsFactory =  OpenSourceDSFactory()
        progress =  Transformations.switchMap(dsFactory.dataSource) {
            it.getNetworkState()
        }

        val pagedListConfig = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(5)
            .setPageSize(10).build()
        repoLiveData = LivePagedListBuilder(dsFactory, pagedListConfig)
            .setFetchExecutor(executor)
            .build()
    }

    interface OpenSourceItemClickHandler{
        fun onOpenSourceItemClicked(repo: OpenSourceResponse.Repo)
    }

}