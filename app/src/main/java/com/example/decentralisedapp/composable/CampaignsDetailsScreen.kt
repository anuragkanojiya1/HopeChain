package com.example.decentralisedapp.composable

import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.decentralisedapp.CampaignList
import com.example.decentralisedapp.viewmodels.DappViewModel
import com.example.decentralisedapp.SignatureInfo
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.collections.get

@Composable
fun CampaignDetailsScreen(dappViewModel: DappViewModel, navController: NavController) {
    val scope = rememberCoroutineScope()
    val campaignData = remember { mutableStateOf<List<CampaignInfo>>(emptyList()) }
    val signaturesData = remember { mutableStateMapOf<String, List<SignatureInfo>>() }

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

                // Start polling for signatures with a coroutine for each campaign
                scope.launch {
                    while (true) {
                        dappViewModel.getSignaturesWithMemo(publicKey) { signatures ->
                            // Only update if there’s a change
                            if (signaturesData[publicKey] != signatures.take(5)) {
                                signaturesData[publicKey] = signatures.take(5)  // Keep only last 5 signatures
                            }
                        }
                        delay(5000L)  // Polling interval
                    }
                }
            }
        }
    }

    // UI
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Campaign Details",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(campaignData.value) { campaign ->
                CampaignItem(
                    campaignName = campaign.name,
                    balance = campaign.balance,
                    description = campaign.description,
                    signatures = signaturesData[CampaignList.campaignList[campaign.name]?.address] ?: emptyList()
                )
            }
        }
    }
}

@Composable
fun CampaignItem(
    campaignName: String,
    balance: Long,
    description: String,
    signatures: List<SignatureInfo>
) {
    val g = Brush.linearGradient(
        listOf(
            Color.Red,
            Color.Yellow
        )
    )
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.background(g)) {
            Text(
                text = "Campaign: $campaignName",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Balance: ${balance / 1000000000} SOL",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Description: $description",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Signatures:",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            if (signatures.isEmpty()) {
                Text(
                    text = "No signatures available",
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
            } else {
                signatures.forEach { signatureInfo ->
                    SignatureItem(signatureInfo)
                }
            }
        }
    }
}

@Composable
fun SignatureItem(signatureInfo: SignatureInfo) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = "Signature: ${signatureInfo.signature}",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "Memo: ${signatureInfo.memo ?: "No memo"}",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

// Data class to represent each campaign information
data class CampaignInfo(
    val name: String,
    val balance: Long,
    val description: String
)

@Preview(showBackground = true)
@Composable
fun CampaignsDetailsScreenPreview(){
    CampaignDetailsScreen(dappViewModel = DappViewModel(), navController = rememberNavController())
}