package io.brick.springcorebasic

import io.brick.springcorebasic.discount.DiscountPolicy
import io.brick.springcorebasic.discount.FixDiscountPolicy
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

    // 역할과 구현 분리
    @Bean
    fun memberService(): MemberService = MemberServiceImpl(memberRepository())

    @Bean
    fun orderService(): OrderService = OrderServiceImpl(memberRepository(), discountPolicy())

    @Bean
    fun memberRepository(): MemberRepository = MemoryMemberRepository()

    @Bean
    fun discountPolicy(): DiscountPolicy = FixDiscountPolicy()

}