package com.mindorks.framework.jetpack.ui.question

import android.os.Bundle
import android.os.Handler
import android.os.Looper.getMainLooper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mindorks.framework.jetpack.R
import com.mindorks.framework.jetpack.databinding.FragmentQuestionBinding


/**
 * Created by jyotidubey on 2019-03-04.
 */
class QuestionsFragment : Fragment() {
    private lateinit var viewModel: QuestionViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentQuestionBinding>(
            inflater, R.layout.fragment_question, container, false
        )
        viewModel = QuestionViewModel(this.activity?.applicationContext)

        binding.setLifecycleOwner(this)
        binding.data = viewModel
        setItemRemoveListener(binding)
        viewModel.questions?.observe(viewLifecycleOwner, Observer {

        })
        return binding.root
    }

    private fun setItemRemoveListener(binding: FragmentQuestionBinding) {
        binding.cardsContainer.addItemRemoveListener { count ->
            if (count === 0) {
                // reload the contents again after 1 sec delay
                Handler(getMainLooper()).postDelayed({
                    //Reload once all the cards are removed
                    viewModel.loadQuestions()
                }, 800)
            } else {
                viewModel.removeQuestion()
            }
        }
    }
}