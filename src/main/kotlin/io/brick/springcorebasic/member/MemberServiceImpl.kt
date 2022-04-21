package io.brick.springcorebasic.member

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * @Autowired > ac.getBean(MemberRepository::class.java)
 * 생략해도 알아서 autowired 수행
 */
@Component
class MemberServiceImpl @Autowired constructor(
    private val memberRepository: MemberRepository
) : MemberService {
    override fun join(member: Member) {
        memberRepository.save(member)
    }

    override fun findMember(memberId: Long): Member? {
        return memberRepository.findById(memberId)
    }

    // 테스트 용도
    fun getMemberRepository(): MemberRepository {
        return this.memberRepository
    }
}