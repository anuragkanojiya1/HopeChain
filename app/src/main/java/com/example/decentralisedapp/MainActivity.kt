package com.example.decentralisedapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.decentralisedapp.composable.CampaignDetailsScreen
import com.example.decentralisedapp.composable.HomeScreen
import com.example.decentralisedapp.composable.MainScreen2
import com.example.decentralisedapp.navigation.NavGraph
import com.example.decentralisedapp.ui.theme.DecentralisedAppTheme
import com.example.decentralisedapp.viewmodels.DappViewModel
import com.example.decentralisedapp.viewmodels.SnapViewModel
import com.solana.mobilewalletadapter.clientlib.ActivityResultSender

class MainActivity : ComponentActivity() {

    private lateinit var navController : NavController
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
            val navController = rememberNavController()
        //    x(identityUri, iconUri, identityName, activityResultSender, snapViewModel, dappViewModel)
            NavGraph(identityUri, iconUri, identityName, activityResultSender, navController, dappViewModel, snapViewModel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dappViewModel.stopSignaturePolling()  // Optionally stop polling when the activity is destroyed
    }

}


@Composable
fun x(
    identityUri: Uri,
    iconUri: Uri,
    identityName: String,
    activityResultSender: ActivityResultSender,
    snapViewModel: SnapViewModel,
    dappViewModel: DappViewModel
){
    MainScreen(identityUri, iconUri, identityName, activityResultSender, snapViewModel, dappViewModel)
}