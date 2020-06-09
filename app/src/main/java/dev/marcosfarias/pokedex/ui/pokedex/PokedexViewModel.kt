package dev.marcosfarias.pokedex.ui.pokedex

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.marcosfarias.pokedex.model.Pokemon
import dev.marcosfarias.pokedex.repository.PokedexRepository
import kotlinx.coroutines.launch

class PokedexViewModel(
    private val pokedexRepository: PokedexRepository
) : ViewModel() {

    val isLoadingData = MutableLiveData(false)
    private val pokedexListData = MutableLiveData<List<Pokemon>>()

    private val initialOffSet = 0
    private val listOffSet = 20

    fun getPokedexList(
        offset: Int = initialOffSet,
        limit: Int = listOffSet
    ): LiveData<List<Pokemon>> {
        viewModelScope.launch {
            pokedexRepository.loadPokedexList(
                offset = offset,
                limit = limit,
                isLoadingData = isLoadingData,
                pokedexListData = pokedexListData
            )
        }
        return pokedexListData
    }
}