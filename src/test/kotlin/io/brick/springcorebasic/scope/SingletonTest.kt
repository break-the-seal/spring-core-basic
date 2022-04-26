package io.brick.springcorebasic.scope

import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class SingletonTest {
    @Test
    fun singletonBeanFind() {
        val ac = AnnotationConfigApplicationContext(SingletonBean::class.java)
        val singletonBean1 = ac.getBean(SingletonBean::class.java)
        val singletonBean2 = ac.getBean(SingletonBean::class.java)

        println("singletonBean1 = $singletonBean1")
        println("singletonBean2 = $singletonBean2")

        assertSame(singletonBean1, singletonBean2)

        ac.close()
    }

    @Scope("singleton")
    private class SingletonBean {
        @PostConstruct
        fun init() {
            println("SingletonBean.init")
        }

        @PreDestroy
        fun destroy() {
            println("SingletonBean.destroy")
        }
    }
}