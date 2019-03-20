package com.mindorks.framework.jetpack.data.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.mindorks.framework.jetpack.data.model.api.BlogResponse

/**
 * Created by jyotidubey on 2019-03-20.
 */
class BlogDSFactory: DataSource.Factory<Long, BlogResponse.Blog>(){
    val dataSource : MutableLiveData<BlogDataSource> = MutableLiveData()

    override fun create(): DataSource<Long,BlogResponse.Blog> {
        var blogDataSource = BlogDataSource()
        dataSource.postValue(blogDataSource)
        return blogDataSource
    }

}