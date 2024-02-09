package com.example.libraryapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.filmapp.database.Converters
import com.example.filmapp.database.dao.CustomerDao
import com.example.filmapp.model.Customer
import com.example.libraryapp.model.Film
import com.example.libraryapp.database.dao.FilmDao
import com.example.libraryapp.database.dao.RentalDao
import com.example.libraryapp.model.Rental

@Database(entities = [Film::class,Customer::class, Rental::class], version = 2)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {
    companion object {
        val DATABASE_NAME = "Film"
    }
    abstract fun FilmDao(): FilmDao
    abstract fun customerDao(): CustomerDao
    abstract fun rentalDao(): RentalDao

}
