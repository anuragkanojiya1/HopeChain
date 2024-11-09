package com.example.decentralisedapp.composable

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.decentralisedapp.CampaignList
import com.example.decentralisedapp.navigation.Screen
import com.example.decentralisedapp.viewmodels.DappViewModel
import com.solana.mobilewalletadapter.clientlib.ActivityResultSender

@Composable
fun CampaignScreen(
    identityUri: Uri,
    iconUri: Uri,
    identityName: String,
    activityResultSender: ActivityResultSender,
    navController: NavController,
    dappViewModel: DappViewModel) {
    val campaignData = remember { mutableStateOf<List<CampaignInfo>>(emptyList()) }

    LaunchedEffect(Unit) {
        // Populate campaign data initially
        CampaignList.campaignList.keys.take(10).forEach { campaignName ->
            val campaign = CampaignList.campaignList[campaignName]
            campaign?.let {
                val publicKey = it.address

                // Fetch balance for each campaign
                dappViewModel.viewTotalDonations(publicKey) { balance ->
                    val updatedCampaignData = campaignData.value.toMutableList()
                    updatedCampaignData.add(CampaignInfo(campaignName, balance ?: 0L, it.description))
                    campaignData.value = updatedCampaignData
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Donate",
            color = Color(0xFF181411),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Trending Campaigns",
            color = Color(0xFF181411),
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = (-0.015).em,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Display campaign items dynamically
        campaignData.value.forEach { campaignInfo ->
            CampaignItem(
                title = campaignInfo.name,
                description = campaignInfo.description,
                balance = campaignInfo.balance,
                navController = navController
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CampaignItem(
    title: String,
    description: String,
    balance: Long,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = Color(0xFF181411),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable(onClick = {
                    navController.navigate("donation_details_screen/${title}/${balance}/${description}")
                })
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                color = Color(0xFF897361),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.clickable(onClick = {
                    navController.navigate("donation_details_screen/${title}/${balance}/${description}")
                })
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Show the total donation balance
            Text(
                text = "Total Donations: ${balance/1000000000} SOL",
                color = Color(0xFF181411),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Button(
            onClick = {
                navController.navigate("donation_screen/${title}/${balance}/${description}")
            },
            modifier = Modifier
                .height(32.dp)
                .defaultMinSize(minWidth = 84.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF4F2F0)),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            Text(
                text = "Donate",
                color = Color(0xFF181411),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun CampaignScreenPreview(){
  //  CampaignScreen(navController = rememberNavController(), dappViewModel = DappViewModel())
}