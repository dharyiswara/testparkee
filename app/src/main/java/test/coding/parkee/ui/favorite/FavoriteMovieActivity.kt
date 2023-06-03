package test.coding.parkee.ui.favorite

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import test.coding.parkee.adapter.FavoriteAdapter
import test.coding.parkee.databinding.ActivityFavoriteMovieBinding
import test.coding.parkee.model.FavoriteTable
import test.coding.parkee.ui.moviedetail.MovieDetailActivity

@AndroidEntryPoint
class FavoriteMovieActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun startActivity(activity: Activity) {
            activity.startActivity(Intent(activity, FavoriteMovieActivity::class.java))
        }
    }

    private lateinit var binding: ActivityFavoriteMovieBinding

    private val viewModel by viewModels<FavoriteMovieViewModel>()

    private val favoriteAdapter by lazy { FavoriteAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFavoriteMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeToLiveData()
        setupView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteMovie()
    }

    private fun setupView() {
        binding.rvFavoriteMovie.run {
            layoutManager =
                LinearLayoutManager(this@FavoriteMovieActivity, LinearLayoutManager.VERTICAL, false)
            adapter = favoriteAdapter.apply {
                setOnClickListener {
                    MovieDetailActivity.startActivity(this@FavoriteMovieActivity, it)
                }
            }
        }
        binding.ivBackFavorite.setOnClickListener {
            onBackPressed()
        }
    }

    private fun subscribeToLiveData() {
        viewModel.getLoadingLiveData().observe(this) {
            binding.pbPopularMovie.isVisible = it
            binding.rvFavoriteMovie.isVisible = !it
        }
        viewModel.getFavoriteLiveData().observe(this) {
            setFavoriteData(it)
        }
    }

    private fun setFavoriteData(favoriteList: List<FavoriteTable>) {
        favoriteAdapter.updateListFavorite(favoriteList)
        binding.tvNoFavorite.isVisible = favoriteList.isEmpty()
        binding.rvFavoriteMovie.isVisible = favoriteList.isNotEmpty()
    }

}