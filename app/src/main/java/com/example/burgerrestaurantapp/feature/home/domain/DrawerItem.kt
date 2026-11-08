package com.example.burgerrestaurantapp.feature.home.domain

import com.example.burgerrestaurantapp.ui.theme.Resources

enum class DrawerItem(
    val title: String,
    val icon: Int
) {

    Profile(
        title = "Profile",
        icon = Resources.Icon.Person
    ),

    Locations(
        title = "Locations",
        icon = Resources.Icon.MapPin
    ),

    Rewards(
        title = "Rewards",
        icon = Resources.Icon.Heart
    ),

    Offers(
        title = "Offers",
        icon = Resources.Icon.Gift
    ),

    ContactUs(
        title = "Contact Us",
        icon = Resources.Icon.Edit
    ),

    SingOut(
        title = "Sign out",
        icon = Resources.Icon.SignOut
    ),

    AdminPanel(
        title = "Admin Panel",
        icon = Resources.Icon.Unlock
    )
}