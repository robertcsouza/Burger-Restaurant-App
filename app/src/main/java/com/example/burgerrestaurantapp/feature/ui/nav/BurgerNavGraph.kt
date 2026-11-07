package com.example.burgerrestaurantapp.feature.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.burgerrestaurantapp.feature.splash.SplashScreen

@Composable
fun BurgerNavGraph(startDestination: Screens = Screens.SplashScreen) {

    val navController = rememberNavController()

    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        composable<Screens.SplashScreen> {
            SplashScreen()
        }
    }
}