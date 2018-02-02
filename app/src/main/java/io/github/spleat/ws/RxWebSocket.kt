package io.github.spleat.ws

import android.util.Log
import com.google.gson.Gson
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.PublishProcessor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.WebSocket

class RxWebSocket {

    private val webSocketOnSubscribe: WebSocketOnSubscribe
    private var socketEventProcessor = PublishProcessor.create<SocketEvent>()
    private val disposables = CompositeDisposable()
    private var connectionDisposables: CompositeDisposable? = null
    private var webSocket: WebSocket? = null

    private val eventSource: Flowable<SocketEvent>
        get() = socketEventProcessor.onErrorResumeNext { throwable: Throwable ->
            Log.e(TAG, "RxWebSocket EventSubject internal error occured.")
            Log.e(TAG, throwable.message)
            throwable.printStackTrace()
            socketEventProcessor = PublishProcessor.create()
            socketEventProcessor
        }

    constructor(connectionUrl: String) {
        this.webSocketOnSubscribe = WebSocketOnSubscribe(connectionUrl)
    }

    constructor(client: OkHttpClient, connectionUrl: String) {
        this.webSocketOnSubscribe = WebSocketOnSubscribe(client, connectionUrl)
    }

    fun onTextMessage(): Flowable<SocketEvent.TextMessage> {
        return eventSource
                .ofType(SocketEvent.TextMessage::class.java)
    }

    @Synchronized
    fun connect() {
        connectionDisposables = CompositeDisposable()
        val webSocketInstanceDisposable = eventSource
                .ofType(SocketEvent.SocketOpenEvent::class.java)
                .firstElement()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                        { (socket) -> webSocket = socket }
                ) { throwable ->
                    Log.e(TAG, throwable.message)
                    throwable.printStackTrace()
                }
        val connectionDisposable = Flowable.create(webSocketOnSubscribe, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(
                        { event -> socketEventProcessor.onNext(event) }
                ) { throwable ->
                    Log.e(TAG, throwable.message)
                    throwable.printStackTrace()
                }
        connectionDisposables!!.add(webSocketInstanceDisposable)
        connectionDisposables!!.add(connectionDisposable)
        disposables.add(connectionDisposable)
    }

    @Synchronized
    fun sendMessage(gson: Gson, payload: Any): Single<Boolean> {
        return Single.fromCallable {
            if (webSocket != null) {
                val jsonBody = Gson().toJson(payload)
                webSocket!!.send(jsonBody)
            } else {
                throw RuntimeException("WebSocket not connected!")
            }
        }
    }

    @Synchronized
    fun sendMessage(content: String): Single<Boolean> {
        return Single.fromCallable {
            if (webSocket != null) {
                webSocket!!.send(content)
            } else {
                throw RuntimeException("WebSocket not connected!")
            }
        }
    }

    companion object {

        private val TAG = "RxWebSocket"
    }
}