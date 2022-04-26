package io.brick.springcorebasic.scope

import org.junit.jupiter.api.Assertions.assertNotSame
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

class PrototypeTest {
    @Test
    fun prototypeBeanFind() {
        val ac = AnnotationConfigApplicationContext(PrototypeBean::class.java)
        println("find prototypeBean1")
        val prototypeBean1 = ac.getBean(PrototypeBean::class.java)
        println("find prototypeBean2")
        val prototypeBean2 = ac.getBean(PrototypeBean::class.java)

        println("prototypeBean1 = $prototypeBean1")
        println("prototypeBean2 = $prototypeBean2")

        assertNotSame(prototypeBean1, prototypeBean2)

        // PreDestroy 호출 X
        ac.close()
    }

    @Scope("prototype")
    private class PrototypeBean {
        @PostConstruct
        fun init() {
            println("PrototypeBean.init")
        }

        @PreDestroy
        fun destroy() {
            println("PrototypeBean.destroy")
        }
    }
}