package com.example.decentralisedapp

object CampaignList {
    val puppyCare = "Puppy Care"
    val pookieBear = "Pookie Bear"
    val healthCampaign = "Health Campaign"
    val campaignList = hashMapOf(
        puppyCare to "FzH6RrFXzfBjvaRNCBkyeaxqCzeQ75LHcDR3cHms4baK",
        pookieBear to "GevgS45omAamZUEuEV9KniFKkX3543RtSgWLL344JqoQ",
        healthCampaign to "GevgS45omAamZUEuEV9KniFKkX3543RtSgWLL344JqoQ"
    )

    val c = campaignList.get("Puppy Care")
}

//object CampaignList {
//    data class Campaign(val address: String, val description: String)
//
//    val puppyCare = "Puppy Care"
//    val pookieBear = "Pookie Bear"
//    val healthCampaign = "Health Campaign"
//
//    val campaignList = hashMapOf(
//        puppyCare to Campaign("FzH6RrFXzfBjvaRNCBkyeaxqCzeQ75LHcDR3cHms4baK", "This is the description of this campaign"),
//        pookieBear to Campaign("GevgS45omAamZUEuEV9KniFKkX3543RtSgWLL344JqoQ", "This is the description of this campaign"),
//        healthCampaign to Campaign("GevgS45omAamZUEuEV9KniFKkX3543RtSgWLL344JqoQ", "This is the description of this campaign")
//    )
//
//    // Accessing address and description for a specific campaign
//    val c = campaignList["Puppy Care"]
//    val address = c?.address
//    val description = c?.description
//}