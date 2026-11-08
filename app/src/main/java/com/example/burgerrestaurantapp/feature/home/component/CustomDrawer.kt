package com.example.burgerrestaurantapp.feature.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.burgerrestaurantapp.R
import com.example.burgerrestaurantapp.feature.home.domain.DrawerItem
import com.example.burgerrestaurantapp.ui.theme.BrandYellow
import com.example.burgerrestaurantapp.ui.theme.FontSize
import com.example.burgerrestaurantapp.ui.theme.TextBrand
import com.example.burgerrestaurantapp.ui.theme.oswaldVariableFont
import com.google.firebase.auth.FirebaseAuth

@Composable
fun CustomDrawer(
    onProfileClick: () -> Unit,
    onContactUsClick: () -> Unit,
    onSignOutClick: () -> Unit,
    onAdminPanelClick: () -> Unit
) {
    val auth = FirebaseAuth.getInstance()
    val currentUser = auth.currentUser
    Spacer(modifier = Modifier.height(50.dp))

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(0.6f)
            .padding(12.dp)
    ) {
        AsyncImage(
            model = currentUser?.photoUrl ?: "Unknow",
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            fallback = painterResource(R.drawable.user)

        )
        Spacer(modifier = Modifier.height(height = 8.dp))
        Text(
            text = "Welcome ${currentUser?.displayName?.split(" ")?.firstOrNull() ?: "User"}",
            fontFamily = oswaldVariableFont(),
            fontSize = FontSize.EXTRA_REGULAR,
            fontWeight = FontWeight.Medium,
            color = TextBrand
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 8.dp, end = 12.dp)
                .clip(RoundedCornerShape(99.dp)),
            thickness = 2.dp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(height = 30.dp))

        DrawerItem.entries.take(6).forEach { drawerItem ->
            DrawerItemCard(
                drawerItem = drawerItem,
                onClick = {
                    when (drawerItem) {
                        DrawerItem.Profile -> onProfileClick()
                        DrawerItem.ContactUs -> onContactUsClick()
                        DrawerItem.SingOut -> onSignOutClick()
                        else -> {}
                    }
                }
            )
            Spacer(modifier = Modifier.height(height = 4.dp))
        }
        Spacer(modifier = Modifier.weight(1f))
        DrawerItemCard(
            drawerItem = DrawerItem.AdminPanel,
            onClick = onAdminPanelClick
        )
        Spacer(modifier = Modifier.height(height = 24.dp))

    }

}