package io.brick.springcorebasic.order

import io.brick.springcorebasic.discount.DiscountPolicy
import io.brick.springcorebasic.member.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

//  - 1. 생성자 주입 때는 생성자가 하나인 경우 @Autowired 생략해도 된다. (웬만하면 생성자 주입 방식 추천)
//  - 2. setter 방식 주입 때는 선택적으로 주입을 할 수 있다. (원하는 경우 주입을 안할수도 있다.)
//      - @Autowired(required = false)
//  - 3. field 주입은 간결해서 보기 좋아 보이지만 DI framework(Spring Container 등)가 없으면 순수 자바로는 테스트해볼 수 없다.
//      - NullPointerException
//      - 단, JUnit Test 때는 필드 주입 방식을 사용해도된다. (테스트할 때만 사용하기 때문)
//      - Config 코드 내에서도 필드 주입으로 외부 bean을 가져다 사용해도 괜찮다.
@Component
class OrderServiceImpl(
    private val memberRepository: MemberRepository,
    private val discountPolicy: DiscountPolicy
) : OrderService {

//  - 4. 이런 식의 일반 메소드로 주입하는 방식이 있다.
//    - 거의 사용하는 방식이 아니다.
//    @Autowired
//    fun init(memberRepository: MemberRepository, discountPolicy: DiscountPolicy) {
//        this.memberRepository = memberRepository
//        this.discountPolicy = discountPolicy
//    }

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