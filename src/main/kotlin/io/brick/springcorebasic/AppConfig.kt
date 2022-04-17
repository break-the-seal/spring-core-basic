package io.brick.springcorebasic

import io.brick.springcorebasic.discount.DiscountPolicy
import io.brick.springcorebasic.discount.FixDiscountPolicy
import io.brick.springcorebasic.member.MemberRepository
import io.brick.springcorebasic.member.MemberService
import io.brick.springcorebasic.member.MemberServiceImpl
import io.brick.springcorebasic.member.MemoryMemberRepository
import io.brick.springcorebasic.order.OrderService
import io.brick.springcorebasic.order.OrderServiceImpl

class AppConfig {

    // 역할과 구현 분리
    fun memberService(): MemberService = MemberServiceImpl(memberRepository())

    fun orderService(): OrderService = OrderServiceImpl(memberRepository(), discountPolicy())

    private fun memberRepository(): MemberRepository = MemoryMemberRepository()

    private fun discountPolicy(): DiscountPolicy = FixDiscountPolicy()

}