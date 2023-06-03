package test.coding.parkee.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import test.coding.parkee.databinding.ListItemPopularBinding
import test.coding.parkee.model.Movie

class PopularViewHolder(
    private val binding: ListItemPopularBinding,
    private val clickListener: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        Glide.with(binding.ivMovieContent.context)
            .load("https://image.tmdb.org/t/p/w500${movie.backdrop}")
            .into(binding.ivMovieContent)

        binding.root.setOnClickListener {
            clickListener.invoke(movie.id)
        }
    }

}