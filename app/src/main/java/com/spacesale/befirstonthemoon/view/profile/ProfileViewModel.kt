package com.spacesale.befirstonthemoon.view.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spacesale.befirstonthemoon.R
import com.spacesale.befirstonthemoon.domain.Purchase
import com.spacesale.befirstonthemoon.repository.ProfileRepository
import com.spacesale.befirstonthemoon.view.globe.GlobeViewModel
import kotlinx.coroutines.launch
import java.lang.Exception

class ProfileViewModel(private val profileRepository: ProfileRepository ) : ViewModel() {

    private val _purchasesLiveData = MutableLiveData<List<Purchase>>()

    val purchasesLiveData: LiveData<List<Purchase>>
            get() = _purchasesLiveData

    fun showPurchases(userId: Int) {
        /*val purchases: List<Purchase> = listOf(
            Purchase(
                planetPicture = R.drawable.moon_details_pic,
                planetName = "Луна",
                sectorNames = "Участок 123\nУчасток 234\nУчасток 345"
            ),
            Purchase(
                planetPicture = R.drawable.mars_details_pic,
                planetName = "Марс",
                sectorNames = "Участок 34453\nУчасток 424324"
            )
        )*/

        viewModelScope.launch {
            try {
                _purchasesLiveData.value = profileRepository.getPurchasesByUser(userId)
            } catch (e: Exception) {
                Log.e(
                    GlobeViewModel::class.java.simpleName,
                    "Error load purchases by userId := $userId ${e.message}"
                )
            }
        }

    }

}