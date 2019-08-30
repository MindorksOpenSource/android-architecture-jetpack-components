package com.mindorks.framework.jetpack.data.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.mindorks.framework.jetpack.data.database.repository.options.Option
import com.mindorks.framework.jetpack.data.database.repository.options.OptionsDao
import com.mindorks.framework.jetpack.data.database.repository.questions.Question
import com.mindorks.framework.jetpack.data.database.repository.questions.QuestionsDao

/**
 * Created by jyotidubey on 03/01/18.
 */
@Database(entities = [(Question::class), (Option::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun optionsDao(): OptionsDao

    abstract fun questionsDao(): QuestionsDao

}