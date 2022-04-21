package io.brick.springcorebasic.order

import io.brick.springcorebasic.discount.DiscountPolicy
import io.brick.springcorebasic.member.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class OrderServiceImpl @Autowired constructor(
    private val memberRepository: MemberRepository,
    private val discountPolicy: DiscountPolicy
) : OrderService {
    override fun createOrder(
        memberId: Long,
        itemName: String,
        itemPrice: Int
    ): Order {
        val member = memberRepository.findById(memberId)
            ?: throw java.lang.RuntimeException("존재하지 않는 회원입니다. memberId=${memberId}")

        val discountPrice = discountPolicy.discount(
            member = member,
            price = itemPrice
        )

        return Order(
            memberId = memberId,
            itemName = itemName,
            itemPrice = itemPrice,
            discountPrice = discountPrice
        )
    }

    // 테스트 용도
    fun getMemberRepository(): MemberRepository {
        return this.memberRepository
    }
}