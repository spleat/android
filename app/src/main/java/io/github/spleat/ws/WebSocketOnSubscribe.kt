package io.github.spleat.ws

import java.util.concurrent.TimeUnit

import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import okhttp3.OkHttpClient
import okhttp3.Request

class WebSocketOnSubscribe : FlowableOnSubscribe<SocketEvent> {

    private val client: OkHttpClient
    private val request: Request

    constructor(url: String) {
        client = OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build()

        request = Request.Builder()
                .url(url)
                .build()
    }

    constructor(client: OkHttpClient, url: String) {
        this.client = client
        request = Request.Builder()
                .url(url)
                .build()
    }

    override fun subscribe(emitter: FlowableEmitter<SocketEvent>) {
        client.newWebSocket(request, WebSocketEventRouter(emitter))
    }
}