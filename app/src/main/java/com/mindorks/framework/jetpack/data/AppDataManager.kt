package com.mindorks.framework.jetpack.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.google.gson.GsonBuilder
import com.google.gson.internal.`$Gson$Types`
import com.mindorks.framework.jetpack.data.database.AppDBHelper
import com.mindorks.framework.jetpack.data.database.repository.options.Option
import com.mindorks.framework.jetpack.data.database.repository.questions.Question
import com.mindorks.framework.jetpack.utils.loadJSONFromAsset
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by jyotidubey on 2019-03-05.
 */
const val SEED_DATABASE_OPTIONS = "seed/options.json"
const val SEED_DATABASE_QUESTIONS = "seed/questions.json"

class AppDataManager(private var context: Context?) : DataManager {

    companion object {

        @Volatile private var instance: AppDataManager? = null

        fun getInstance(context: Context?) =
            instance ?: synchronized(this) {
                instance ?: AppDataManager(context).also {
                    instance = it

                }
            }
    }

    private val dbHelper = AppDBHelper.getInstance(context!!)
    private val gson = GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()

    override fun seedOptions(): Observable<Boolean> {
        return isOptionEmpty()
            .filter { it }
            .concatMap {
                val type = `$Gson$Types`.newParameterizedTypeWithOwner(
                    null, List::class.java,
                    Option::class.java
                )
                val optionList = gson
                    .fromJson<List<Option>>(loadJSONFromAsset(context!!, SEED_DATABASE_OPTIONS), type)
                return@concatMap saveOptionList(optionList)
            }
    }

    override fun seedQuestions(): Observable<Boolean> {
        return isQuestionEmpty()
            .filter { it }
            .concatMap {
                val type = `$Gson$Types`.newParameterizedTypeWithOwner(
                    null, List::class.java,
                    Question::class.java
                )
                val questionList = gson
                    .fromJson<List<Question>>(loadJSONFromAsset(context!!, SEED_DATABASE_QUESTIONS), type)
                return@concatMap saveQuestionList(questionList)
            }
    }

    override fun saveOptionList(optionList: List<Option>): Observable<Boolean> {
        return dbHelper.saveOptionList(optionList)
    }

    override fun saveQuestionList(questionList: List<Question>): Observable<Boolean> {
        return dbHelper.saveQuestionList(questionList)
    }

    override fun getAllQuestions() = dbHelper.getAllQuestions()

    override fun getOptionsForQuestionId(questionId: Long?) = dbHelper.getOptionsForQuestionId(questionId)

    override fun isOptionEmpty() = dbHelper.isOptionEmpty()

    override fun isQuestionEmpty() = dbHelper.isQuestionEmpty()


    override fun getQuestionCardData(): Single<List<Question>> {
        return getAllQuestions()!!
            .flatMapObservable {  Observable.fromIterable(it) }
            .map { question: Question ->
                question.options =  getOptionsForQuestionId(question.id)
                question
            }
            .toList()


    }


}