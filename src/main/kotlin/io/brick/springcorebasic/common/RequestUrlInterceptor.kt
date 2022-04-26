package io.brick.springcorebasic.common

import mu.KLogging
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class RequestUrlInterceptor(
    private val myLogger: MyLogger
): HandlerInterceptor {
    companion object: KLogging()

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        logger.info { "##################### Interceptor preHadnler #####################" }

        val requestURL = request.requestURL.toString()

        myLogger.requestURL = requestURL
        myLogger.log("RequestUrlInterceptor.preHandle")

        return true
    }
}