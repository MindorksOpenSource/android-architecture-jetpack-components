package com.mindorks.framework.jetpack.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.mindorks.framework.jetpack.data.model.api.OpenSourceResponse
import com.mindorks.framework.jetpack.data.remote.ApiHeader
import com.mindorks.framework.jetpack.data.remote.AppApiHelper
import com.mindorks.framework.jetpack.utils.NetworkState
import java.util.concurrent.TimeUnit

/**
 * Created by jyotidubey on 2019-03-07.
 */
class OpenSouceDataSource : PageKeyedDataSource<Long, OpenSourceResponse.Repo>() {


    private val header = ApiHeader(
        ApiHeader.PublicApiHeader("ABCXYZ123TEST"),
        ApiHeader.ProtectedApiHeader("ABCXYZ123TEST", 1, "demo.token.from.mock.server")
    )
    private val apiHelper = AppApiHelper(header)
    private var networkState: MutableLiveData<NetworkState>? = MutableLiveData()

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, OpenSourceResponse.Repo>
    ) {
        apiHelper.getOpenSourceApiCall(1)
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

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, OpenSourceResponse.Repo>) {
        apiHelper.getOpenSourceApiCall(params.key.toInt())
            .doOnSubscribe {  networkState?.postValue(NetworkState(NetworkState.Status.RUNNING, "Loading")) }
            .delay(5,TimeUnit.SECONDS)
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

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, OpenSourceResponse.Repo>) {
    }

    fun getNetworkState() = networkState


}