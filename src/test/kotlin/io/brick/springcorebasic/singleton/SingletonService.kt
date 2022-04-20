package io.brick.springcorebasic.singleton

class SingletonService private constructor() {

    // 싱글톤 패턴의 문제점은 많다.
    companion object {
        private val instance = SingletonService()

        fun getInstance(): SingletonService {
            return instance
        }
    }

    fun logic() {
        println("싱글톤 객체 로직 호출")
    }
}