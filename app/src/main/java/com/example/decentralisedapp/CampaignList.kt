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