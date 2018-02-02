package io.github.spleat

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import org.web3j.protocol.core.JsonRpc2_0Web3j
import org.web3j.protocol.infura.InfuraHttpService

class SpleatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        contextProvider = { applicationContext }
        val walletManager = WalletManager(applicationContext)
        walletManagerProvider = { walletManager }
        val web3 = JsonRpc2_0Web3j(InfuraHttpService("https://rinkeby.infura.io/0ZevQ4HkUCzCVBOsYZcQ"))
        etherPizzaServiceProvider = { EtherPizzaService(walletManager, web3) }
        spleatServiceProvider = { SpleatService(walletManager, web3) }
    }
}

var contextProvider: () -> Context = { TODO() }
val gsonProvider by lazy { Gson() }
var etherPizzaServiceProvider: () -> EtherPizzaService = { TODO() }
var walletManagerProvider: () -> WalletManager = { TODO() }
var spleatServiceProvider: () -> SpleatService = { TODO() }