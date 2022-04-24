package io.brick.springcorebasic.autowired

import io.brick.springcorebasic.AutoAppConfig
import io.brick.springcorebasic.discount.DiscountPolicy
import io.brick.springcorebasic.member.Grade
import io.brick.springcorebasic.member.Member
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class AllBeanTest {

    @Test
    fun findAllBean() {
        val ac: ApplicationContext =
            AnnotationConfigApplicationContext(AutoAppConfig::class.java, DiscountService::class.java)

        val discountService = ac.getBean(DiscountService::class.java)
        val member = Member(1L, "member-A", Grade.VIP)
        var discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy")

        assertThat(discountService).isInstanceOf(DiscountService::class.java)
        assertThat(discountPrice).isEqualTo(1000)

        var rateDiscountPrice = discountService.discount(member, 20000, "rateDiscountPolicy")
        assertThat(rateDiscountPrice).isEqualTo(2000)
    }

    class DiscountService(
        private val policyMap: Map<String, DiscountPolicy>,
        private val policies: List<DiscountPolicy>
    ) {
        fun discount(member: Member?, price: Int, discountCode: String): Int? {
            val discountPolicy = policyMap[discountCode]
            return discountPolicy?.discount(member, price)
        }

        init {
            println("policyMap = ${policyMap}")
            println("policies = ${policies}")
        }
    }
}