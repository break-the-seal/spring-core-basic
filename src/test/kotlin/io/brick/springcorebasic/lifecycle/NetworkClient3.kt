package io.brick.springcorebasic.lifecycle

import mu.KLogging
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class NetworkClient3 {
    companion object : KLogging()

    var url: String? = null

    constructor() {
        logger.info { "생성자 호출, url = $url" }
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

    @PostConstruct
    fun init() {
        logger.info { "NetworkClient3.init" }
        connect()
        call("초기화 연결 메시지")
    }

    @PreDestroy
    fun close() {
        logger.info { "NetworkClient3.close" }
        disconnect()
    }
}