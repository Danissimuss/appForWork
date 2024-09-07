package com.example.hhtestus.downPanel.response

import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.hhtestus.Navigation.SharedViewModel
import com.example.hhtestus.downPanel.ApiSide.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun responseApi (viewModel: SharedViewModel, navController: NavController) {

    viewModel.setLoading(true)

    viewModel.viewModelScope.launch(Dispatchers.IO){
        try {

            RetrofitClient.apiService.getOffersAndVacancies()

            withContext(Dispatchers.Main) {
                viewModel.setLoading(false)

                navController.navigate("allVac")
            }

        } catch (e: Exception) {
            println(e)
        }

    }
}