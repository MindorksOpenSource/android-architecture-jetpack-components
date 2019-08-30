package com.mindorks.framework.jetpack.ui.question

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mindorks.framework.jetpack.data.AppDataManager
import com.mindorks.framework.jetpack.data.database.repository.questions.Question
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by jyotidubey on 2019-03-05.
 */

class QuestionViewModel(context: Context?) : ViewModel() {

    private val dataManager = AppDataManager.getInstance(context)
    companion object {
        const val NO_ACTION = -1
        const val ACTION_ADD_ALL = 0
    }
    var questions: MutableLiveData<List<Question>>? = MutableLiveData()
    var action = NO_ACTION


    init {
        loadQuestions()
    }

    fun loadQuestions() {
        action = ACTION_ADD_ALL
         dataManager.getQuestionCardData()
             .subscribe {questionCards-> questions?.value = questionCards }

    }

    fun removeQuestion(){
        val cardDeck = questions?.value as MutableList<Question>
        cardDeck.removeAt(0)
        questions?.value = cardDeck
    }



}