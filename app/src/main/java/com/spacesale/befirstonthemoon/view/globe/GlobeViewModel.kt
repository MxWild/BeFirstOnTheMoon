package com.spacesale.befirstonthemoon.view.globe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spacesale.befirstonthemoon.domain.Planet
import kotlinx.coroutines.launch
import java.lang.Exception

class GlobeViewModel(
    private val globeRepository: GlobeRepository
) : ViewModel() {

    private val _mutablePlanet = MutableLiveData<Planet>()
    val planet: LiveData<Planet> get() = _mutablePlanet

    fun loadPlanetInfo(planetId: Int) {
        viewModelScope.launch {
            try {
                _mutablePlanet.value = globeRepository.getPlanetById(planetId)
            } catch (e: Exception) {
                Log.e(
                    GlobeViewModel::class.java.simpleName,
                    "Error load planet by planetId := $planetId ${e.message}"
                )
            }
        }
    }

}