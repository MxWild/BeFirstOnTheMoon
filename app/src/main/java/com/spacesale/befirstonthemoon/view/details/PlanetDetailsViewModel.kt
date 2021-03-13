package com.spacesale.befirstonthemoon.view.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.domain.Planet

class PlanetDetailsViewModel() : ViewModel() {
    private val _planetLiveData = MutableLiveData<Planet>()

    val planetLiveData: LiveData<Planet>
        get() = _planetLiveData

    fun showPlanet(
        planetId: Int
    ) {

        val planet = Planet(
            1,
            "Марс",
            R.drawable.mars_select,
            R.drawable.ic_marsdetails,
            R.drawable.mars,
            "Марс — четвёртая по удалённости от Солнца и седьмая по размеру планета Солнечной системы; масса планеты составляет 10,7 % массы Земли. Названа в честь Марса — древнеримского бога войны, соответствующего древнегреческому Аресу. Иногда Марс называют «красной планетой» из-за красноватого оттенка поверхности, придаваемого ей минералом маггемидом— γ-оксидом железа.",
            "95,32 % углекислый газ\n2,7 % азот\n1,6 % аргон\n0,145 % кислород\n0,08 % угарный газ\n0,021 % водяной пар\n0,01 % окись азота\n0,00025 % неон",
            "Масса - 6,39Е23 кг\nРасстояние от Земли - от 55,76 до 401 млн км\nРадиус - 3389,5 км\nТемпература - от −153 °C до +35 °C"
        )
//        if (_planetLiveData.value?.id != movieId) {
//            viewModelScope.launch {
//
//            }
//        }

        _planetLiveData.value = planet
    }

}