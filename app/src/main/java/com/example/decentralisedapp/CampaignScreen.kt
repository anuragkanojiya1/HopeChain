package com.example.decentralisedapp

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Composable
fun CampaignScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
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

        CampaignItem(
            title = "Support Ukraine",
            description = "Give to the people who are fighting for freedom in Russia."
        )

        CampaignItem(
            title = "DeFi Education Fund",
            description = "Help us fight for the future of the internet."
        )

        CampaignItem(
            title = "YC for Founders",
            description = "Join us in supporting the next generation of entrepreneurs."
        )

        CampaignItem(
            title = "Open Source Collective",
            description = "Invest in the future of web3 and open source software."
        )

        CampaignItem(
            title = "Solana Foundation",
            description = "Support the development of the Solana blockchain."
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun CampaignItem(title: String, description: String) {
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
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = description,
                color = Color(0xFF897361),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Button(
            onClick = { /* Handle Donate action */ },
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
    CampaignScreen()
}