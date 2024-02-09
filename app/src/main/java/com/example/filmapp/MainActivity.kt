package com.example.libraryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.filmapp.model.Customer
import com.example.libraryapp.database.AppDatabase
import com.example.libraryapp.databinding.ActivityMainBinding
import com.example.libraryapp.model.Film
import com.example.libraryapp.model.Rental
import java.util.Date

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.actionMenuToolbar)

        db = Room
            .databaseBuilder(
                this,
                AppDatabase::class.java,
                AppDatabase.DATABASE_NAME
            )
            .allowMainThreadQueries().build()

        //createInitialData()
        binding.filmsRecyclerView.layoutManager =
            GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)

        binding.filmsRecyclerView.adapter = FilmAdapter(
            db.FilmDao().list(), this, db
        )

        binding.addFilmButton.setOnClickListener {
            val createFilmIntent = Intent(
                this, CreateFilmActivity::class.java
            )

            startActivity(createFilmIntent)
        }
    }

    fun createInitialData() {
        db.FilmDao().save(
            Film("1", "El padrino", "Crimen/Misterio", "Francis Ford Cappola")
        )
        db.FilmDao().save(
            Film("2", "Scarface", "Crimen/Suspenso", "Brian de Palma")
        )
        db.FilmDao().save(
            Film("3", "Lo imposible", "Suspenso/Aventura", "Yoon Je-kyoon")
        )
        db.FilmDao().save(
            Film("4", "Regreso al futuro", "Ciencia ficción/Comedia", "Robert Zemeckis")
        )


        val customerId = db.customerDao().save(
            Customer(1, "Pedro", "Lopez", "pLopez@gmail.com")
        )

        db.customerDao().save(
            Customer(2, "Lucia", "Gómez", "luciGomez@hotmail.com")
        )

        db.rentalDao().save(
            Rental(1, "1", Date())
        )

        db.rentalDao().save(
            Rental(1, "4", Date())
        )

        db.rentalDao().save(
            Rental(2, "1", Date())
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.showCustomersActivityItem -> {
                val intent = Intent(
                    this, CustomerManagmentActivity::class.java
                )
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        val adapter = binding.filmsRecyclerView.adapter as FilmAdapter

        adapter.film = db.FilmDao().list()

        adapter.notifyDataSetChanged()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.film_context_menu, menu)
    }
}