package io.brick.springcorebasic.annotation

import org.springframework.beans.factory.annotation.Qualifier
import java.lang.annotation.Inherited

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.TYPE,
    AnnotationTarget.ANNOTATION_CLASS,
    AnnotationTarget.TYPE_PARAMETER,
    AnnotationTarget.VALUE_PARAMETER
)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@MustBeDocumented
@Qualifier("mainDiscountPolicy")
annotation class MainDiscountPolicy() {

}