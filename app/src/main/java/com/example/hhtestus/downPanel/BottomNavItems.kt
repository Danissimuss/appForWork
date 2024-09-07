package com.example.hhtestus.downPanel

sealed class BottomNavItems(val route: String, val icon: String, val iconSelected: String, val label: String) {

    object Home: BottomNavItems("homeScreen", "search", "search_selected", "Поиск")
    object Fav: BottomNavItems("favScreen", "favourite", "favourite_selected", "Избранное")
    object Responce: BottomNavItems("goslingScreen", "response", "response_selected", "Отклики")
    object Message: BottomNavItems("bladerunnerScreen", "message", "message", "Сообщения")
    object Profile: BottomNavItems("driveScreen", "profile", "profile_selected", "Профиль")

}