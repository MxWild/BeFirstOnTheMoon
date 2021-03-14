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