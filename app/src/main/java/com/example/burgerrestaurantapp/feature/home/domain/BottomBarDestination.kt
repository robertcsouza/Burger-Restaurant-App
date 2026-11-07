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
        title = "Cart",
        screen = Screens.Cart
    ),

    NotificationsScreen(
        icon = Resources.Icon.Bell,
        title = "Notifications",
        screen = Screens.Notifications
    ),

    CategoriesScreen(
        icon = Resources.Icon.Categories,
        title = "Categories",
        screen = Screens.Categories
    )

}