package io.github.spleat.ws

import android.util.Log

import io.reactivex.FlowableEmitter
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString

class WebSocketEventRouter(private val emitter: FlowableEmitter<SocketEvent>) : WebSocketListener() {

    override fun onOpen(webSocket: WebSocket?, response: Response?) {
        if (!emitter.isCancelled) {
            emitter.onNext(SocketEvent.SocketOpenEvent(webSocket!!))
            Log.e("kasper", "socketOpen")
        }
    }

    override fun onMessage(webSocket: WebSocket?, text: String?) {
        if (!emitter.isCancelled) {
            emitter.onNext(SocketEvent.TextMessage(text!!))
        }
    }

    override fun onMessage(webSocket: WebSocket?, bytes: ByteString?) {
        if (!emitter.isCancelled) {
            //            emitter.onNext(new SocketMessageEvent(bytes));
            Log.e("kasper", "onMessageByte")
        }
    }

    override fun onClosing(webSocket: WebSocket?, code: Int, reason: String?) {
        if (!emitter.isCancelled) {
            //            emitter.onNext(new SocketClosingEvent(code, reason));
            Log.e("kasper", "socketClosing")

        }
    }

    override fun onClosed(webSocket: WebSocket?, code: Int, reason: String?) {
        if (!emitter.isCancelled) {
            Log.e("kasper", "socketClosed")
        }
    }

    override fun onFailure(webSocket: WebSocket?, t: Throwable?, response: Response?) {
        if (!emitter.isCancelled) {
            Log.e("kasper", "socketfailure")
        }
    }
}