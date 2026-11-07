package com.example.burgerrestaurantapp.feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.burgerrestaurantapp.feature.home.component.BurgerBottomBar
import com.example.burgerrestaurantapp.feature.home.domain.BottomBarDestination
import com.example.burgerrestaurantapp.feature.nav.Screens
import com.example.burgerrestaurantapp.ui.theme.BurgerRestaurantAppTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryAsState()
    val selectedDestination by remember {
        derivedStateOf {
            val route = currentRoute.value?.destination?.route.toString()
            when {
                route.contains(BottomBarDestination.ProductOverviewScreen.screen.toString()) ->
                    BottomBarDestination.ProductOverviewScreen

                route.contains(BottomBarDestination.CartScreen.screen.toString()) ->
                    BottomBarDestination.CartScreen

                route.contains(BottomBarDestination.NotificationsScreen.screen.toString()) ->
                    BottomBarDestination.NotificationsScreen

                route.contains(BottomBarDestination.CategoriesScreen.screen.toString()) ->
                    BottomBarDestination.CategoriesScreen

                else -> BottomBarDestination.ProductOverviewScreen
            }
        }
    }
    Scaffold() { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            NavHost(
                modifier = Modifier.weight(1f),
                navController = navController,
                startDestination = Screens.ProductOverviewScreen
            ) {
                composable<Screens.ProductOverviewScreen> { }
                composable<Screens.Cart> { }
                composable<Screens.Notifications> { }
                composable<Screens.Categories> { }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Box(modifier = Modifier.padding(12.dp)) {
                BurgerBottomBar(
                    selected = selectedDestination,
                    onSelect = { destination ->

                        navController.navigate(destination.screen) {
                            launchSingleTop = true
                            popUpTo<Screens.ProductOverviewScreen> {
                                saveState = true
                                inclusive = false
                            }
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    BurgerRestaurantAppTheme() {
        HomeScreen()
    }
}