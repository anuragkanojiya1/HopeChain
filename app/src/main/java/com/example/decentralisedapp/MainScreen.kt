package com.example.decentralisedapp

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.decentralisedapp.viewmodels.DappViewModel
import com.example.decentralisedapp.viewmodels.SnapViewModel
import com.solana.mobilewalletadapter.clientlib.ActivityResultSender

@Composable
fun MainScreen(
    identityUri: Uri,
    iconUri: Uri,
    identityName: String,
    activityResultSender: ActivityResultSender,
    snapViewModel: SnapViewModel,
    dappViewModel: DappViewModel
) {
    val viewState = snapViewModel.viewState.collectAsState()

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to Snap!", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "You can connect to your wallet and sign a message on chain.",
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {

            if (viewState.value.userAddress.isEmpty()) {
                snapViewModel.connect(identityUri, iconUri, identityName, activityResultSender)
            } else {
                snapViewModel.disconnect()
            }
        }) {
            val pubKey = viewState.value.userAddress
            val buttonText = when {
                viewState.value.noWallet -> "Please install a wallet"
                pubKey.isEmpty() -> "Connect Wallet"
                viewState.value.userAddress.isNotEmpty() -> pubKey.take(4).plus("...")
                    .plus(pubKey.takeLast(4))

                else -> ""
            }
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = buttonText,
                maxLines = 1,
            )

        }
        Button(
            onClick = {
                snapViewModel.sign_message(identityUri, iconUri, identityName, activityResultSender)
            },
        ) {
            Text(text = "Sign Message")
        }

        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = {
                snapViewModel.sendSol(
                    "GevgS45omAamZUEuEV9KniFKkX3543RtSgWLL344JqoQ",
                    1.0,
                    "",
                    identityUri,
                    iconUri,
                    identityName,
                    activityResultSender
                )
            },
        ) {
            Text(text = "Send Transaction")
        }
        Button(
            onClick = {
                dappViewModel.getAccountInformation3("GevgS45omAamZUEuEV9KniFKkX3543RtSgWLL344JqoQ")
            }
        ) {
            Text(text = "Info")
        }
        Button(
            onClick = {
                snapViewModel.ViewTotalDonations("GevgS45omAamZUEuEV9KniFKkX3543RtSgWLL344JqoQ")
            }
        ) {
            Text(text = "view total")
        }
    }
}
