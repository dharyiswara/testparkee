package test.coding.parkee.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.coding.parkee.model.ListMovie
import test.coding.parkee.repository.MovieListRepository
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieListRepository: MovieListRepository
) : ViewModel() {

    private val popularLoadingLiveData = MutableLiveData<Boolean>()
    private val popularMovieListLiveData = MutableLiveData<ListMovie?>()
    private val topRatedLoadingLiveData = MutableLiveData<Boolean>()
    private val topRatedMovieListLiveData = MutableLiveData<ListMovie?>()
    private val nowPlayingLoadingLiveData = MutableLiveData<Boolean>()
    private val nowPlayingMovieListLiveData = MutableLiveData<ListMovie?>()

    fun getPopularLoadingLiveData(): LiveData<Boolean> = popularLoadingLiveData
    fun getPopularMovieListLiveData(): LiveData<ListMovie?> = popularMovieListLiveData

    fun getTopRatedLoadingLiveData(): LiveData<Boolean> = topRatedLoadingLiveData
    fun getTopRatedMovieListLiveData(): LiveData<ListMovie?> = topRatedMovieListLiveData

    fun getNowPlayingLoadingLiveData(): LiveData<Boolean> = nowPlayingLoadingLiveData
    fun getNowPlayingRatedMovieListLiveData(): LiveData<ListMovie?> = nowPlayingMovieListLiveData

    fun getPopularMovieList() {
        viewModelScope.launch {
            popularLoadingLiveData.value = true
            popularMovieListLiveData.value = movieListRepository.getPopularMovieList()
            popularLoadingLiveData.value = false
        }
    }

    fun getTopRatedMovieList() {
        viewModelScope.launch {
            topRatedLoadingLiveData.value = true
            topRatedMovieListLiveData.value = movieListRepository.getTopRatedMovieList()
            topRatedLoadingLiveData.value = false
        }
    }

    fun getNowPlayingMovieList() {
        viewModelScope.launch {
            nowPlayingLoadingLiveData.value = true
            nowPlayingMovieListLiveData.value = movieListRepository.getNowPlayingMovieList()
            nowPlayingLoadingLiveData.value = false
        }
    }

}