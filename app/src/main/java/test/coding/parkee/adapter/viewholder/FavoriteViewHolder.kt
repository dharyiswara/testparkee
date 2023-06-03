package test.coding.parkee.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import test.coding.parkee.databinding.ListItemFavoriteBinding
import test.coding.parkee.helper.CommonFunction.getReleaseDate
import test.coding.parkee.model.FavoriteTable

class FavoriteViewHolder(
    private val binding: ListItemFavoriteBinding,
    private val clickListener: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(favorite: FavoriteTable) {
        binding.run {
            tvTitleFavorite.text = favorite.title
            tvReleaseDateFavorite.text = getReleaseDate(favorite.releaseDate)
            tvDescriptionFavorite.text = favorite.description
            Glide.with(ivFavoriteMovie.context)
                .load("https://image.tmdb.org/t/p/w500${favorite.poster}")
                .into(ivFavoriteMovie)

            root.setOnClickListener {
                clickListener.invoke(favorite.id)
            }
        }
    }

}