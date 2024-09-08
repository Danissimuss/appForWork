package com.example.hhtestus.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.hhtestus.downPanel.favourite.favScreen
import com.example.hhtestus.downPanel.home.allVac.allVac
import com.example.hhtestus.downPanel.home.homeScreen
import com.example.hhtestus.downPanel.home.vacCard.VacancyCard
import com.example.hhtestus.downPanel.plug.bladerunnerScreen
import com.example.hhtestus.downPanel.plug.driveScreen
import com.example.hhtestus.downPanel.plug.goslingScreen
import com.example.hhtestus.registration.Greeting
import com.example.hhtestus.validation.validationScreen

@Composable
fun appNavigation(){
    
    val navController = rememberNavController()
    val viewModel: SharedViewModel = viewModel()
    
    NavHost(navController = navController, startDestination = "greeting"){
        composable("greeting"){
            Greeting(navController = navController, viewModel)
        }
        composable("second/{email}",
            arguments = listOf(navArgument("email") {type = NavType.StringType})){
            backStackEntry ->
            val email = backStackEntry.arguments?.getString("email")

            validationScreen(email = email, navController, viewModel = viewModel)
        }
        composable("homeScreen") {

            homeScreen(navController = navController, viewModel = viewModel)

        }

        composable("favScreen"){

            favScreen(viewModel, navController)

        }

        composable("goslingScreen") {

            goslingScreen(navController, viewModel)
        }

        composable("driveScreen") {

            driveScreen(viewModel, navController)
        }

        composable("bladerunnerScreen") {

            bladerunnerScreen(navController, viewModel)
        }

        composable("allVac") {

            allVac(navController, viewModel)
        }
        composable("vacancyCard") {

            val selectedVacancy = viewModel.selectedVacancy
            if (selectedVacancy != null) {
                VacancyCard(viewModel = viewModel, vacancy = selectedVacancy, navController)
            }
        }
    }
}