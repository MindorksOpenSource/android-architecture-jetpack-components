package com.mindorks.framework.jetpack.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mindorks.framework.jetpack.data.model.api.OpenSourceResponse

/**
 * Created by jyotidubey on 2019-03-07.
 */
class OpenSourceDSFactory: DataSource.Factory<Long,OpenSourceResponse.Repo>(){

    val dataSource : MutableLiveData<OpenSourceDataSource> = MutableLiveData()

    override fun create(): DataSource<Long, OpenSourceResponse.Repo> {
        var openSourceDataSource = OpenSourceDataSource()
        dataSource.postValue(openSourceDataSource)
        return openSourceDataSource
    }

}