package io.github.spleat

import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import com.elpassion.android.commons.sharedpreferences.asProperty
import com.elpassion.android.commons.sharedpreferences.createSharedPrefs
import com.elpassion.sharedpreferences.gsonadapter.gsonConverterAdapter
import org.web3j.crypto.Credentials
import org.web3j.crypto.WalletUtils
import java.io.File

class WalletManager(
        private val context: Context
) {
    private var walletFileName by sharedPrefsProvider<String>().asProperty("walletFile")

    fun getWallet(): Credentials {
        val credentialsVault = File(context.filesDir, "credentials")
        if (!credentialsVault.exists()) {
            credentialsVault.mkdir()
        }
        if (walletFileName == null) {
            Log.e("kasper","createing wallet")
            val fullNewWalletFile = WalletUtils.generateLightNewWalletFile("password", credentialsVault)
            walletFileName = fullNewWalletFile
            Log.e("kasper", "walletManager created $fullNewWalletFile")
        }

        return WalletUtils.loadCredentials("password", File(credentialsVault, walletFileName)).also {
            Log.e("kasper","myAddress ${it.address}")
        }
    }
}

inline fun <reified T> sharedPrefsProvider() =
        createSharedPrefs({ PreferenceManager.getDefaultSharedPreferences(contextProvider()) }, gsonConverterAdapter<T>(gson = gsonProvider))
