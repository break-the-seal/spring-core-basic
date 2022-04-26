package io.brick.springcorebasic.lifecycle

import mu.KLogging

class NetworkClient2 {
    companion object : KLogging()

    var url: String? = null

    constructor() {
        logger.info { "생성자 호출, url = $url" }
//        connect()
//        call("초기화 연결 메시지")
    }

    fun connect() {
        logger.info { "connect: $url" }
    }

    fun call(message: String) {
        logger.info { "call: $url, message: $message" }
    }

    fun disconnect() {
        logger.info { "close: $url" }
    }

    fun init() {
        logger.info { "NetworkClient2.init" }
        connect()
        call("초기화 연결 메시지")
    }

    fun close() {
        logger.info { "NetworkClient2.close" }
        disconnect()
    }
}