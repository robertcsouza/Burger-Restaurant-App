package com.example.burgerrestaurantapp.feature.profile

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.burgerrestaurantapp.feature.components.InfoCard
import com.example.burgerrestaurantapp.feature.components.LoadingCard
import com.example.burgerrestaurantapp.feature.components.PrimaryButton
import com.example.burgerrestaurantapp.feature.profile.component.ProfileForm
import com.example.burgerrestaurantapp.feature.util.DisplayResult
import com.example.burgerrestaurantapp.feature.util.RequestState
import com.example.burgerrestaurantapp.ui.theme.FontSize
import com.example.burgerrestaurantapp.ui.theme.IconPrimary
import com.example.burgerrestaurantapp.ui.theme.Resources
import com.example.burgerrestaurantapp.ui.theme.Surface
import com.example.burgerrestaurantapp.ui.theme.TextPrimary
import com.example.burgerrestaurantapp.ui.theme.oswaldVariableFont
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    navigateBack: () -> Unit
) {

    val profileViewModel = koinViewModel<ProfileViewModel>()
    val screenState = profileViewModel.screenState
    val screenready = profileViewModel.screenReady
    val isFormValid = profileViewModel.isFormValid
    val context = LocalContext.current

    Scaffold(
        containerColor = Surface,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "My Profile",
                        fontFamily = oswaldVariableFont(),
                        fontSize = FontSize.REGULAR,
                        color = TextPrimary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBack() }) {
                        Icon(
                            painter = painterResource(Resources.Icon.BackArrow),
                            contentDescription = "Arrow Back",
                            tint = IconPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Surface,
                    scrolledContainerColor = Surface,
                    navigationIconContentColor = IconPrimary,
                    titleContentColor = TextPrimary,
                    actionIconContentColor = IconPrimary,

                    )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(paddingValues)
                .imePadding()
        ) {

            screenready.DisplayResult(
                onLoading = { LoadingCard(modifier = Modifier.fillMaxSize()) },
                onSuccess = {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ProfileForm(
                            modifier = Modifier.weight(1f),
                            firstName = screenState.firstName,
                            onFirstNameChange = profileViewModel::updateFirstName,
                            lastName = screenState.lastName,
                            onLastNameChange = profileViewModel::updateLastName,
                            email = screenState.email,
                            city = screenState.city,
                            onCityChange = profileViewModel::updateCity,
                            postalCode = screenState.postalCode,
                            onPostalCodeChange = profileViewModel::updatePostalCode,
                            address = screenState.address,
                            onAddressChange = profileViewModel::updateAddress,
                            phoneNumber = screenState.phoneNumber?.number,
                            onPhoneNumberChange = profileViewModel::updatePhoneNumber
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        PrimaryButton(
                            text = "Update",
                            icon = painterResource(Resources.Icon.Checkmark),
                            enabled = isFormValid,
                            onClick = {
                                profileViewModel.updateCustomer(
                                    onSuccess = {
                                        RequestState.Success("Customer details successfully updated!")
                                        Toast.makeText(
                                            context,
                                            "Customer details successfully updated!",
                                            Toast.LENGTH_SHORT
                                        ).show()

                                    },
                                    onError = { message ->
                                        RequestState.Error("Error updating customer details")
                                        Toast.makeText(
                                            context,
                                            "Error updating customer details",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                )
                            },

                            )
                    }
                },
                onError = { message ->
                    InfoCard(
                        image = Resources.Icon.Dog,
                        title = "Oops!",
                        subtitle = message
                    )
                }
            )
        }
    }

}