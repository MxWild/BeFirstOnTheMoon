package com.spacesale.befirstonthemoon.view.planets

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spacesale.befirstonthemoon.domain.Planet
import kotlinx.coroutines.launch
import java.lang.Exception

class SelectPlanetViewModel(private val repository: SelectPlanetRepository) : ViewModel() {
    private val _planetLiveData = MutableLiveData<List<Planet>>()
    val planetLiveData: LiveData<List<Planet>> get() = _planetLiveData

    fun loadPlanets() {
        viewModelScope.launch {
                try {
                    Log.e("QWERTY_VM", "try")
                    _planetLiveData.value = repository.getPlanets()
                    Log.e("QWERTY_VM", _planetLiveData.value.toString())
                } catch (e: Exception) {
                    Log.e(
                        SelectPlanetViewModel::class.java.simpleName,
                        "Error load planets ${e.message}"
                    )
                }
        }


    }

}