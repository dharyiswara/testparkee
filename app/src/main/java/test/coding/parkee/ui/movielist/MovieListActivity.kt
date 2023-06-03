package test.coding.parkee.ui.movielist

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import test.coding.parkee.adapter.MovieAdapter
import test.coding.parkee.adapter.PopularAdapter
import test.coding.parkee.databinding.ActivityMovieListBinding
import test.coding.parkee.helper.MarginItemDecoration
import test.coding.parkee.ui.favorite.FavoriteMovieActivity
import test.coding.parkee.ui.moviedetail.MovieDetailActivity

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding

    private val viewModel by viewModels<MovieListViewModel>()
    private val popularAdapter by lazy { PopularAdapter() }
    private val topRatedAdapter by lazy { MovieAdapter() }
    private val nowPlayingAdapter by lazy { MovieAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        subscribeToLiveData()
        getMovieList()
    }

    private fun subscribeToLiveData() {
        viewModel.getPopularLoadingLiveData().observe(this) {
            binding.pbPopularMovie.isVisible = it
        }
        viewModel.getPopularMovieListLiveData().observe(this) {
            it?.let { movie ->
                popularAdapter.updateListMovie(movie.movieList)
            }
        }
        viewModel.getTopRatedLoadingLiveData().observe(this) {
            binding.pbTopRatedMovie.isVisible = it
        }
        viewModel.getTopRatedMovieListLiveData().observe(this) {
            it?.let { movie ->
                topRatedAdapter.updateListMovie(movie.movieList)
            }
        }
        viewModel.getNowPlayingLoadingLiveData().observe(this) {
            binding.pbNowPlayingMovie.isVisible = it
        }
        viewModel.getNowPlayingRatedMovieListLiveData().observe(this) {
            it?.let { movie ->
                nowPlayingAdapter.updateListMovie(movie.movieList)
            }
        }
    }

    private fun setupView() {
        binding.rvPopularMovie.run {
            layoutManager =
                LinearLayoutManager(this@MovieListActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = popularAdapter.apply {
                setOnClickListener {
                    MovieDetailActivity.startActivity(this@MovieListActivity, it)
                }
            }
            addItemDecoration(MarginItemDecoration(16))
        }
        binding.rvTopRatedMovie.run {
            layoutManager =
                LinearLayoutManager(this@MovieListActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = topRatedAdapter.apply {
                setOnClickListener {
                    MovieDetailActivity.startActivity(this@MovieListActivity, it)
                }
            }
            addItemDecoration(MarginItemDecoration(16))
        }
        binding.rvNowPlayingMovie.run {
            layoutManager =
                LinearLayoutManager(this@MovieListActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = nowPlayingAdapter.apply {
                setOnClickListener {
                    MovieDetailActivity.startActivity(this@MovieListActivity, it)
                }
            }
            addItemDecoration(MarginItemDecoration(16))
        }

        binding.ivFavorite.setOnClickListener {
            FavoriteMovieActivity.startActivity(this)
        }
    }

    private fun getMovieList() {
        viewModel.getPopularMovieList()
        viewModel.getTopRatedMovieList()
        viewModel.getNowPlayingMovieList()
    }
}