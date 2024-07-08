package com.example.proyfinalmv2.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.proyfinalmv2.models.Rest.RestSnFilts
import com.example.proyfinalmv2.repositories.items.Rest.RestSinFiltrRepository

class ActRestGlbModel : ViewModel() {
    private val _restInv: MutableLiveData<RestSnFilts> by lazy {
        MutableLiveData<RestSnFilts>(RestSnFilts())
    }
    val restInv: LiveData<RestSnFilts> get() = _restInv

    fun goToDetRestInv(restinvc: RestSnFilts) {
        _restInv.value = restinvc
    }

    fun fetchLstRestInv(restId: Int) {
        RestSinFiltrRepository.searchRest(
            id = 0,
            success = { restinv ->
                restinv?.let {
                    val restinvSorted = it
                    val restList = RestSnFilts()
                    restList.addAll(restinvSorted)
                    _restInv.value = restList
                }
            },
            failure = {
                it.printStackTrace()
            })
    }
}