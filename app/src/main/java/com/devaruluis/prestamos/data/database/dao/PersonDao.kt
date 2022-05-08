package com.devaruluis.prestamos.data.database.dao

import androidx.room.*
import com.devaruluis.prestamos.model.Person

@Dao
abstract class PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(person: Person): Long

    suspend fun insertWithTimestamp(person: Person) =
        insert(person.apply {
            createdAt = System.currentTimeMillis()
            updatedAt = System.currentTimeMillis()
        })


    @Update
    abstract suspend fun update(person: Person): Int

    suspend fun updateWithTimestamp(person: Person) {
        update(person.apply {
            updatedAt = System.currentTimeMillis()
        })
    }

    @Delete
    abstract suspend fun delete(person: Person)

    @Query("SELECT * FROM people_table WHERE id LIKE :id")
    abstract suspend fun find(id: Long): Person

    @Query("SELECT * FROM people_table ORDER BY id")
    abstract fun getAll(): List<Person>
}