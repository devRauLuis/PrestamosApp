package com.devaruluis.loanscompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people_table")
data class Person
    (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "names")
    val names: String?,
    @ColumnInfo(name = "surnames")
    val surnames: String?,
    @ColumnInfo(name = "occupation")
    val occupationId: Long?,
    @ColumnInfo(name = "income")
    val income: Float?,
//    @ColumnInfo(name = "created_at")
//    var createdAt: Long? = null,
//    @ColumnInfo(name = "updated_at")
//    var updatedAt: Long? = null
)
