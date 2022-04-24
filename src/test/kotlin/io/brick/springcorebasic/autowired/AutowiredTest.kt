package io.brick.springcorebasic.autowired

import io.brick.springcorebasic.member.Member
import org.jetbrains.annotations.Nullable
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.util.Optional

class AutowiredTest {

    class TestBean {
        @Autowired(required = false)
        fun setNoBean1(noBean1: Member) {
            println("noBean1 = ${noBean1}")
        }

        @Autowired
        fun setNoBean2(@Nullable noBean2: Member) {
            println("noBean2 = ${noBean2}")
        }

        @Autowired
        fun setNoBean3(noBean3: Optional<Member>) {
            println("noBean2 = ${noBean3}")
        }
    }

    @Test
    fun autowiredOption() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(TestBean::class.java)

    }
}