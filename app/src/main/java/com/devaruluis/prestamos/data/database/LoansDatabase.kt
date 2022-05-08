package com.devaruluis.prestamos.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devaruluis.prestamos.data.database.dao.PersonDao
import com.devaruluis.prestamos.model.Person

@Database(entities = [Person::class], version = 6)
abstract class LoansDatabase : RoomDatabase() {

    abstract fun getPersonDao(): PersonDao
}