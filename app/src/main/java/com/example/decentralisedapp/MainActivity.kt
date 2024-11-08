package com.example.decentralisedapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.decentralisedapp.ui.theme.DecentralisedAppTheme
import com.portto.solana.web3.PublicKey
import com.solana.mobilewalletadapter.clientlib.ActivityResultSender

class MainActivity : ComponentActivity() {

    private val snapViewModel: SnapViewModel by viewModels()
    private val dappViewModel: DappViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val activityResultSender = ActivityResultSender(this)
        val identityUri: Uri = Uri.parse(application.getString(R.string.id_url))
        val iconUri: Uri = Uri.parse(application.getString(R.string.id_favicon))
        val identityName = application.getString(R.string.app_name)

        val accountPublicKey = "FzH6RrFXzfBjvaRNCBkyeaxqCzeQ75LHcDR3cHms4baK"
        dappViewModel.startSignaturePolling(accountPublicKey)

        setContent {
            DecentralisedAppTheme {

                CampaignDetailsScreen(dappViewModel)
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    MainScreen(
//                        identityUri = identityUri,
//                        iconUri = iconUri,
//                        identityName = identityName,
//                        activityResultSender = activityResultSender,
//                        snapViewModel = snapViewModel,
//                        dappViewModel = dappViewModel
//                    )
//                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dappViewModel.stopSignaturePolling()  // Optionally stop polling when the activity is destroyed
    }

}


