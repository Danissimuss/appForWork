package com.example.hhtestus.Navigation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hhtestus.downPanel.ApiSide.Offer
import com.example.hhtestus.downPanel.ApiSide.Vacancy

class SharedViewModel : ViewModel() {

    private val _offers = MutableLiveData<List<Offer>>()
    val offers: LiveData<List<Offer>> get() = _offers

    private val _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>> get() = _vacancies

    private val _isLoading = MutableLiveData(false)

    fun setOffers(newOffers: List<Offer>) {
        _offers.value = newOffers

    }

    fun setVacancies(vacancyList: List<Vacancy>) {
        _vacancies.value = vacancyList
    }

    fun setLoading(isLoading: Boolean){

        _isLoading.value = isLoading

    }

}