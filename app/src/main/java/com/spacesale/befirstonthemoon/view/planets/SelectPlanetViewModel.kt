package com.spacesale.befirstonthemoon.view.planets

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.domain.Planet
import kotlinx.coroutines.launch
import java.lang.Exception

class SelectPlanetViewModel(private val repository: SelectPlanetRepository) : ViewModel() {
    private val _planetLiveData = MutableLiveData<Map<Int, Planet>>()

    val planetLiveData: LiveData<Map<Int, Planet>>
        get() = _planetLiveData

    fun getPlanets() {
        viewModelScope.launch {
                try {
                    _planetLiveData.value = repository.getPlanets()
                } catch (e: Exception) {
                    Log.e(
                        SelectPlanetViewModel::class.java.simpleName,
                        "Error load planets ${e.message}"
                    )
                }
        }


    }

}