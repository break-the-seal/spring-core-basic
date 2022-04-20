package io.brick.springcorebasic.config

import io.brick.springcorebasic.discount.DiscountPolicy
import io.brick.springcorebasic.discount.FixDiscountPolicy
import io.brick.springcorebasic.discount.RateDiscountPolicy
import io.brick.springcorebasic.member.MemberRepository
import io.brick.springcorebasic.member.MemberService
import io.brick.springcorebasic.member.MemberServiceImpl
import io.brick.springcorebasic.member.MemoryMemberRepository
import io.brick.springcorebasic.order.OrderService
import io.brick.springcorebasic.order.OrderServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun memberService(): MemberService {
        println("call AppConfig.memberService")
        return MemberServiceImpl(memberRepository())
    }

    @Bean
    fun memberRepository(): MemberRepository {
        println("call AppConfig.memberRepository")  // 한 번만 호출된다.
        return MemoryMemberRepository()
    }

    @Bean
    fun orderService(): OrderService {
        println("call AppConfig.orderService")
        return OrderServiceImpl(memberRepository(), discountPolicy())
    }

    @Bean
    fun discountPolicy(): DiscountPolicy {
        return FixDiscountPolicy()
//        return RateDiscountPolicy()
    }
}