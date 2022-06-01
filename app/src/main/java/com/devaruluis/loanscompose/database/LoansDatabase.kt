package com.devaruluis.loanscompose.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devaruluis.loanscompose.database.dao.OccupationDao
import com.devaruluis.loanscompose.database.dao.PersonDao
import com.devaruluis.loanscompose.model.Occupation
import com.devaruluis.loanscompose.model.Person

@Database(entities = [Person::class, Occupation::class], version = 14)
abstract class LoansDatabase : RoomDatabase() {
    abstract fun getPersonDao(): PersonDao
    abstract fun getOccupationDao(): OccupationDao
}