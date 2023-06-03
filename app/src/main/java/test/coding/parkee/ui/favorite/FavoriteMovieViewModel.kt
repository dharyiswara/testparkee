package test.coding.parkee.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import test.coding.parkee.model.FavoriteTable
import test.coding.parkee.repository.FavoriteMovieRepository
import javax.inject.Inject

@HiltViewModel
class FavoriteMovieViewModel @Inject constructor(
    private val favoriteMovieRepository: FavoriteMovieRepository
) : ViewModel() {

    private val loadingLiveData = MutableLiveData<Boolean>()
    private val favoriteLiveData = MutableLiveData<List<FavoriteTable>>()

    fun getLoadingLiveData(): LiveData<Boolean> = loadingLiveData
    fun getFavoriteLiveData(): LiveData<List<FavoriteTable>> = favoriteLiveData

    fun getFavoriteMovie() {
        viewModelScope.launch {
            loadingLiveData.value = true
            favoriteLiveData.value = favoriteMovieRepository.getFavoriteMovie()
            loadingLiveData.value = false
        }
    }

}