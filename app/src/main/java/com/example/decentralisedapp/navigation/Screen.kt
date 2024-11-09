package com.example.decentralisedapp.navigation

sealed class Screen(val route: String){
    object CampaignScreen: Screen("campaign_screen")
    object CampaignDetailsScreen: Screen("campaign_details_screen")
    object DonationDetailsScreen: Screen("donation_details_screen")
    object DonationScreen: Screen("donation_screen")
    object MainScreen: Screen("main_screen")
    object HomeScreen: Screen("home_screen")
}