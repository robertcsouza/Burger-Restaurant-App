package com.example.burgerrestaurantapp.feature.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.burgerrestaurantapp.R
import com.example.burgerrestaurantapp.feature.components.GoogleButton
import com.example.burgerrestaurantapp.feature.components.PrimaryButton
import com.example.burgerrestaurantapp.ui.theme.BurgerRestaurantAppTheme
import com.example.burgerrestaurantapp.ui.theme.FontSize
import com.example.burgerrestaurantapp.ui.theme.oswaldVariableFont

@Composable
fun AuthScreen(modifier: Modifier = Modifier) {

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(R.drawable.burgers),
                    contentDescription = "Burgers Logo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(width = 220.dp, height = 130.dp)
                )

                Text(
                    text = stringResource(R.string.sign_text),
                    fontFamily = oswaldVariableFont(),
                    fontSize = FontSize.MEDIUM
                )
            }

            GoogleButton(
                loading = false,
                onClick = {},
                icon = painterResource(
                    R.drawable
                        .google_logo
                )
            )
            Spacer(modifier = Modifier.height(14.dp))
            PrimaryButton(text = stringResource(R.string.guest_text), icon = painterResource(R
                .drawable.log_in),
                onClick = {})
        }

    }

}


@Preview
@Composable
private fun AuthScreePreview() {
    BurgerRestaurantAppTheme() {
        AuthScreen()
    }
}