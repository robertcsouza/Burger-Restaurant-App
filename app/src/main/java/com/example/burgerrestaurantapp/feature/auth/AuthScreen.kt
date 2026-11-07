package com.example.burgerrestaurantapp.feature.auth

import android.app.Activity
import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.burgerrestaurantapp.R
import com.example.burgerrestaurantapp.core.data.auth.GoogleUiClient
import com.example.burgerrestaurantapp.feature.components.GoogleButton
import com.example.burgerrestaurantapp.feature.components.PrimaryButton
import com.example.burgerrestaurantapp.ui.theme.BurgerRestaurantAppTheme
import com.example.burgerrestaurantapp.ui.theme.FontSize
import com.example.burgerrestaurantapp.ui.theme.oswaldVariableFont
import kotlinx.coroutines.launch

import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun AuthScreen(navigateToHome: () -> Unit) {
    val context = LocalContext.current
    val activity = context as Activity
    val scope = rememberCoroutineScope()

//    Inject With Coin

    val authViewModel: AuthViewModel = koinViewModel()

    val googleAuthUiClient: GoogleUiClient = koinInject()

    val uiEvent by authViewModel.uiEvent.collectAsStateWithLifecycle()

    val loading = uiEvent is AuthUiEvent.Loading

    LaunchedEffect(uiEvent) {
        when (uiEvent) {
            is AuthUiEvent.Success -> {
                navigateToHome()
                authViewModel.consumeEvent()
            }

            is AuthUiEvent.Error -> {
                authViewModel.consumeEvent()
            }

            else -> Unit

        }
    }

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
                loading = loading,
                onClick = {
                    scope.launch {
                        authViewModel.startLoading()
                        try {
                            val authResult = googleAuthUiClient.signInWithGoogle(activity)
                            val user = authResult.user
                            if (user != null) {
                                authViewModel.onFirebaseUserSignIn(user)
                            } else {
                                authViewModel.emitError("Google SignIn failed")
                            }
                        } catch (e: Exception) {
                            Log.e(
                                "GoogleSignIn",
                                "Falha: ${e.javaClass.name} | ${e.message}",
                                e
                            )
                            authViewModel.emitError(e.message ?: "Sign-in Error")
                        }

                    }
                },
                icon = painterResource(
                    R.drawable
                        .google_logo
                )
            )
            Spacer(modifier = Modifier.height(14.dp))
            PrimaryButton(
                text = stringResource(R.string.guest_text), icon = painterResource(
                    R
                        .drawable.log_in
                ),
                onClick = {
                    scope.launch {
                        try {
                            authViewModel.startLoading()
                            val guestResult = googleAuthUiClient.guestSign()
                            val user = guestResult.user
                            if (user != null) {
                                authViewModel.onFirebaseUserSignIn(user)
                            } else {
                                authViewModel.emitError("Guest SignIn failed")
                            }
                        } catch (e: Exception) {
                            authViewModel.emitError(e.message ?: "Error  to loading as Guest")
                        }
                    }
                })
        }

    }

}


@Preview
@Composable
private fun AuthScreePreview() {
    BurgerRestaurantAppTheme() {
        AuthScreen({})
    }
}