package com.example.decentralisedapp

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
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonationScreen() {
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
                text = "Donate to Ukraine",
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
                value = "",
                onValueChange = {},
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
                value = "",
                onValueChange = {},
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

        // Donate Button
        Button(
            onClick = { /* Handle donation */ },
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
    DonationScreen()
}