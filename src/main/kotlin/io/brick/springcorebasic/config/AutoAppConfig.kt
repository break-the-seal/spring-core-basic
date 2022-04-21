package io.brick.springcorebasic.config

import io.brick.springcorebasic.SpringCoreBasicApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.ComponentScan.*
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
class AutoAppConfig