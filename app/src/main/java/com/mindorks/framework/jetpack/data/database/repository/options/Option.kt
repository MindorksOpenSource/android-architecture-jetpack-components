package com.mindorks.framework.jetpack.data.database.repository.options


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.mindorks.framework.jetpack.data.database.repository.questions.Question

/**
 * Created by jyotidubey on 03/01/18.
 */
@Entity(
    tableName = "options", foreignKeys = [(ForeignKey(
        entity = Question::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("question_id"),
        onDelete = ForeignKey.CASCADE
    ))]
)
data class Option(

    @PrimaryKey(autoGenerate = true) var id: Long,

    @Expose
    @SerializedName("option_text")
    @ColumnInfo(name = "option_text")
    var optionText: String,

    @Expose
    @SerializedName("question_id")
    @ColumnInfo(name = "question_id")
    var questionId: Long,

    @Expose
    @SerializedName("is_correct")
    @ColumnInfo(name = "is_correct")
    var isCorrect: Boolean,

    @Expose
    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    var createdAt: String?,

    @Expose
    @SerializedName("updated_at")
    @ColumnInfo(name = "updated_at")
    var updatedAt: String?
)


