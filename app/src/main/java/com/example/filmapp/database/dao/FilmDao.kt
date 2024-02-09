package com.example.libraryapp.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.libraryapp.model.Film

@Dao
interface FilmDao {

    @Query("SELECT * FROM film")
    fun list(): List<Film>

    @Query("DELETE FROM film WHERE codfilm=:codfilm")
    fun delete(codfilm: String): Int

    @Query("SELECT * FROM film WHERE author=:director")
    fun listAuthorFilms(director: String): List<Film>

    @Insert
    fun save(book: Film)
}