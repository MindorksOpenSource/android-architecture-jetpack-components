package com.mindorks.framework.jetpack.data.database.repository.options

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


/**
 * Created by jyotidubey on 03/01/18.
 */
@Dao
interface OptionsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(options: List<Option>)

    @Query("SELECT * FROM options WHERE question_id = :questionId")
    fun loadOptionsByQuestionId(questionId: Long?): List<Option>

    @Query("SELECT * FROM options")
    fun loadAll(): List<Option>
}