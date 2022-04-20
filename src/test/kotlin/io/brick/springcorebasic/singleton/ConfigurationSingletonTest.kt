package io.brick.springcorebasic.singleton

import io.brick.springcorebasic.config.AppConfig
import io.brick.springcorebasic.member.MemberRepository
import io.brick.springcorebasic.member.MemberServiceImpl
import io.brick.springcorebasic.order.OrderServiceImpl
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ConfigurationSingletonTest {

    @Test
    fun configurationTest() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

        val memberService = ac.getBean("memberService", MemberServiceImpl::class.java)
        val orderService = ac.getBean("orderService", OrderServiceImpl::class.java)
        val memberRepository = ac.getBean("memberRepository", MemberRepository::class.java)

        val memberRepository1 = memberService.getMemberRepository()
        val memberRepository2 = orderService.getMemberRepository()

        assertSame(memberRepository, memberRepository1)
        assertSame(memberRepository, memberRepository2)
    }

    @Test
    fun configurationDeep() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
        val bean = ac.getBean(AppConfig::class.java)

        // @Configuration 붙은 경우에 해당
        // AppConfig$$CGLIB - AppConfig를 상속받은 자식 클래스
        // io.brick.springcorebasic.config.AppConfig$$EnhancerBySpringCGLIB$$667597a4
        // 여기서 싱글톤으로 객체가 관리되도록 해준다.
        println("bean = ${bean.javaClass}")
    }
}