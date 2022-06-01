package com.devaruluis.loanscompose.database.dao

import androidx.room.*
import com.devaruluis.loanscompose.model.Occupation

@Dao
interface OccupationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(occupation: Occupation): Long

    @Update
    suspend fun update(occupation: Occupation): Int

    @Delete
    suspend fun delete(occupation: Occupation)

    @Query("SELECT * FROM occupation_table WHERE id LIKE :id")
    suspend fun find(id: Long?): Occupation

    @Query("SELECT * FROM occupation_table ORDER BY id")
    fun getAll(): List<Occupation>
}