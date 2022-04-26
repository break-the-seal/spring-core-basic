package io.brick.springcorebasic.web

import io.brick.springcorebasic.common.MyLogger
import mu.KLogging
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletRequest

@Controller
class LogDemoController(
    private val logDemoService: LogDemoService,
    private val myLoggerProvider: ObjectProvider<MyLogger>
) {
    companion object: KLogging()

    @RequestMapping("log-demo")
    @ResponseBody
    fun logDemo(request: HttpServletRequest): String {
        logger.info { "##################### Controller \"logDemo\" Handler #####################" }

        val myLogger = myLoggerProvider.getObject()
        myLogger.log("controller test")

        logDemoService.logic("testId")

        return "OK"
    }
}