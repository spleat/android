package io.github.spleat

import android.app.Application
import android.content.Context
import com.google.gson.Gson

class SpleatApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        contextProvider = { applicationContext }
    }
}

var contextProvider: () -> Context = { TODO() }
val gsonProvider by lazy { Gson() }