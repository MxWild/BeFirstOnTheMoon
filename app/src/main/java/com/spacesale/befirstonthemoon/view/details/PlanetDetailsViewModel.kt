package com.spacesale.befirstonthemoon.view.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.domain.Planet
import com.spacesale.befirstonthemoon.view.globe.GlobeViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

class PlanetDetailsViewModel(
    private val repository: PlanetDetailsRepository
) : ViewModel() {
    private val _planetLiveData = MutableLiveData<Planet>()

    val planetLiveData: LiveData<Planet>
        get() = _planetLiveData

    fun showPlanet(
        planetId: Int
    ) {

        if (_planetLiveData.value?.id != planetId) {
            viewModelScope.launch {
                try {
                    _planetLiveData.value = repository.getPlanetById(planetId)
                } catch (e: Exception) {
                    Log.e(
                        PlanetDetailsViewModel::class.java.simpleName,
                        "Error load planet by planetId := $planetId ${e.message}"
                    )
                }
            }
        }


    }

}