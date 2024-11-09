package com.example.decentralisedapp.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.decentralisedapp.composable.CampaignDetailsScreen
import com.example.decentralisedapp.composable.CampaignScreen
import com.example.decentralisedapp.composable.DonationDetailsScreen
import com.example.decentralisedapp.composable.DonationScreen
import com.example.decentralisedapp.composable.HomeScreen
import com.example.decentralisedapp.composable.MainScreen2
import com.example.decentralisedapp.viewmodels.DappViewModel
import com.example.decentralisedapp.viewmodels.SnapViewModel
import com.solana.mobilewalletadapter.clientlib.ActivityResultSender

@Composable
fun NavGraph(
    identityUri: Uri,
    iconUri: Uri,
    identityName: String,
    activityResultSender: ActivityResultSender,
    navController: NavController,
    dappViewModel: DappViewModel,
    snapViewModel: SnapViewModel
){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(Screen.HomeScreen.route){
            HomeScreen(
                identityUri,
                iconUri,
                identityName,
                activityResultSender,
                navController,
                snapViewModel
            )
        }
        composable(Screen.MainScreen.route){
            MainScreen2(
                identityUri,
                iconUri,
                identityName,
                activityResultSender,
                navController,
                dappViewModel,
                snapViewModel
            )
        }
        composable("donation_screen/{title}/{balance}/{description}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("balance") { type = NavType.LongType },
                navArgument("description") { type = NavType.StringType }
            )
        ){ backStackEntry ->
            DonationScreen(
                identityUri,
                iconUri,
                identityName,
                activityResultSender,
                navController,
                snapViewModel)
        }
        composable(Screen.CampaignScreen.route){
            CampaignScreen(identityUri,
                iconUri,
                identityName,
                activityResultSender,
                navController,
                dappViewModel)
        }
        composable("donation_details_screen/{title}/{balance}/{description}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("balance") { type = NavType.LongType },
                navArgument("description") { type = NavType.StringType }
            )){ backStackEntry ->
            DonationDetailsScreen(identityUri,
                iconUri,
                identityName,
                activityResultSender,
                navController)
        }
        composable(Screen.CampaignDetailsScreen.route){

            CampaignDetailsScreen(dappViewModel, navController)
        }
    }

}