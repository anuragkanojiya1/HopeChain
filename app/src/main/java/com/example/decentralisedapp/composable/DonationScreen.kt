package com.example.decentralisedapp.composable

import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.decentralisedapp.CampaignList
import com.example.decentralisedapp.R
import com.example.decentralisedapp.viewmodels.SnapViewModel
import com.solana.mobilewalletadapter.clientlib.ActivityResultSender
import org.checkerframework.checker.units.qual.Length
import kotlin.math.absoluteValue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonationScreen(
    identityUri: Uri,
    iconUri: Uri,
    identityName: String,
    activityResultSender: ActivityResultSender,
    navController: NavController, snapViewModel: SnapViewModel) {

    val campaignInfoArgs = navController.currentBackStackEntry?.arguments
    val title = campaignInfoArgs?.getString("title") ?: ""
    val balance = campaignInfoArgs?.getLong("balance") ?: 0L
    val description = campaignInfoArgs?.getString("description") ?: ""

    val campaignAddress = CampaignList.campaignList[title]?.address

    var memo = remember { mutableStateOf("") }
    var donationAmount = remember { mutableStateOf("") }
    val viewState = snapViewModel.viewState.collectAsState()

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(4.dp)
    ) {
        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                tint = Color(0xFF181411),
                modifier = Modifier.size(24.dp)
            )
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF181411),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.width(24.dp))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Title
        Text(
            text = "How much do you want to donate?",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF181411),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Donation Amount Input
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(Color(0xFFF4F2F0), shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = donationAmount.value,
                onValueChange = {
                    if (it.isEmpty() || it.toDoubleOrNull() != null) {
                        donationAmount.value = it
                    }
                },
                placeholder = { Text("0.00 USDC", color = Color(0xFF897361)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color(0xFF181411)
                ),
                textStyle = TextStyle(fontSize = 18.sp),
               // trailingIcon =
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with your dollar icon
                contentDescription = null,
                tint = Color(0xFF897361),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Create a memo?",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF181411),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(Color(0xFFF4F2F0), shape = RoundedCornerShape(12.dp))
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = memo.value,
                onValueChange = {
                    memo.value = it
                },
                placeholder = { Text("Create a Memo", color = Color(0xFF897361)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color(0xFF181411)
                ),
                textStyle = TextStyle(fontSize = 18.sp),
               // trailingIcon =
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with your dollar icon
                contentDescription = null,
                tint = Color(0xFF897361),
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Currency Options
        Text(
            text = "Choose Custom Memo",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF181411),
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 16.dp)
        ) {
            listOf("USDC", "USDT", "BUSD", "SOL").forEach { currency ->
                Box(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .background(Color(0xFFF4F2F0), shape = RoundedCornerShape(12.dp))
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = currency,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF181411)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                val amount = donationAmount.value.toDoubleOrNull() ?: 0.1
                if (viewState.value.userAddress.isEmpty()){
                    snapViewModel.connect(identityUri, iconUri, identityName, activityResultSender)
                    Toast.makeText(context, "Connect to Wallet First", Toast.LENGTH_SHORT).show()
                }
                else {
                    snapViewModel.sendSol(
                        campaignAddress.toString(),
                        amount,
                        memo.value,
                        identityUri,
                        iconUri,
                        identityName,
                        activityResultSender,
                    )
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEC7113)),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(48.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Donate",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun dsPreview(){
 //   DonationScreen(navController = rememberNavController(), SnapViewModel())
}