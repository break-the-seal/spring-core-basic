package io.brick.springcorebasic.lifecycle

import mu.KLogging
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.InitializingBean

class NetworkClient : InitializingBean, DisposableBean {
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

    override fun afterPropertiesSet() {
        logger.info { "NetworkClient.afterPropertiesSet" }
        connect()
        call("초기화 연결 메시지")
    }

    override fun destroy() {
        logger.info { "NetworkClient.destroy" }
        disconnect()
    }
}