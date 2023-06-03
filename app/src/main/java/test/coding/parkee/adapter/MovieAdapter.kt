package test.coding.parkee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import test.coding.parkee.adapter.viewholder.MovieViewHolder
import test.coding.parkee.databinding.ListItemMovieBinding
import test.coding.parkee.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    private var listMovie = listOf<Movie>()
    private var clickListener: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ListItemMovieBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return MovieViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size

    fun updateListMovie(list: List<Movie>) {
        listMovie = list
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: (Int) -> Unit) {
        clickListener = listener
    }
}