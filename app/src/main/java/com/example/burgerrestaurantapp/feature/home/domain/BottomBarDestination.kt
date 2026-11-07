package com.example.burgerrestaurantapp.feature.home.domain

import com.example.burgerrestaurantapp.feature.nav.Screens
import com.example.burgerrestaurantapp.ui.theme.Resources

enum class BottomBarDestination(
    val icon: Int,
    val title: String,
    val screen: Screens
) {

    ProductOverviewScreen(
        icon = Resources.Icon.Home,
        title = "Burgers",
        screen = Screens.ProductOverviewScreen
    ),

    CartScreen(
        icon = Resources.Icon.ShoppingCart,
        title = "Burgers",
        screen = Screens.Cart
    ),

    NotificationsScreen(
        icon = Resources.Icon.Bell,
        title = "Burgers",
        screen = Screens.Notifications
    ),

    CategoriesScreen(
        icon = Resources.Icon.Categories,
        title = "Burgers",
        screen = Screens.Categories
    )

}