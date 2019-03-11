package com.mindorks.framework.jetpack.utils

import androidx.databinding.BindingAdapter
import com.mindorks.framework.jetpack.data.database.repository.questions.Question
import com.mindorks.framework.jetpack.ui.question.QuestionCard
import com.mindorks.framework.jetpack.ui.question.QuestionViewModel
import com.mindorks.placeholderview.SwipePlaceHolderView

/**
 * Created by jyotidubey on 2019-03-11.
 */


@BindingAdapter("adapter","action")
fun addQuestionItems(view: SwipePlaceHolderView, questions: List<Question>?, action: Int) {
    questions?.let {
        if(action == QuestionViewModel.ACTION_ADD_ALL)
        view.removeAllViews()
        for (question in questions) {
            if (question?.options != null && question?.options?.size === 3) {
                view.addView(QuestionCard(question))
            }
        }

    }


}
