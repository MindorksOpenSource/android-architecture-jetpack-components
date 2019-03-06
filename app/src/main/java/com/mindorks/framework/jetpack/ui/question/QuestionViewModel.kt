package com.mindorks.framework.jetpack.ui.question

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mindorks.framework.jetpack.data.AppDataManager
import com.mindorks.framework.jetpack.data.QuestionCardData

/**
 * Created by jyotidubey on 2019-03-05.
 */
class QuestionViewModel(context: Context?) : ViewModel() {
    private val dataManager = AppDataManager.getInstance(context)
    val questions: LiveData<List<QuestionCardData>> = dataManager.getQuestionCardData()



}