package com.mindorks.framework.jetpack.ui.question

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mindorks.framework.jetpack.data.AppDataManager
import com.mindorks.framework.jetpack.data.database.repository.questions.Question

/**
 * Created by jyotidubey on 2019-03-05.
 */
class QuestionViewModel(context: Context?) : ViewModel() {
    private val dataManager = AppDataManager.getInstance(context)
    val questions: LiveData<List<Question>> = dataManager.getQuestionCardData()
}