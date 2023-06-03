package test.coding.parkee.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import test.coding.parkee.databinding.ListItemMovieBinding
import test.coding.parkee.helper.CommonFunction.getReleaseDate
import test.coding.parkee.model.Movie

class MovieViewHolder(
    private val binding: ListItemMovieBinding,
    private val clickListener: (Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.run {
            Glide.with(ivMovie.context)
                .load("https://image.tmdb.org/t/p/w500${movie.poster}")
                .into(ivMovie)
            tvMovie.text = movie.title
            tvReleaseDate.text = getReleaseDate(movie.releaseDate)
            root.setOnClickListener {
                clickListener.invoke(movie.id)
            }
        }
    }
}