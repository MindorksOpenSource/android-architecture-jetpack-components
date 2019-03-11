package com.mindorks.framework.jetpack.ui.question

import android.graphics.Color
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.mindorks.framework.jetpack.R
import com.mindorks.framework.jetpack.data.database.repository.questions.Question
import com.mindorks.placeholderview.annotations.Click
import com.mindorks.placeholderview.annotations.Layout
import com.mindorks.placeholderview.annotations.Resolve
import com.mindorks.placeholderview.annotations.View


/**
 * Created by jyotidubey on 2019-03-11.
 */
@Layout(R.layout.item_question_card)
class QuestionCard(var question: Question) {

    @View(R.id.btn_option_1)
    lateinit var option1: Button

    @View(R.id.btn_option_2)
    lateinit var option2: Button

    @View(R.id.btn_option_3)
    lateinit var option3: Button

    @View(R.id.iv_pic)
    lateinit var image: ImageView

    @View(R.id.tv_question_txt)
    lateinit var questionText: TextView

    @Resolve
    fun onResolved() {
        questionText?.text = question?.questionText
        if (question?.showCorrectOption!!) {
            showCorrectOptions()
        }

        for (i in 0..2) {
            var button: Button? = null
            when (i) {
                0 -> button = option1
                1 -> button = option2
                2 -> button = option3
            }
            button?.text = question?.options?.get(i)?.optionText

            if (question?.imgUrl != null) {
                //image.setImageUrl(question?.imgUrl)
            }
        }
    }

    private fun showCorrectOptions() {
        question?.showCorrectOption = true
        for (i in 0..2) {
            val option = question?.options?.get(i)
            var button: Button? = null
            when (i) {
                0 -> button = option1
                1 -> button = option2
                2 -> button = option3
            }
            if (button != null) {
                if (option?.isCorrect!!) {
                    button.setBackgroundColor(Color.GREEN)
                } else {
                    button.setBackgroundColor(Color.RED)
                }
            }
        }
    }

    @Click(R.id.btn_option_1)
    fun onOption1Click() = showCorrectOptions()

    @Click(R.id.btn_option_2)
    fun onOption2Click() = showCorrectOptions()

    @Click(R.id.btn_option_3)
    fun onOption3Click() = showCorrectOptions()


}