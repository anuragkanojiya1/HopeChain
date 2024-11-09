package com.example.decentralisedapp

//object CampaignList {
//    val puppyCare = "Puppy Care"
//    val pookieBear = "Pookie Bear"
//    val healthCampaign = "Health Campaign"
//    val campaignList = hashMapOf(
//        puppyCare to "FzH6RrFXzfBjvaRNCBkyeaxqCzeQ75LHcDR3cHms4baK",
//        pookieBear to "GevgS45omAamZUEuEV9KniFKkX3543RtSgWLL344JqoQ",
//        healthCampaign to "GevgS45omAamZUEuEV9KniFKkX3543RtSgWLL344JqoQ"
//    )
//
//    val c = campaignList.get("Puppy Care")
//}

object CampaignList {
    data class Campaign(val address: String, val description: String)

    val puppyCare = "Puppy Care"
    val RescueBear = "Bear Rescue"
    val healthCampaign = "Global Health Initiative"
    val oceanCleanup = "Ocean Cleanup"
    val treePlanting = "Tree Planting"
    val educationFund = "Education Fund"
    val animalRescue = "Animal Rescue"
    val foodForAll = "Food For All"
    val mentalHealthSupport = "Mental Health Support"
    val cleanWater = "Clean Water Initiative"

    val campaignList = hashMapOf(
        puppyCare to Campaign("FzH6RrFXzfBjvaRNCBkyeaxqCzeQ75LHcDR3cHms4baK", "Help provide food, shelter, and medical care for rescued puppies."),
        RescueBear to Campaign("GevgS45omAamZUEuEV9KniFKkX3543RtSgWLL344JqoQ", "Support the rescue and rehabilitation of injured and abandoned bears."),
        healthCampaign to Campaign("FPJqfNcWUSxhgnzggaK81mUduWox1pFXmQC38RadefDD", "A global initiative to provide medical care and medicines to underserved communities."),
        oceanCleanup to Campaign("H9n7tPW95BX3wH1enkF8qXEKjvgZ6WzNCdAfEVtf2wUF", "A campaign to clean the ocean and protect marine life."),
//        treePlanting to Campaign("6Mr7rQkfyTgeW7yRDi1knqww5vn5tGv8UWA7T7S8UCA7", "This campaign focuses on planting trees to combat climate change."),
//    educationFund to Campaign("HfT87sMwE2HjFkDkw3pkm3fn9S7vhGmMN2LbgXKw9r7V", "An education fund to support underprivileged children with access to quality education."),
//    animalRescue to Campaign("4Y7JH5jVRu1X3sPz4Qh7W7G7X9VbyrHLp2F9s9hh6NKv", "Supporting animal shelters and rescuing abandoned animals."),
//    foodForAll to Campaign("D2zvF2cHs3rf3G4YHaQx9jHGkgp8c5L2P8gVYfgrfdfr", "Feeding the hungry and providing food for people in need."),
//    mentalHealthSupport to Campaign("CwFpY6t3HTNGq7wFkvH7Rgg7XGH8fkyBdy8GF8D57R4T", "Providing mental health support and raising awareness."),
//    cleanWater to Campaign("Gh8jns4p3ZYfXYzS8qTFE2X7sm9hpq8kzkc2z4qfj29H", "Access to clean water for communities in need.")

    )

    // Accessing address and description for a specific campaign
    val c = campaignList["Puppy Care"]
    val address = c?.address
    val description = c?.description

    fun getCampaignByName(name: String): Campaign? {
        return campaignList[name]
    }
}