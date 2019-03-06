package com.mindorks.framework.jetpack.data

import androidx.lifecycle.LiveData
import com.mindorks.framework.jetpack.data.database.DBHelper
import io.reactivex.Observable

/**
 * Created by jyotidubey on 2019-03-05.
 */
interface DataManager : DBHelper {
    fun seedQuestions(): Observable<Boolean>

    fun seedOptions(): Observable<Boolean>

    fun getQuestionCardData() : LiveData<List<QuestionCardData>>


}