package test.coding.parkee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import test.coding.parkee.adapter.viewholder.PopularViewHolder
import test.coding.parkee.databinding.ListItemPopularBinding
import test.coding.parkee.model.Movie

class PopularAdapter : RecyclerView.Adapter<PopularViewHolder>() {

    private var listMovie = listOf<Movie>()
    private var clickListener: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        val binding = ListItemPopularBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return PopularViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
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