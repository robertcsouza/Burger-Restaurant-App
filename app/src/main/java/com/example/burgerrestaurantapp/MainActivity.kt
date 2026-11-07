package com.example.burgerrestaurantapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.burgerrestaurantapp.feature.nav.BurgerNavGraph
import com.example.burgerrestaurantapp.ui.theme.BurgerRestaurantAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BurgerRestaurantAppTheme {
                BurgerNavGraph()
            }
        }
    }
}
