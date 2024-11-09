package com.example.decentralisedapp.composable

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.runtime.mutableStateOf
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.decentralisedapp.navigation.Screen
import com.example.decentralisedapp.viewmodels.DappViewModel
import com.example.decentralisedapp.viewmodels.SnapViewModel
import com.solana.mobilewalletadapter.clientlib.ActivityResultSender
import com.example.decentralisedapp.R

@Composable
fun MainScreen2(
    identityUri: Uri,
    iconUri: Uri,
    identityName: String,
    activityResultSender: ActivityResultSender,
    navController: NavController,
    dappViewModel: DappViewModel,
    snapViewModel: SnapViewModel
) {
    val screens = listOf("Home", "Campaigns", "Details")
    val selectedScreen = remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(screens, selectedScreen.value) {
                selectedScreen.value = it
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedScreen.value) {
                0 -> HomeScreen(
                        identityUri,
                        iconUri,
                        identityName,
                        activityResultSender,
                        navController,
                        snapViewModel
                )
                1 -> CampaignScreen(
                    identityUri,
                    iconUri,
                    identityName,
                    activityResultSender,
                    navController, dappViewModel)
                2 -> CampaignDetailsScreen(DappViewModel(), navController)
            }
        }
    }
}

@Composable
fun BottomNavigationBar(items: List<String>, selectedIndex: Int, onItemSelected: (Int) -> Unit) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = {
                    when (index) {
                        0 -> Icon(Icons.Default.Home, contentDescription = item)
                        1 -> Icon(Icons.Default.List, contentDescription = item)
                        2 -> Icon(Icons.Default.Info, contentDescription = item)
                    }
                },
                label = { Text(item) },
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) }
            )
        }
    }
}

@Composable
fun HomeScreen(
    identityUri: Uri,
    iconUri: Uri,
    identityName: String,
    activityResultSender: ActivityResultSender,
    navController: NavController,
    snapViewModel: SnapViewModel
) {
    // Observe the view state to get wallet connection status
    val viewState = snapViewModel.viewState.collectAsState().value


    Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.givingdonation), // Replace with your actual drawable resource ID
                contentDescription = "Group of people making a difference",
                modifier = Modifier.fillMaxWidth(0.7f)
                    .clip(shape = RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Welcome to the future of giving",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Join the thousands of individuals and organizations who are making a difference every day with Solana. Connect your wallet to get started.",
                fontSize = 16.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            if (viewState.noWallet) {
                Text(
                    text = "No wallet found. Please connect a wallet.",
                    color = Color.Red,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            } else {
                Text(
                    text = if (viewState.userAddress.isNotEmpty()) {
                        "Wallet Connected: ${viewState.userLabel} (${viewState.userAddress})"
                    } else {
                        "Wallet not connected."
                    },
                    color = if (viewState.userAddress.isNotEmpty()) Color.Green else Color.Gray,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

        Spacer(modifier = Modifier.height(12.dp))
            Button(onClick = {
                snapViewModel.connect(
                    identityUri,
                    iconUri,
                    identityName,
                    activityResultSender,
                )
            },
                modifier = Modifier
                    .height(32.dp)
                    .defaultMinSize(minWidth = 84.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEC7113)),
                contentPadding = PaddingValues(horizontal = 16.dp)
            ) {
                Text("Connect Wallet")
            }
        }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
   // MainScreen2(navController = rememberNavController())
}
