package com.example.decentralisedapp.viewmodels

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.portto.solana.web3.Connection
import com.portto.solana.web3.PublicKey
import com.portto.solana.web3.SerializeConfig
import com.portto.solana.web3.Transaction
import com.portto.solana.web3.programs.MemoProgram
import com.portto.solana.web3.programs.SystemProgram
import com.portto.solana.web3.rpc.types.config.Commitment
import com.portto.solana.web3.util.Cluster
import com.solana.mobilewalletadapter.clientlib.ActivityResultSender
import com.solana.mobilewalletadapter.clientlib.MobileWalletAdapter
import com.solana.mobilewalletadapter.clientlib.MobileWalletAdapter.Companion.TAG
import com.solana.mobilewalletadapter.clientlib.RpcCluster
import com.solana.mobilewalletadapter.clientlib.TransactionResult
import com.solana.mobilewalletadapter.clientlib.successPayload
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.bitcoinj.core.Base58

data class SnapViewState(
    val userAddress: String = "",
    val userLabel: String = "",
    val authToken: String = "",
    val noWallet: Boolean = false,
)

class SnapViewModel: ViewModel() {

    private val api by lazy { Connection(Cluster.DEVNET) }

    private fun SnapViewState.updateViewState() {
        _state.update { this }
    }

    private val _state = MutableStateFlow(SnapViewState())

    val viewState: StateFlow<SnapViewState>
        get() = _state

    init {
        viewModelScope.launch {
            _state.value = SnapViewState()
        }
    }

    fun connect(
        identityUri: Uri,
        iconUri: Uri,
        identityName: String,
        activityResultSender: ActivityResultSender
    ) {
        viewModelScope.launch {
            val walletAdapterClient = MobileWalletAdapter()
            val result = walletAdapterClient.transact(activityResultSender) {
                authorize(
                    identityUri = identityUri,
                    iconUri = iconUri,
                    identityName = identityName,
                    rpcCluster = RpcCluster.Devnet
                )
            }

            when (result) {
                is TransactionResult.Success -> {
                    _state.value.copy(
                        userAddress = PublicKey(result.payload.publicKey).toBase58(),
                        userLabel = result.payload.accountLabel ?: "",
                        authToken = result.payload.authToken,
                    ).updateViewState()
                    Log.d(TAG, "connect: $result")
                }

                is TransactionResult.NoWalletFound -> {
                    _state.value.copy(
                        noWallet = true,
                    ).updateViewState()
                }

                is TransactionResult.Failure -> {
                    _state.value.copy().updateViewState()
                }
            }
        }
    }

    fun disconnect() {
        viewModelScope.launch {
            _state.update {
                _state.value.copy(
                    userAddress = "",
                    userLabel = "",
                    authToken = "",
                )
            }
        }
    }

    fun sign_message(
        identityUri: Uri,
        iconUri: Uri,
        identityName: String,
        activityResultSender: ActivityResultSender
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.d(TAG, "Starting sign_message transaction")

                // Step 1: Get latest block hash
                val blockHash = api.getLatestBlockhash(Commitment.FINALIZED)
                Log.d(TAG, "Latest block hash: $blockHash")

                // Step 2: Create transaction with Memo

                val tx = Transaction()
                tx.add(MemoProgram.writeUtf8(PublicKey(_state.value.userAddress), "memoText"))
                tx.setRecentBlockHash(blockHash!!)
                tx.feePayer = PublicKey(_state.value.userAddress)
                Log.d(TAG, "Transaction created with memo")

                // Step 3: Serialize transaction
                val bytes = tx.serialize(SerializeConfig(requireAllSignatures = false))
                Log.d(TAG, "Transaction serialized")

                // Step 4: Start signing process
                val walletAdapterClient = MobileWalletAdapter()
                val result = walletAdapterClient.transact(activityResultSender) {
                    Log.d(TAG, "Attempting reauthorization")
                    reauthorize(identityUri, iconUri, identityName, _state.value.authToken)
                    Log.d(TAG, "Reauthorization successful")

                    Log.d(TAG, "Signing and sending transaction")
                    signAndSendTransactions(arrayOf(bytes))

                    // Log.d("SignSend:",signAndSendTransactions(arrayOf(bytes)).toString())
                }

                result.successPayload?.signatures?.firstOrNull()?.let { sig ->
                    val readableSig = Base58.encode(sig)
                    Log.d(
                        TAG,
                        "sign_message: https://explorer.solana.com/tx/$readableSig?cluster=devnet"
                    )
                }
            }
        }
    }

    fun sendSol(
        recipientAddress: String,
        amountInSol: Double,
        memo: String,
        identityUri: Uri,
        iconUri: Uri,
        identityName: String,
        activityResultSender: ActivityResultSender
    ) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.d(TAG, "Starting SOL transfer transaction")

                // Step 1: Get latest block hash
                val blockHash = api.getLatestBlockhash(Commitment.FINALIZED)
                Log.d(TAG, "Latest block hash: $blockHash")

                // Step 2: Create transaction for transferring SOL
                val lamports = (amountInSol * 1_000_000_000).toLong() // Convert SOL to lamports
                val tx = Transaction()
                tx.add(
                    SystemProgram.transfer(
                        fromPublicKey = PublicKey(_state.value.userAddress),
                        toPublicKey = PublicKey(recipientAddress),
                        lamports = lamports
                    ),
                    MemoProgram.writeUtf8(PublicKey(_state.value.userAddress), memo)
                )
                tx.setRecentBlockHash(blockHash!!)
                tx.feePayer = PublicKey(_state.value.userAddress)
                Log.d(TAG, "SOL transfer transaction created")

                // Step 3: Serialize transaction
                val bytes = tx.serialize(SerializeConfig(requireAllSignatures = false))
                Log.d(TAG, "Transaction serialized")

                // Step 4: Start signing process
                val walletAdapterClient = MobileWalletAdapter()
                val result = walletAdapterClient.transact(activityResultSender) {
                    reauthorize(identityUri, iconUri, identityName, _state.value.authToken)
                    signAndSendTransactions(arrayOf(bytes))
                }

                result.successPayload?.signatures?.firstOrNull()?.let { sig ->
                    val readableSig = Base58.encode(sig)
                    Log.d(
                        TAG,
                        "sendSol: https://explorer.solana.com/tx/$readableSig?cluster=devnet"
                    )
                } ?: Log.d(TAG, "Transaction failed: ${result as? TransactionResult.Failure}")
            }
        }
    }

//    fun viewAccountinfo(recepientAddress: String) {
//
//        CoroutineScope(Dispatchers.IO).launch {
//            Log.d(
//                "AccountInfo:",
//                api.getBalance(PublicKey(recepientAddress)).toString()
//            )
//            Log.d("AccountInfo", api.getProgramAccounts(MemoProgram.PROGRAM_ID).toString())
//
//        }
//    }
//
//    // Account Information Retrieval
//    fun getAccountInformation1(accountPublicKey: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            try {
//                val accountInfo = api.getBalanceAndContext(PublicKey(accountPublicKey)).context.slot
//                Log.d("AccountInfo", "Info for $accountPublicKey: ${accountInfo}")
//            } catch (e: Exception) {
//                Log.e("Error", "Failed to retrieve account info: ${e.message}")
//            }
//        }
//    }
//    // Account Information Retrieval
//    fun getAccountInformation2(accountPublicKey: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//                val slot = api.getBalanceAndContext(PublicKey(accountPublicKey)).context.slot
//                val block = api.getBlock(338221956)
//                val signature = api.getBlockSignatures(slot)
//                val signatureForAddress = api.getSignaturesForAddress(
//                    PublicKey(accountPublicKey.toByteArray()),
//                    null,
//                    Finality.FINALIZED
//                    )
//                Log.d("AccountInfo", "Info for $accountPublicKey: ${signature}")
//                Log.d("Sign Address", "${signatureForAddress}")
//                Log.d("Block:", "${block?.transactions?.get(0)?.transaction?.signatures?.get(0)}")
//        }
//    }
//
//    fun getAccountInformation3(accountPublicKey: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//                val slot = api.getBalanceAndContext(PublicKey(accountPublicKey)).context.slot
//                val block = api.getBlock(338221956)
//                val signature = api.getBlockSignatures(slot)
//            val signatureForAddress = api.getSignaturesForAddress(
//                PublicKey(accountPublicKey),
//                null,
//                Finality.FINALIZED
//            )
//
//            Log.d("AccountInfo", "Info for $accountPublicKey: ${signature}")
//            Log.d("Sign Address", "${signatureForAddress.get(1).signature}")
//            Log.d("Sign Address sign", "${signatureForAddress.get(0).signature}")
//            Log.d("Sign Address sign memo", "${signatureForAddress.get(0).memo}")
//            //    Log.d("Block:", "${block?.transactions}")
//        }
//    }

    fun ViewTotalDonations(campaignPublicKey: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val balance = api.getBalance(PublicKey(campaignPublicKey))

            val x = api.getProgramAccounts(programId = SystemProgram.PROGRAM_ID)
            Log.d("System program:",x.get(0).account.data.toString())
        }

    }

}
