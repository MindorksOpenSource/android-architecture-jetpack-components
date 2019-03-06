package com.mindorks.framework.jetpack.data.database.repository.questions


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by jyotidubey on 04/01/18.
 */
@Dao
interface QuestionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(question: List<Question>)

    @Query("SELECT * FROM questions")
    fun loadAll(): LiveData<List<Question>>
}