package io.brick.springcorebasic.config

import io.brick.springcorebasic.common.MyLogger
import io.brick.springcorebasic.common.RequestUrlInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val myLogger: MyLogger
): WebMvcConfigurer {
    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(RequestUrlInterceptor(myLogger))
    }
}