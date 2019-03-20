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
    companion object {
        const val NO_ACTION = -1
        const val ACTION_ADD_ALL = 0
    }
    var questions: LiveData<List<Question>>? = null
    var action = NO_ACTION


    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        action = ACTION_ADD_ALL
        questions = dataManager.getQuestionCardData()
    }




}