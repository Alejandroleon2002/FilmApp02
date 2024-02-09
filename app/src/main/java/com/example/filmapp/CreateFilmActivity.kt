package com.example.libraryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.example.libraryapp.database.AppDatabase
import com.example.libraryapp.databinding.ActivityCreateFilmBinding
import com.example.libraryapp.model.Film

class CreateFilmActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateFilmBinding

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .allowMainThreadQueries().build()

        binding.saveButton.setOnClickListener{
            val cod = binding.codEditText.text.toString()
            val title = binding.titleEditText.text.toString()
            val director = binding.authorEditText.text.toString()
            val description = binding.descriptionEditText.text.toString()

            val film = Film(
                codfilm = cod,
                title = title,
                director = director,
                description = description
            )

            db
                .FilmDao()
                .save(film)


            finish()
        }
    }
}