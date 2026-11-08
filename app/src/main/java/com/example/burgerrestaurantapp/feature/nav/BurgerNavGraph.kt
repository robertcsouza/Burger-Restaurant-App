package com.example.burgerrestaurantapp.feature.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.burgerrestaurantapp.feature.auth.AuthScreen
import com.example.burgerrestaurantapp.feature.home.HomeScreen
import com.example.burgerrestaurantapp.feature.profile.ProfileScreen
import com.example.burgerrestaurantapp.feature.splash.SplashScreen

@Composable
fun BurgerNavGraph(startDestination: Screens = Screens.SplashScreen) {

    val navController = rememberNavController()

    NavHost(
        startDestination = startDestination,
        navController = navController
    ) {
        composable<Screens.SplashScreen> {
            SplashScreen(
                navigateToAuth = {
                    navController.navigate(Screens.AuthScreen) {
                        popUpTo<Screens.SplashScreen> {
                            inclusive = true
                        }
                    }
                },
                navigateToHome = {
                    navController.navigate(Screens.HomeGraph) {
                        popUpTo<Screens.AuthScreen> { inclusive = true }
                    }
                }
            )
        }

        composable<Screens.AuthScreen> {
            AuthScreen(navigateToHome = {
                navController.navigate(Screens.HomeGraph) {
                    popUpTo<Screens.AuthScreen> { inclusive = true }
                }
            })
        }

        composable<Screens.HomeGraph> {
            HomeScreen(
                navigateToAuth = {
                    navController.navigate(Screens.AuthScreen) {
                        popUpTo<Screens.HomeGraph> { inclusive = true }
                    }
                },
                navigateToProfile = {
                    navController.navigate(Screens.Profile)
                }
            )
        }

        composable<Screens.Profile> {
            ProfileScreen(
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}