package io.brick.springcorebasic.common

import mu.KLogging
import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import java.util.UUID
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
class MyLogger {
    companion object: KLogging()

    var uuid: String? = null
    var requestURL: String? = null

    fun log(message: String) {
        logger.info { "[${uuid}][${requestURL}] ${message}" }
    }

    @PostConstruct
    fun init() {
        uuid = UUID.randomUUID().toString()
        logger.info { "[${uuid}] request scope bean create: ${this}" }
    }

    @PreDestroy
    fun close() {
        logger.info { "[${uuid}] request scope bean close: ${this}" }
    }
}