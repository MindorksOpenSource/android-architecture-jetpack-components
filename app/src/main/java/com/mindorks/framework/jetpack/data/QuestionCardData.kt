package com.mindorks.framework.jetpack.data

import com.mindorks.framework.jetpack.data.database.repository.options.Option
import com.mindorks.framework.jetpack.data.database.repository.questions.Question

/**
 * Created by jyotidubey on 2019-03-06.
 */
data class QuestionCardData(var question: Question,var options:List<Option>?)