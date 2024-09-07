package com.example.hhtestus.Navigation

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.hhtestus.downPanel.ApiSide.Offer
import com.example.hhtestus.downPanel.ApiSide.Vacancy
import com.example.hhtestus.downPanel.dataBase.dataBase
import com.example.hhtestus.downPanel.dataBase.vacDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SharedViewModel(application: Application) : AndroidViewModel(application) {

    var selectedVacancy by mutableStateOf<Vacancy?>(null)

    private val _offers = MutableLiveData<List<Offer>>()
    val offers: LiveData<List<Offer>> get() = _offers

    private val _vacancies = MutableLiveData<List<Vacancy>>()
    val vacancies: LiveData<List<Vacancy>> get() = _vacancies

    private val _isLoading = MutableLiveData(false)

    private val favorVac: LiveData<List<Vacancy>>

    private val  vacDao: vacDao

    init {

        val db = dataBase.getDatabase(application)
        vacDao = db.vacdao()
        favorVac = vacDao.getAllFav()

    }

    fun updateFav(vacancy: Vacancy, isFav: Boolean){

        viewModelScope.launch(Dispatchers.IO) {
            if (isFav) {
                vacDao.insert(vacancy.copy(isFavorite = true))
            } else {
                vacDao.delete(vacancy.copy(isFavorite = false))
            }
            withContext(Dispatchers.Main) {
                _vacancies.postValue(_vacancies.value?.map {
                    if (it.id == vacancy.id) it.copy(isFavorite = isFav) else it
                })
            }
        }
    }

    fun getFavVac(): LiveData<List<Vacancy>>{
        return favorVac
    }

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