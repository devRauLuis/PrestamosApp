package com.devaruluis.prestamos.data.database.dao

import androidx.room.*
import com.devaruluis.prestamos.model.Person

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Query("SELECT * FROM people_table WHERE id LIKE :id")
    suspend fun find(id: Int): Person

    @Query("SELECT * FROM people_table ORDER BY id")
    suspend fun getAll(): List<Person>
}