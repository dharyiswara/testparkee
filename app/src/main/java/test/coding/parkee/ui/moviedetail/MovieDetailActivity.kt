package test.coding.parkee.ui.moviedetail

import android.app.Activity
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import test.coding.parkee.R
import test.coding.parkee.databinding.ActivityMovieDetailBinding
import test.coding.parkee.helper.CommonFunction.getReleaseDate
import test.coding.parkee.model.FavoriteTable
import test.coding.parkee.model.Movie

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun startActivity(activity: Activity, movieId: Int) {
            activity.startActivity(Intent(activity, MovieDetailActivity::class.java).apply {
                val bundle = bundleOf().apply {
                    putInt(MOVIE_ID, movieId)
                }
                putExtras(bundle)
            })
        }

        private const val MOVIE_ID = "movie_id"
    }

    private lateinit var binding: ActivityMovieDetailBinding

    private val viewModel by viewModels<MovieDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeToLiveData()
        setupView()
    }

    private fun setupView() {
        when (resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                binding.ivFavoriteMovie.setColorFilter(ContextCompat.getColor(this, R.color.white))
                binding.ivShareMovie.setColorFilter(ContextCompat.getColor(this, R.color.white))
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                binding.ivFavoriteMovie.setColorFilter(ContextCompat.getColor(this, R.color.black))
                binding.ivShareMovie.setColorFilter(ContextCompat.getColor(this, R.color.black))
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {
                binding.ivFavoriteMovie.setColorFilter(ContextCompat.getColor(this, R.color.black))
                binding.ivShareMovie.setColorFilter(ContextCompat.getColor(this, R.color.black))
            }
        }

        binding.ivShareMovie.setOnClickListener {
            val title = viewModel.getDetailMovieListLiveData().value?.title
            share("Ayo nonton $title bareng-bareng")
        }

        binding.ivFavoriteMovie.setOnClickListener {
            favorite()
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

        viewModel.getDetailMovie(intent.getIntExtra(MOVIE_ID, 0))
    }

    private fun subscribeToLiveData() {
        viewModel.getDetailLoadingLiveData().observe(this) {
            binding.pbDetailMovie.isVisible = it
            binding.clDetailMovie.isVisible = !it
        }
        viewModel.getDetailMovieListLiveData().observe(this) {
            it?.let { movie ->
                setMovieData(movie)
            }
        }
        viewModel.getReviewMovieListLiveData().observe(this) {
            it?.let { review ->
                binding.tvReviewMovie.text = review.content
            }
        }
        viewModel.getCheckFavoriteListLiveData().observe(this) {
            binding.ivFavoriteMovie.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    if (it) {
                        R.drawable.ic_favorite
                    } else {
                        R.drawable.ic_unfavorite
                    }
                )
            )
        }
        viewModel.getClickFavoriteListLiveData().observe(this) {
            Toast.makeText(
                this,
                if (it) {
                    "Added to favorite"
                } else {
                    "Deleted from favorite"
                },
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setMovieData(movie: Movie) {
        binding.run {
            Glide.with(this@MovieDetailActivity)
                .load("https://image.tmdb.org/t/p/original${movie.backdrop}")
                .into(ivBannerMovie)
            tvTitleMovie.text = movie.title
            tvReleaseDateMovie.text = getReleaseDate(movie.releaseDate)
            tvDescriptionMovie.text = movie.overview
        }
        viewModel.getReviewMovie(movie.id)
        viewModel.getSelectedMovie(movie.id)
    }

    private fun share(text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, text)
        }
        val shareIntent = Intent.createChooser(sendIntent, "Share")
        startActivity(shareIntent)
    }

    private fun favorite() {
        val favoriteMovie = viewModel.getCheckFavoriteListLiveData().value ?: false
        val movie = viewModel.getDetailMovieListLiveData().value
        movie?.let {
            if (favoriteMovie) {
                viewModel.deleteFavoriteMovie(it.id)
            } else {
                viewModel.insertFavoriteMovie(
                    FavoriteTable(it.id, it.title, it.releaseDate, it.overview, it.poster)
                )
            }
        }
    }

}