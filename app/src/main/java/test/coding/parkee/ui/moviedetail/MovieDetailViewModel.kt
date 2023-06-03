package test.coding.parkee.ui.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.coding.parkee.model.FavoriteTable
import test.coding.parkee.model.Movie
import test.coding.parkee.model.Review
import test.coding.parkee.repository.MovieDetailRepository
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository
) : ViewModel() {

    private val detailLoadingLiveData = MutableLiveData<Boolean>()
    private val detailMovieListLiveData = MutableLiveData<Movie?>()
    private val reviewMovieListLiveData = MutableLiveData<Review?>()
    private val checkFavoriteLiveData = MutableLiveData<Boolean>()
    private val clickFavoriteLiveData = MutableLiveData<Boolean>()

    fun getDetailLoadingLiveData(): LiveData<Boolean> = detailLoadingLiveData
    fun getDetailMovieListLiveData(): LiveData<Movie?> = detailMovieListLiveData
    fun getReviewMovieListLiveData(): LiveData<Review?> = reviewMovieListLiveData
    fun getCheckFavoriteListLiveData(): LiveData<Boolean> = checkFavoriteLiveData
    fun getClickFavoriteListLiveData(): LiveData<Boolean> = clickFavoriteLiveData

    fun getDetailMovie(movieId: Int) {
        viewModelScope.launch {
            detailLoadingLiveData.value = true
            detailMovieListLiveData.value = movieDetailRepository.getDetailMovie(movieId)
            detailLoadingLiveData.value = false
        }
    }

    fun getReviewMovie(movieId: Int) {
        viewModelScope.launch {
            val result = movieDetailRepository.getReviewMovie(movieId)
            reviewMovieListLiveData.value = result?.results?.getOrNull(0)
        }
    }

    fun getSelectedMovie(movieId: Int) {
        viewModelScope.launch {
            val result = movieDetailRepository.getFavoriteMovie(movieId)
            checkFavoriteLiveData.value = result != null
        }
    }

    fun insertFavoriteMovie(favoriteTable: FavoriteTable) {
        viewModelScope.launch {
            movieDetailRepository.insertFavoriteMovie(favoriteTable)
            checkFavoriteLiveData.value = true
            clickFavoriteLiveData.value = true
        }
    }

    fun deleteFavoriteMovie(movieId: Int) {
        viewModelScope.launch {
            movieDetailRepository.deleteFavoriteMovie(movieId)
            checkFavoriteLiveData.value = false
            clickFavoriteLiveData.value = false
        }
    }
}