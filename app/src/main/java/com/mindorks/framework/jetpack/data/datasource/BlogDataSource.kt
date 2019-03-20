package com.mindorks.framework.jetpack.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.mindorks.framework.jetpack.BuildConfig
import com.mindorks.framework.jetpack.data.model.api.BlogResponse
import com.mindorks.framework.jetpack.data.remote.ApiHeader
import com.mindorks.framework.jetpack.data.remote.AppApiHelper
import com.mindorks.framework.jetpack.utils.NetworkState

/**
 * Created by jyotidubey on 2019-03-20.
 */
class BlogDataSource : PageKeyedDataSource<Long, BlogResponse.Blog>() {

    private val header = ApiHeader(
        ApiHeader.PublicApiHeader(BuildConfig.API_KEY),
        ApiHeader.ProtectedApiHeader(BuildConfig.API_KEY, 1, BuildConfig.BASE_URL)
    )
    private val apiHelper = AppApiHelper(header)
    private var networkState: MutableLiveData<NetworkState>? = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, BlogResponse.Blog>
    ) {
        apiHelper.getBlogApiCall()
            .doOnSubscribe {  networkState?.postValue(NetworkState(NetworkState.Status.RUNNING, "Loading")) }
            .subscribe({
                if (it.statusCode == "success") {
                    callback.onResult(it.data, null, 2L)
                    networkState?.postValue(NetworkState(NetworkState.Status.SUCCESS, it.message))
                } else {
                    networkState?.postValue(NetworkState(NetworkState.Status.FAILED, it.message))
                }
            }, {
                networkState?.postValue(NetworkState(NetworkState.Status.FAILED, it.message))
            })
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, BlogResponse.Blog>) {
        apiHelper.getBlogApiCall()
            .doOnSubscribe {  networkState?.postValue(NetworkState(NetworkState.Status.RUNNING, "Loading")) }
            .subscribe({
                if (it.statusCode == "success") {
                    var nextPage = if (params.key.toInt()==4) null else params.key.plus(1)
                    if (params.key.toInt()==4){
                        nextPage = null
                        networkState?.postValue(NetworkState(NetworkState.Status.ALL_RESULTS_LOADED, it.message))
                    }else{
                        networkState?.postValue(NetworkState(NetworkState.Status.SUCCESS, it.message))
                    }
                    callback.onResult(it.data, nextPage)

                } else {
                    networkState?.postValue(NetworkState(NetworkState.Status.FAILED, it.message))
                }
            }, {
                networkState?.postValue(NetworkState(NetworkState.Status.FAILED, it.message))
            })
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, BlogResponse.Blog>) {
    }

    fun getNetworkState() = networkState


}