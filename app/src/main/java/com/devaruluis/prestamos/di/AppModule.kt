package com.devaruluis.prestamos.di

import android.content.Context
import androidx.room.Room
import com.devaruluis.prestamos.data.database.LoansDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val LOANS_DATABASE_NAME = "loans_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        LoansDatabase::class.java, LOANS_DATABASE_NAME
    ).fallbackToDestructiveMigration().build()

    @Provides
    fun providePersonDao(loansDatabase: LoansDatabase) = loansDatabase.getPersonDao()

}