package io.brick.springcorebasic.autowired

import io.brick.springcorebasic.config.AutoAppConfig
import io.brick.springcorebasic.discount.DiscountPolicy
import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertInstanceOf
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AllBeanTest {
    @Test
    fun findAllBean() {
        val ac: ApplicationContext =
            AnnotationConfigApplicationContext(AutoAppConfig::class.java, DiscountService::class.java)

        val discountService = ac.getBean(DiscountService::class.java)
        assertInstanceOf(DiscountService::class.java, discountService)

        val member = Member(1L, "userA", Grade.VIP)
        val discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy")
        assertEquals(discountPrice, 1000)

        val rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy")
        assertEquals(rateDiscountPrice, 2000)
    }

    private class DiscountService {
        private val policyMap: Map<String, DiscountPolicy>
        private val policies: List<DiscountPolicy>

        constructor(policyMap: Map<String, DiscountPolicy>, policies: List<DiscountPolicy>) {
            this.policyMap = policyMap
            this.policies = policies
            println("policyMap = ${this.policyMap}")
            println("policies = ${this.policies}")
        }

        fun discount(member: Member, price: Int, discountCode: String): Int {
            return policyMap[discountCode]?.discount(member, price)
                ?: throw RuntimeException("no discount policy")
        }
    }
}