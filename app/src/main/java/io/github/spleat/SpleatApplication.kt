package io.github.spleat

import android.app.Application
import android.content.Context
import com.google.gson.Gson

class SpleatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        contextProvider = { applicationContext }
        val walletManager = WalletManager(applicationContext)
        walletManagerProvider = { walletManager }
        etherPizzaServiceProvider = { EtherPizzaService(walletManager) }
    }
}

var contextProvider: () -> Context = { TODO() }
val gsonProvider by lazy { Gson() }
var etherPizzaServiceProvider: () -> EtherPizzaService = { TODO() }
var walletManagerProvider: () -> WalletManager = { TODO() }