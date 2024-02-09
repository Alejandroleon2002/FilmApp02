package com.example.libraryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.libraryapp.database.AppDatabase
import com.example.libraryapp.databinding.FilmLayoutBinding
import com.example.libraryapp.model.Film

class FilmAdapter(
    var film: List<Film>,
    val context: Context,
    val db: AppDatabase
) :

    Adapter<FilmAdapter.ItemViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            layoutInflater.inflate(R.layout.film_layout, null)
        )
    }

    override fun getItemCount(): Int {
        return film.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val film = film[position]
        val binding = FilmLayoutBinding.bind(holder.itemView)

        binding.titleTextView.text = film.title


        binding.isbnTextView.text = film.codfilm

        binding.authorTextView.text = film.director

        binding.descriptionTextView.text = film.description

        binding.DeleteBoton.setOnClickListener{
            val deletedRows = db.FilmDao().delete(film.codfilm)

            this.film = db.FilmDao().list()

            notifyDataSetChanged()
            if(deletedRows == 0) {
                Toast.makeText(context, "No se ha eliminado ningún libro", Toast.LENGTH_LONG).show()
            }
        }
    }


}