package io.github.spleat.ws

import okhttp3.WebSocket

sealed class SocketEvent {
    data class TextMessage(val text: String) : SocketEvent()
    data class SocketOpenEvent(val socket: WebSocket) : SocketEvent()
}