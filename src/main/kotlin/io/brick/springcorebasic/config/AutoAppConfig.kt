package io.brick.springcorebasic.config

import io.brick.springcorebasic.SpringCoreBasicApplication
import io.brick.springcorebasic.member.MemberRepository
import io.brick.springcorebasic.member.MemoryMemberRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.Filter
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.FilterType

// basePackages 관련된 내용 설정이 없으면
// default로 해당 @ComponentScan이 붙은 설정 정보 클래스의 패키지가 시작위치가 된다.
@Configuration
@ComponentScan(
    basePackages = ["io.brick.springcorebasic"],
    basePackageClasses = [SpringCoreBasicApplication::class],
    excludeFilters = [Filter(type = FilterType.ANNOTATION, classes = [Configuration::class])]
)
class AutoAppConfig {

    // Overriding bean definition for bean 'memoryMemberRepository'
    // 아래와 같이 수동 빈 등록이 자동 빈 등록보다 우선권을 가지게 된다. (Overriding)
    // 하지만 Spring Boot에서는 이러한 중복 빈 등록에 대해서 아예 허용을 하지 않는다.
//    @Bean(name = ["memoryMemberRepository"])
//    fun memberRepository(): MemberRepository {
//        return MemoryMemberRepository()
//    }
}