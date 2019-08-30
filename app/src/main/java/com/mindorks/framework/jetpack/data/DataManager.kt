package com.mindorks.framework.jetpack.data

import com.mindorks.framework.jetpack.data.database.DBHelper
import com.mindorks.framework.jetpack.data.database.repository.questions.Question
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by jyotidubey on 2019-03-05.
 */
interface DataManager : DBHelper {
    fun seedQuestions(): Observable<Boolean>

    fun seedOptions(): Observable<Boolean>

    fun getQuestionCardData(): Single<List<Question>>


}