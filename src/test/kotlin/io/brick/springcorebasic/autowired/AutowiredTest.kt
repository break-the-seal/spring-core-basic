package io.brick.springcorebasic.autowired

import io.brick.springcorebasic.member.Member
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.util.*

class AutowiredTest {

    @Test
    fun autowiredOptionTest() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(TestBean::class.java)
    }

    private class TestBean {

        // "required = false" 옵션일 때
        // noBean1에 해당하는 빈이 없으면 setter 자체가 실행이 안된다.
        @Autowired(required = false)
        fun setNoBean1(noBean1: Member?) {
            println("noBean1 = $noBean1")
        }

        @Autowired
        fun setNoBean2(noBean2: Member?) {
            println("noBean2 = $noBean2")
        }

        @Autowired
        fun setNoBean3(noBean3: Optional<Member>) {
            println("noBean3 = $noBean3")
        }
    }

}