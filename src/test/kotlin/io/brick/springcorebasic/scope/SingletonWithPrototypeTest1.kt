package io.brick.springcorebasic.scope

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Scope
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy
import javax.inject.Provider

class SingletonWithPrototypeTest1 {

    @Test
    fun prototypeFind() {
        val ac = AnnotationConfigApplicationContext(PrototypeBean::class.java)
        val prototypeBean1 = ac.getBean(PrototypeBean::class.java)
        prototypeBean1.addCount()

        assertEquals(prototypeBean1.count, 1)

        val prototypeBean2 = ac.getBean(PrototypeBean::class.java)
        prototypeBean2.addCount()

        assertEquals(prototypeBean2.count, 1)

        ac.close()
    }

    @Test
    fun singletonClientUsePrototype() {
        val ac = AnnotationConfigApplicationContext(ClientBean::class.java, PrototypeBean::class.java)

        val clientBean1 = ac.getBean(ClientBean::class.java)
        val count1 = clientBean1.logic()
        assertEquals(count1, 1)

        val clientBean2 = ac.getBean(ClientBean::class.java)
        val count2 = clientBean2.logic()
        assertEquals(count2, 2) // ClientBean > PrototypeBean 같은 객체 사용

       ac.close()
    }

    @Test
    fun singletonClientUsePrototypeByProvider() {
        val ac = AnnotationConfigApplicationContext(ClientBean2::class.java, PrototypeBean::class.java)

        val clientBean1 = ac.getBean(ClientBean2::class.java)
        val count1 = clientBean1.logic()
        assertEquals(count1, 1)

        val clientBean2 = ac.getBean(ClientBean2::class.java)
        val count2 = clientBean2.logic()
        assertEquals(count2, 1) // 다른 PrototypeBean 객체

        ac.close()
    }

    @Scope("singleton")
    private class ClientBean(
        private val prototypeBean: PrototypeBean
    ) {
        fun logic(): Int {
            prototypeBean.addCount()

            return prototypeBean.count
        }
    }

    @Scope("singleton")
    private class ClientBean2(
        // 이전에는 ObjectFactory -> ObjectProvider
        private val prototypeBeanProvider: Provider<PrototypeBean>
    ) {
        fun logic(): Int {
            val prototypeBean = prototypeBeanProvider.get()
            prototypeBean.addCount()

            return prototypeBean.count
        }
    }

    @Scope("prototype")
    private class PrototypeBean {
        var count: Int = 0
            private set

        fun addCount() {
            count++
        }

        @PostConstruct
        fun init() {
            println("PrototypeBean.init $this")
        }

        @PreDestroy
        fun destroy() {
            println("PrototypeBean.destroy")
        }
    }
}