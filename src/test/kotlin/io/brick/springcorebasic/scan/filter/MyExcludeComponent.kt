package io.brick.springcorebasic.scan.filter

import java.lang.annotation.*
import java.lang.annotation.Retention
import java.lang.annotation.Target

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
annotation class MyExcludeComponent()
