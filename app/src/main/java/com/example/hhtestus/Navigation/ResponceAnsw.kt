package com.example.hhtestus.Navigation

import com.example.hhtestus.downPanel.ApiSide.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class responceAnsw (
    private val apiService: ApiService,
    private val viewModel: SharedViewModel,
    private val onError: (String) -> Unit,
    private val onSuccess: () -> Unit
) {

    fun fetchOff(){
        CoroutineScope(Dispatchers.IO).launch {

            try {
                val response = apiService.getOffersAndVacancies()
                val vacancies = response.vacancies
                val offers = response.offers

                withContext(Dispatchers.Main){
                    viewModel.setOffers(offers)
                    viewModel.setVacancies(vacancies)
                    viewModel.setLoading(false)
                    onSuccess()
                }
            } catch (e: Exception){
                withContext(Dispatchers.Main){
                    viewModel.setLoading(false)
                    onError("ошибка сети: ${e.message}")
                }
            }

        }
    }

}