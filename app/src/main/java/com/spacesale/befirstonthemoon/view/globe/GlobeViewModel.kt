package com.spacesale.befirstonthemoon.view.globe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spacesale.befirstonthemoon.domain.Planet
import com.spacesale.befirstonthemoon.domain.Sector
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.lang.Exception

class GlobeViewModel(
    private val repository: GlobeRepository
) : ViewModel() {

    private val _mutablePlanet = MutableLiveData<Planet>()
    val planet: LiveData<Planet> get() = _mutablePlanet

    private val _mutableSectors = MutableLiveData<List<Sector>>()
    val sectors: LiveData<List<Sector>> get() = _mutableSectors

    fun loadPlanetInfo(planetId: Int) {
        viewModelScope.launch {
            try {
                _mutablePlanet.value = repository.getPlanetById(planetId)
            } catch (e: Exception) {
                Log.e(
                    GlobeViewModel::class.java.simpleName,
                    "Error load planet by planetId := $planetId ${e.message}"
                )
            }
        }
    }

    fun loadSectors(planetId: Int) {
        viewModelScope.launch {
            try {
                _mutableSectors.value = repository.getSectorByPlanetId(planetId)
            } catch (e: Exception) {
                Log.e(
                    GlobeViewModel::class.java.simpleName,
                    "Error load planet by planetId := $planetId ${e.message}"
                )
            }
        }
    }

    suspend fun buySector(planetId: Int, sectorId: Int): Boolean {
        var b: Boolean = false
        viewModelScope.async {
            b = repository.buySector(planetId,sectorId)
        }.await()
        return b
    }

}