package com.example.decentralisedapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portto.solana.web3.Connection
import com.portto.solana.web3.PublicKey
import com.portto.solana.web3.rpc.types.config.Finality
import com.portto.solana.web3.util.Cluster
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class DappViewModel : ViewModel() {

    private var pollingJob: Job? = null  // Job to control the polling coroutine
    private val api by lazy { Connection(Cluster.DEVNET) }

    // Retrieve list of signatures for an account, including memo information
    fun getSignaturesWithMemo(accountPublicKey: String, onResult: (List<SignatureInfo>) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val signatures = api.getSignaturesForAddress(
                    PublicKey(accountPublicKey),
                    null,
                    Finality.FINALIZED
                )

                val signatureInfoList = signatures.map { signatureResult ->
                    SignatureInfo(
                        signature = signatureResult.signature,
                        memo = signatureResult.memo
                    )
                }

                Log.d("SignaturesWithMemo", "Signatures for $accountPublicKey: $signatureInfoList")
                onResult(signatureInfoList)
            } catch (e: Exception) {
                Log.e("Error", "Failed to retrieve signatures: ${e.message}")
                onResult(emptyList())
            }
        }
    }

    fun getAccountInformation3(accountPublicKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val slot = api.getBalanceAndContext(PublicKey(accountPublicKey)).context.slot
            val signature = api.getBlockSignatures(slot)
            val signatureForAddress = api.getSignaturesForAddress(
                PublicKey(accountPublicKey),
                null,
                Finality.FINALIZED
            )

            for(x in 0..10){
                Log.d("Hello",signatureForAddress.get(x).signature)
                Log.d("Hello",signatureForAddress.get(x).memo.toString())
            }

        }
    }

    // View total donations by retrieving the balance of a campaign account
    fun viewTotalDonations(campaignPublicKey: String, onResult: (Long?) -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val balance = api.getBalance(PublicKey(campaignPublicKey))
                Log.d("TotalDonations", "Balance for campaign $campaignPublicKey: $balance")
                onResult(balance)
            } catch (e: Exception) {
                Log.e("Error", "Failed to retrieve campaign balance: ${e.message}")
                onResult(null)
            }
        }
    }

    // Polling function to keep checking for new signatures
    fun startSignaturePolling(accountPublicKey: String, intervalMillis: Long = 5000L) {
        viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {
                try {
                    getSignaturesWithMemo(accountPublicKey) { signatures ->
                        // Process and display the signatures (e.g., update UI, log the result)
                        Log.d("PollingSignatures", "Updated signatures: $signatures")
                    }
                } catch (e: Exception) {
                    Log.e("PollingError", "Error during signature polling: ${e.message}")
                }

                // Wait for the specified interval before the next poll
                delay(intervalMillis)
            }
        }
    }

    // Stop polling by canceling the job
    fun stopSignaturePolling() {
        pollingJob?.cancel()  // Cancel the coroutine job
        pollingJob = null
    }

    override fun onCleared() {
        super.onCleared()
        stopSignaturePolling()  // Ensure polling is stopped when ViewModel is cleared
    }

}