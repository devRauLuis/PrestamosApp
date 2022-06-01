package com.devaruluis.loanscompose.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "occupation_table")
data class Occupation
    (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "description")
    val description: String?,
//    @ColumnInfo(name = "created_at")
//    var createdAt: Long? = null,
//    @ColumnInfo(name = "updated_at")
//    var updatedAt: Long? = null
)