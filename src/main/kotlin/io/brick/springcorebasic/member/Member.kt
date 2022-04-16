package io.brick.springcorebasic.member

data class Member(
    var id: Long = 0L,
    var name: String,
    var grade: Grade
)