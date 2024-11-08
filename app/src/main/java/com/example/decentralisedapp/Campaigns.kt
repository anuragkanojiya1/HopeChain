package com.example.decentralisedapp

// Data class to represent each signature information
data class SignatureInfo(
    val signature: String,
    val memo: String?
)

// Data class to store account information
data class AccountInfo(
    val slot: Long,
    val balance: Long
)