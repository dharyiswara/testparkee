package test.coding.parkee.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import test.coding.parkee.adapter.viewholder.FavoriteViewHolder
import test.coding.parkee.databinding.ListItemFavoriteBinding
import test.coding.parkee.model.FavoriteTable

class FavoriteAdapter : RecyclerView.Adapter<FavoriteViewHolder>() {

    private var listFavorite = listOf<FavoriteTable>()
    private var clickListener: (Int) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val binding = ListItemFavoriteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return FavoriteViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bind(listFavorite[position])
    }

    override fun getItemCount(): Int = listFavorite.size

    fun updateListFavorite(list: List<FavoriteTable>) {
        listFavorite = list
        notifyDataSetChanged()
    }

    fun setOnClickListener(listener: (Int) -> Unit) {
        clickListener = listener
    }
}