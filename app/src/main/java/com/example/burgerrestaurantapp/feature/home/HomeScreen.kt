package com.example.burgerrestaurantapp.feature.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.burgerrestaurantapp.R
import com.example.burgerrestaurantapp.feature.home.component.BurgerBottomBar
import com.example.burgerrestaurantapp.feature.home.domain.BottomBarDestination
import com.example.burgerrestaurantapp.feature.nav.Screens
import com.example.burgerrestaurantapp.ui.theme.BurgerRestaurantAppTheme
import com.example.burgerrestaurantapp.ui.theme.FontSize
import com.example.burgerrestaurantapp.ui.theme.IconPrimary
import com.example.burgerrestaurantapp.ui.theme.Surface
import com.example.burgerrestaurantapp.ui.theme.SurfaceLighter
import com.example.burgerrestaurantapp.ui.theme.TextPrimary
import com.example.burgerrestaurantapp.ui.theme.oswaldVariableFont

@OptIn(ExperimentalMaterial3Api::class)
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
    Scaffold(
        containerColor = Surface,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    AnimatedContent(targetState = selectedDestination) { destination ->
                        Text(
                            text = destination.title,
                            fontFamily = oswaldVariableFont(),
                            fontSize = FontSize.LARGE,
                            color = TextPrimary

                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(R.drawable.menu),
                            contentDescription = "Menu Button",
                            tint = IconPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Surface,
                    scrolledContainerColor = Surface,
                    titleContentColor = TextPrimary,
                    actionIconContentColor = IconPrimary,

                    )
            )
        }
    ) { paddingValues ->

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